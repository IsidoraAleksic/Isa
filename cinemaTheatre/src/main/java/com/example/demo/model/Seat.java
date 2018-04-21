package com.example.demo.model;

import javax.persistence.*;

@Entity(name = "seats")
public class Seat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int row;

    private int col;

    @ManyToOne
    private Hall hall;

    @Enumerated(EnumType.STRING)
    private SeatType seatType;

    @ManyToOne
    private Projection projection;

    public Hall getHall() {
        return hall;
    }

    public void setHall(Hall hall) {
        this.hall = hall;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Projection getProjection() {
        return projection;
    }

    public void setProjection(Projection projection) {
        this.projection = projection;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public Seat(Long id, int row, int col, SeatType seatType) {
        this.id = id;
        this.row = row;
        this.col = col;
        this.seatType = seatType;
    }

    public Seat(int row, int col, SeatType seatType, Hall h, Projection p) {
        this.row = row;
        this.col = col;
        this.seatType = seatType;
        this.hall = h;
        this.projection = p;
    }
    
    public Seat() {

    }

    public SeatType getSeatType() {
        return seatType;
    }

    public void setSeatType(SeatType seatType) {
        this.seatType = seatType;
    }


}
