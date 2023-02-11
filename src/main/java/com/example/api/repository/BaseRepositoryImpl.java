package com.example.api.repository;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;

import com.example.api.entity.BaseEntity;


//Devuewlve CRUD Generico
interface BaseGenericoRepository<T extends BaseEntity> extends CrudRepository<T,Integer> {
  
}
public class BaseRepositoryImpl<T extends BaseEntity>  implements BaseRepository<T> {

  @Autowired
  private BaseGenericoRepository<T> repo;

  @Override
  public List<T> findAll(){
    return  (List<T>) this.repo.findAll();
  }
  @Override
  public T save(T entity) {
    return this.repo.save(entity); 
  }
  @Override
  public T update(T entity) {
    return this.repo.save(entity); 
  }
  @Override
  public T findById(int id){
    return this.repo.findById(id).get();
  }
  @Override
  public void deleteById(int id){
    this.repo.deleteById(id);
  }

  


  
}
