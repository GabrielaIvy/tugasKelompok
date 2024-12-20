package com.example.manpro.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class JdbcUserRepository implements UserRepository{
    
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public boolean authenticateUser(String username, String passwords){
        String sql = "SELECT * FROM pengguna WHERE username=? AND passwords=?";
        List<User> user = jdbcTemplate.query(sql, new Object[]{username, passwords}, this::mapRowToUser);
        if(user == null || user.isEmpty()){
            return false;
        }else{
            return true;
        }
    }

    private User mapRowToUser(ResultSet resultSet, int rowNum) throws SQLException{
        return new User(
            resultSet.getInt("id"),
            resultSet.getString("nama"),
            resultSet.getString("username"),
            resultSet.getString("passwords"),
            resultSet.getString("roles"),
            resultSet.getString("alamat"),
            resultSet.getString("noHP"),
            resultSet.getString("email")
        );
    }

    @Override
    public String getUserRole(String username){
        String sql = "SELECT roles FROM pengguna WHERE username=?";
        return jdbcTemplate.queryForObject(sql, String.class, username);
    }

    @Override
    public List<Kecamatan> findAllKecamatan(){
        String sql = "SELECT * FROM kecamatan";
        return jdbcTemplate.query(sql, this::mapRowToKecamatan);
    }

    private Kecamatan mapRowToKecamatan(ResultSet resultSet, int rowNum) throws SQLException{
        return new Kecamatan(
            resultSet.getInt("id"),
            resultSet.getString("nama")
        );
    }

    @Override
    public List<Kelurahan> findAllKelurahan(){
        String sql = "SELECT * FROM kelurahan";
        return jdbcTemplate.query(sql, this::mapRowToKelurahan);
    }

    @Override
    public List<Kelurahan> findByKecamatan(Integer idKecamatan){
        String sql = "SELECT * FROM kecamatankelurahan WHERE idKecamatan=?";
        return jdbcTemplate.query(sql, new Object[]{idKecamatan}, this::mapRowToKelurahan);
    }

    private Kelurahan mapRowToKelurahan(ResultSet resultSet, int rowNum) throws SQLException{
        return new Kelurahan(
            resultSet.getInt("id"),
            resultSet.getString("nama"),
            resultSet.getInt("idKecamatan")
        );
    }
}
