package com.example.api.repository;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.api.entity.BaseEntity;

@Service
public interface BaseRepository<T extends BaseEntity> {
  
  public List<T> findAll();

  public T save(T entity);

  public T update(T entity);

  public T findById(int id);

  public void deleteById(int id);
}



