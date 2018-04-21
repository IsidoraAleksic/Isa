package com.example.demo.repository;

import java.util.Date;
import java.util.List;
import java.sql.Time;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Projection;

@Repository
public interface ProjectionRepository extends JpaRepository<Projection, Long> {

	public List<Projection> findByCt(long id);

	public List<Projection> findByCtId(long id);

	List<Projection> findProjectionsByName(String name);

	List<Projection> findProjectionsByNameAndDate(String name, Date date);

	Projection findProjectionById(long id);

	Projection findFirstByNameAndDateAndTime(String name, Date date, Time time);

	List<Projection> findByCtIdAndDateBetween(long id, Date dateStart, Date dateEnd);

	List<Projection> findByCtIdAndDate(long id, Date date);
	

	List<Projection> findByDateBetween(Date dateStart, Date dateEnd);

	List<Projection> findByDate(Date date);
}
