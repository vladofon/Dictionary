package com.annoying.dictionary.dao;

import com.annoying.dictionary.models.Entity;

public abstract class AbstractCrudDao<T extends Entity<K>, K> implements CrudDao<T, K> {

  @Override
  public T save(T entity) {

    // if id==null then we need to create new row in db otherwise - update existing
    // one
    return entity.getId() == null ? create(entity) : update(entity);
  }

  protected abstract T create(T entity);

  protected abstract T update(T entity);
}
