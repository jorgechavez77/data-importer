package org.data.importer.repository;

import java.util.List;

import org.data.importer.entity.Fabricacion;

public interface FabricacionDao extends BaseDao<Fabricacion> {

	List<Fabricacion> findAll();

}
