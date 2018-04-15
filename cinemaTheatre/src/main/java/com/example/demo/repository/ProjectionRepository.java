package com.example.demo.repository;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Projection;

@Repository
public interface ProjectionRepository extends JpaRepository<Projection, Long> {

	public List<Projection> findByCt(long id);

	public List<Projection> findByCtAndDate(long id, Date date);

}
