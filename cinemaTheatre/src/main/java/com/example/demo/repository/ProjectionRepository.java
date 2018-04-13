package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.example.demo.model.Projection;

@Component
public interface ProjectionRepository extends JpaRepository<Projection, Long>{

	
	
}
