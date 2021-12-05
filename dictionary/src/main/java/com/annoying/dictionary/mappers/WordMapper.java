package com.annoying.dictionary.mappers;

import static com.annoying.dictionary.models.Word.WORD_ID;
import static com.annoying.dictionary.models.Word.WORD_SIGNATURE;
import static com.annoying.dictionary.models.Word.WORD_TRANSCRIPTION;
import static com.annoying.dictionary.models.Word.WORD_TRANSLATION;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.annoying.dictionary.models.Word;

public class WordMapper implements RowMapper<Word> {

  @Override
  public Word mapRow(ResultSet rs, int rowNum) throws SQLException {
    return new Word(rs.getLong(WORD_ID), rs.getString(WORD_SIGNATURE),
        rs.getString(WORD_TRANSLATION), rs.getString(WORD_TRANSCRIPTION));
  }

}
