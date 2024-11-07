package edu.ICET.service;

public interface MySuperService<T> {
    boolean save(T t);
    T search(Long id);
    boolean update(T t);
    boolean delete(Long id);
}