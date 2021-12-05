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
import org.springframework.stereotype.Repository;

import com.annoying.dictionary.dao.AbstractCrudDao;
import com.annoying.dictionary.dao.WordDao;
import com.annoying.dictionary.mappers.WordMapper;
import com.annoying.dictionary.models.Word;

@Repository
public class WordDaoImpl extends AbstractCrudDao<Word, Long> implements WordDao {

  private final JdbcTemplate jdbcTemplate;

  private final String FIND_BY_ID = "SELECT * FROM words WHERE word_id = ?";
  private final String FIND_ALL = "SELECT * FROM words";
  private final String DELETE_BY_ID = "DELETE FROM words WHERE word_id = ?";
  private final String CREATE =
      "INSERT INTO words (signature, translation, transcription) VALUES (?,?,?)";
  private final String UPDATE =
      "UPDATE words SET signature = ?, translation = ?, transcription = ? WHERE word_id = ?";

  @Autowired
  WordDaoImpl(DataSource dataSourse) {
    this.jdbcTemplate = new JdbcTemplate(dataSourse);
  }

  @Override
  public Optional<Word> findById(Long id) {
    Optional<Word> entity =
        Optional.ofNullable(jdbcTemplate.queryForObject(FIND_BY_ID, new WordMapper()));
    if (entity.isEmpty()) {
      return Optional.empty();
    }
    return entity;
  }

  @Override
  public List<Word> findAll() {
    return jdbcTemplate.query(FIND_ALL, new WordMapper());
  }

  @Override
  public void deleteById(Long id) {
    boolean isSucceeded = jdbcTemplate.update(DELETE_BY_ID, id) > 0;
    if (!isSucceeded) {
      throw new IllegalArgumentException("Word with id(" + id + ") doesn't exist");
    }
  }

  @Override
  protected Word create(Word entity) {
    KeyHolder keyHolder = new GeneratedKeyHolder();
    jdbcTemplate.update(new PreparedStatementCreator() {
      @Override
      public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
        PreparedStatement ps = con.prepareStatement(CREATE, new String[] {"word_id"});
        ps.setString(1, entity.getSignature());
        ps.setString(2, entity.getTranslation());
        ps.setString(3, entity.getTranscription());
        return ps;
      }
    }, keyHolder);
    return new Word(keyHolder.getKey().longValue(), entity.getSignature(), entity.getTranslation(),
        entity.getTranscription());
  }

  @Override
  protected Word update(Word entity) {
    jdbcTemplate.update(UPDATE, entity.getSignature(), entity.getTranslation(),
        entity.getTranscription(), entity.getId());
    Optional<Word> updatedEntity = Optional
        .ofNullable(jdbcTemplate.queryForObject(FIND_BY_ID, new WordMapper(), entity.getId()));
    if (updatedEntity.isEmpty()) {
      throw new IllegalArgumentException("Example doesn't updated!");
    }
    return updatedEntity.get();
  }

}
