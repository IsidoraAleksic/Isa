package com.example.demo.repository;

import com.example.demo.model.Hall;
import com.example.demo.model.Projection;
import com.example.demo.model.Seat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface SeatRepository extends JpaRepository<Seat, Long> {

    Seat findByRowAndCol(int row, int col);
    List<Seat> findByHall(Hall hall);
    List<Seat> findByProjectionAndHall(Projection projection, Hall hall);
    Seat findSeatById(Long id);


}
