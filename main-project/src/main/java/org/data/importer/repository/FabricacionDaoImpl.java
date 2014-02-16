package org.data.importer.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.data.importer.entity.Fabricacion;
import org.springframework.stereotype.Repository;

@Repository("fabricacionDao")
public class FabricacionDaoImpl implements FabricacionDao {

	@PersistenceContext(unitName = "simple-jpa")
	private EntityManager entityManager;

	@Override
	public Fabricacion findById(Long id) {
		return this.entityManager.find(Fabricacion.class, id);
	}

	@Override
	public void save(Fabricacion fabricacion) {
		this.entityManager.persist(fabricacion);
	}

}
