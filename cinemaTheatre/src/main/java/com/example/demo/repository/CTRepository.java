package com.example.demo.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.model.CTType;
import com.example.demo.model.CinemaTheater;

@Repository
public interface CTRepository extends JpaRepository<CinemaTheater, Long> {

	public Page<CinemaTheater> getCinemaTheaterByType(CTType theater, Pageable pageable);

}
