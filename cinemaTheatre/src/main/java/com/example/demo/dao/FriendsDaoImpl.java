package com.example.demo.dao;

import com.example.demo.model.Friends;
import com.example.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Service;

@Service
public class FriendsDaoImpl implements FriendsDao{

        private final String INSERT_SQL = "INSERT INTO FRIENDS(user_one_id, user_two_id, status) values(:userOne,:userTwo,:status)";
        private final String UPDATE_SQL = "UPDATE FRIENDS SET status=:status where user_one_id = :userOne and user_two_id = :userTwo";

        @Autowired
        private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

        @Override
        public boolean create(final User userOne, User userTwo, int status) {
            KeyHolder holder = new GeneratedKeyHolder();
            SqlParameterSource parameters = new MapSqlParameterSource()
                    .addValue("userOne", userOne.getId())
                    .addValue("userTwo", userTwo.getId())
                    .addValue("status", status);
            namedParameterJdbcTemplate.update(INSERT_SQL, parameters, holder);
           // friends.setId(holder.getKey().intValue());
            return true;
        }

    @Override
    public boolean update(User userOne, User userTwo, int status) {
        KeyHolder holder = new GeneratedKeyHolder();
        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("userOne", userOne.getId())
                .addValue("userTwo", userTwo.getId())
                .addValue("status", status);
        namedParameterJdbcTemplate.update(UPDATE_SQL, parameters, holder);
        // friends.setId(holder.getKey().intValue());
        return true;
    }


}
