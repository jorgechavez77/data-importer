package org.data.importer.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

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

	@Override
	public List<Fabricacion> findAll() {
		String q = "SELECT f FROM Fabricacion f";
		Query query = entityManager.createQuery(q);
		@SuppressWarnings("unchecked")
		List<Fabricacion> list = query.getResultList();
		return list != null && !list.isEmpty() ? list : null;
	}

}
