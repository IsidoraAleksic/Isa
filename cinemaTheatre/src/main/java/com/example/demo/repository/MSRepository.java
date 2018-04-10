package com.example.demo.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.MovieShow;


@Repository
public interface MSRepository extends JpaRepository<MovieShow, Long>{

	public MovieShow findById(long id);
	
}
