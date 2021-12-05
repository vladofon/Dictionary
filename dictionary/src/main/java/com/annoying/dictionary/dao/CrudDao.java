package com.annoying.dictionary.dao;

import java.util.List;
import java.util.Optional;

import com.annoying.dictionary.models.Entity;

public interface CrudDao<T extends Entity<K>, K> {

	Optional<T> findById(K id);

	List<T> findAll();

	T save(T entity);

	void deleteById(K id);
}
