package com.example.javee.DAO;

import java.util.List;

public interface RepositoryDAO<T> {
    public Long insert(T о) throws Exception;
    // Редактирование cущности
    public void update(T o);
    // Удаление сущности
    public void delete(Long Id);
    // Поиск сущности по Id
    public T findById(Long Id);
    // Получение списка сущностей
    public List<T> findAll();
}
