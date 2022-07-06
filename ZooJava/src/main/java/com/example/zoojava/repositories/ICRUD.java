package com.example.zoojava.repositories;


import java.sql.SQLException;
import java.util.List;

public interface ICRUD<T> {
    T add(T value) throws SQLException;
    T remove(T value) throws SQLException;
    List<T> findAll();
    void removeAll() throws SQLException;
}
