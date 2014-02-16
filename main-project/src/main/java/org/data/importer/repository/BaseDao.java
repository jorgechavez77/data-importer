package org.data.importer.repository;

public interface BaseDao<T> {

	T findById(Long id);

	void save(T t);

}
