package com.annoying.dictionary.models;

public interface Entity<K> {
  K getId();

  void setId(K id);
}
