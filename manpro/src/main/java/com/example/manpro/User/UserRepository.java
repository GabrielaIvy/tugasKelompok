package com.example.manpro.User;

import java.util.List;

public interface UserRepository{
    User authenticateUser(String username, String passwords);
    boolean register(User user);
    List<Kecamatan> findAllKecamatan();
    List<Kelurahan> findAllKelurahan();
    List<Kelurahan> findByKecamatan(Integer idKecamatan);
}
