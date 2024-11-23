package com.example.manpro.User;

import java.util.List;

public interface UserRepository{
    boolean authenticateUser(String username, String passwords);
    String getUserRole(String username);
    List<Kecamatan> findAllKecamatan();
    List<Kelurahan> findAllKelurahan();
    List<Kelurahan> findByKecamatan(Integer idKecamatan);
}
