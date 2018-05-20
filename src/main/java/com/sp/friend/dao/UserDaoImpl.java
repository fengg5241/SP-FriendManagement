package com.sp.friend.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import com.sp.friend.constant.SPConstant;
import com.sp.friend.model.User;

@Component
public class UserDaoImpl implements UserDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	
	
	@Override
	public User retrieve(String email) {
		String sql = "select * from " + SPConstant.TABLE_USER + " where email = ?";
		User user = null;
		try {
			user = (User) jdbcTemplate.queryForObject(sql, new Object[] { email },
					new BeanPropertyRowMapper(User.class));
		} catch (Exception e) {

		}
		return user;
	}

	@Override
	public int save(String email) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		String sql = "insert into sp_user(email) values(?)";
		jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection con)
                    throws SQLException {
                PreparedStatement ps = con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, email);
                return ps;
            }}, keyHolder);
		return keyHolder.getKey().intValue();
	}

}
