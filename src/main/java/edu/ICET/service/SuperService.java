package edu.ICET.service;

import java.util.List;

public interface SuperService<T> {
    boolean save(T t);
    T search(Long id);
    boolean update(T t);
    boolean delete(Long id);
    List<T> getAll();
}