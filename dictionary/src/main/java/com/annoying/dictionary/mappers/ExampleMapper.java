package com.annoying.dictionary.mappers;

import static com.annoying.dictionary.models.Example.EXAMPLE_ID;
import static com.annoying.dictionary.models.Example.EXAMPLE_SIGNATURE;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.annoying.dictionary.models.Example;

public class ExampleMapper implements RowMapper<Example> {

	@Override
	public Example mapRow(ResultSet rs, int rowNum) throws SQLException {
		return new Example(rs.getLong(EXAMPLE_ID), rs.getString(EXAMPLE_SIGNATURE));
	}

}
