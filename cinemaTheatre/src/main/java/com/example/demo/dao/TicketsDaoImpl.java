package com.example.demo.dao;

import com.example.demo.model.Projection;
import com.example.demo.model.Seat;
import com.example.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Service;

@Service
public class TicketsDaoImpl implements TicketsDao {

    private final String INSERT_SQL = "INSERT INTO TICKET(discount, status, seat_id,projection_id) values(:discount,:status,:seat,:projection)";
//    private final String UPDATE_SQL = "UPDATE TICKETS SET status=:status where user_one_id = :userOne and user_two_id = :userTwo";

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public boolean create(short discount, int status, Seat seat, Projection projection) {
        KeyHolder holder = new GeneratedKeyHolder();
        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("discount", discount)
                .addValue("status", status)
                .addValue("seat", seat.getId())
//                .addValue("seat", version)
                .addValue("projection",projection.getId());
        namedParameterJdbcTemplate.update(INSERT_SQL, parameters, holder);
        // friends.setId(holder.getKey().intValue());
        return true;
    }

//    @Override
//    public boolean update(short discount, int status, Seat seat, Projection projection) {
//        return false;
//    }
}
