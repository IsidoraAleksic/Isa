package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Hall;

@Repository
public interface HallRepository extends JpaRepository<Hall ,Long>{

	Hall findById(long id);

	
}
