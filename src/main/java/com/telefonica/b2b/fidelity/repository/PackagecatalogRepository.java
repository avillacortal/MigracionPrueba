package com.telefonica.b2b.fidelity.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.telefonica.b2b.fidelity.entity.Packagecatalog;

@Repository
public interface PackagecatalogRepository extends CrudRepository<Packagecatalog, Serializable> {

    public List<Packagecatalog> findById(String id);

}
