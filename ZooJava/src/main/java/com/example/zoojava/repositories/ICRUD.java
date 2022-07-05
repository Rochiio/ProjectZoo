package com.example.zoojava.repositories;

import java.util.List;

public interface ICRUD<T> {
    T add(T value);
    T remove(T value);
    List<T> findAll();
    void removeAll();
}
