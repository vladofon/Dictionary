package com.annoying.dictionary.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.annoying.dictionary.dao.AbstractCrudDao;
import com.annoying.dictionary.dao.ExampleDao;
import com.annoying.dictionary.mappers.ExampleMapper;
import com.annoying.dictionary.models.Example;

public class ExampleDaoImpl extends AbstractCrudDao<Example, Long> implements ExampleDao {

	private final JdbcTemplate jdbcTemplate;

	private final String FIND_BY_ID = "SELECT * FROM examples WHERE example_id = ?";
	private final String FIND_ALL = "SELECT * FROM examples";
	private final String DELETE_BY_ID = "DELETE FROM examples WHERE example_id = ?";
	private final String CREATE = "INSERT INTO examples (signature) VALUES (?)";
	private final String UPDATE = "UPDATE examples SET signature = ? WHERE example_id = ?";

	@Autowired
	ExampleDaoImpl(DataSource dataSourse) {
		this.jdbcTemplate = new JdbcTemplate(dataSourse);
	}

	@Override
	public Optional<Example> findById(Long id) {
		Optional<Example> entity = Optional.ofNullable(jdbcTemplate.queryForObject(FIND_BY_ID, new ExampleMapper()));
		if (entity.isEmpty()) {
			return Optional.empty();
		}
		return entity;
	}

	@Override
	public List<Example> findAll() {
		return jdbcTemplate.query(FIND_ALL, new ExampleMapper());
	}

	@Override
	public void deleteById(Long id) {
		boolean isSucceeded = jdbcTemplate.update(DELETE_BY_ID, id) > 0;
		if (!isSucceeded) {
			throw new IllegalArgumentException("Word with id(" + id + ") doesn't exist");
		}
	}

	@Override
	protected Example create(Example entity) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				PreparedStatement ps = con.prepareStatement(CREATE, new String[] { "word_id" });
				ps.setString(1, entity.getSignature());
				return ps;
			}
		}, keyHolder);
		return new Example(keyHolder.getKey().longValue(), entity.getSignature());
	}

	@Override
	protected Example update(Example entity) {
		jdbcTemplate.update(UPDATE, entity.getSignature(), entity.getId());
		Optional<Example> updatedEntity = Optional
				.ofNullable(jdbcTemplate.queryForObject(FIND_BY_ID, new ExampleMapper(), entity.getId()));
		if (updatedEntity.isEmpty()) {
			throw new IllegalArgumentException("Example doesn't updated!");
		}
		return updatedEntity.get();
	}

}
