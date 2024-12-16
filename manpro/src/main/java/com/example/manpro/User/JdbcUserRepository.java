package com.example.manpro.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class JdbcUserRepository implements UserRepository{
    
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public User authenticateUser(String username, String passwords){
        String sql = "SELECT * FROM pengguna WHERE username=? AND passwords=?";
        List<User> user = jdbcTemplate.query(sql, this::mapRowToUser, username, passwords);
        if(user == null || user.isEmpty()){
            return null;
        }else{
            return user.get(0);
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
            resultSet.getString("email"),
            resultSet.getInt("idKelurahan")
        );
    }

    @Override
    public boolean register(User u){
        String sql = "INSERT INTO Pengguna (nama, username, passwords, roles, alamat, noHP, email, idKelurahan) VALUES (?,?,?,?,?,?,?,?)";
        int rowsEffected = jdbcTemplate.update(sql, u.getNama(), u.getUsername(), u.getPasswords(), u.getRoles(), u.getAlamat(), u.getNoHP(), u.getEmail(), u.getIdKelurahan());
        return rowsEffected > 0;
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
        return jdbcTemplate.query(sql, this::mapRowToKelurahan, idKecamatan);
    }

    private Kelurahan mapRowToKelurahan(ResultSet resultSet, int rowNum) throws SQLException{
        return new Kelurahan(
            resultSet.getInt("id"),
            resultSet.getString("nama"),
            resultSet.getInt("idKecamatan")
        );
    }

    @Override
    public User findById(Integer id){
        String sql = "SELECT * FROM pengguna WHERE id=?";
        try{
            User user = jdbcTemplate.queryForObject(sql, this::mapRowToUser, id);
            return user;
        }catch (EmptyResultDataAccessException e){
            return null;
        }
    }

    @Override
    public String[] domilisiUser(Integer id){
        String sql = "SELECT kelurahan, kecamatan FROM domisiliuser WHERE id=?";
        return jdbcTemplate.queryForObject(sql, this::mapRowToDomisili, id); 
    }

    private String[] mapRowToDomisili(ResultSet resultSet, int rowNum) throws SQLException{
        String res[] = new String[2];
        res[0] = resultSet.getString("kelurahan");
        res[1] = resultSet.getString("kecamatan");
        return res;
    }
}
