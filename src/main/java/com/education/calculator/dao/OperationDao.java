package com.education.calculator.dao;

import com.education.calculator.model.Operation;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class OperationDao {

  public void create(Operation operation) {
    entityManager.persist(operation);
  }

  @SuppressWarnings("unchecked")
  public List<Operation> getAll() {
    return entityManager.createQuery("from Operation").getResultList();
  }

  @PersistenceContext
  private EntityManager entityManager;
}
