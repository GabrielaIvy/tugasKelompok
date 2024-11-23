package com.example.manpro.User;

import lombok.Data;

@Data
public class User {
    private final int id;
    private final String nama;
    private final String username;
    private final String passwords;
    private final String roles;
    private final String alamat;
    private final String noHP;
    private final String email;
}
