package com.example.demo.repository;

import java.sql.Date;
import java.util.List;
import java.sql.Time;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Projection;

@Repository
public interface ProjectionRepository extends JpaRepository<Projection, Long> {

	public List<Projection> findByCt(long id);

	public List<Projection> findByCtId(long id);
	
	public List<Projection> findByCtAndDateBetween(long id, String dateStart, String dateEnd);

	List<Projection> findProjectionsByName(String name);

	List<Projection> findProjectionsByNameAndDate(String name, Date date);

    Projection findProjectionById(long id);

	Projection findFirstByNameAndDateAndTime(String name, Date date, Time time);

	List<Projection> findByCtAndDateLike(long id, String date);
}
