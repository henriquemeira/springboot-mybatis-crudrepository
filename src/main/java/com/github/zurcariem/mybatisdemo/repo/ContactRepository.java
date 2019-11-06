package com.github.zurcariem.mybatisdemo.repo;

import com.github.zurcariem.mybatisdemo.mapper.ContactMapper;
import com.github.zurcariem.mybatisdemo.model.Contact;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository implementation class that exposes CRUD operations from 
 * ContactRepositoryCrud Interface and add methods not permited in 
 * CrudRepository Interface wihtout @Query annotation.
 * 
 * The additional methods can use either Mybatis or JdbcTemplate/CrudRepository 
 * operations, no matters.
 * 
 * This is the exposed Repository Class for the application.
 */
@Repository
public class ContactRepository implements CrudRepository<Contact, Integer> {

    @Autowired
    private ContactRepositoryCrud contactCRUDRepo;
    
    @Autowired
    private ContactMapper contactMapper;
    
    public Contact getByFullname(String fullname){
        return contactMapper.findByName(fullname);
    }
    
    
    public boolean existsByFullname(String fullname) {
        return contactMapper.existsByFullname(fullname);
    }
    
    public long countContacts() {
        return contactMapper.count();
    }
    
    @Override
    public <S extends Contact> S save(S entity) {
        return contactCRUDRepo.save(entity);
    }

    @Override
    public <S extends Contact> Iterable<S> saveAll(Iterable<S> entities) {
        return contactCRUDRepo.saveAll(entities);
    }

    @Override
    public Optional<Contact> findById(Integer id) {
        return contactCRUDRepo.findById(id);
    }

    @Override
    public boolean existsById(Integer id) {
        return contactCRUDRepo.existsById(id);
    }

    @Override
    public Iterable<Contact> findAll() {
        return contactCRUDRepo.findAll();
    }

    @Override
    public Iterable<Contact> findAllById(Iterable<Integer> ids) {
        return contactCRUDRepo.findAllById(ids);
    }

    @Override
    public long count() {
        return contactCRUDRepo.count();
    }

    @Override
    public void deleteById(Integer id) {
        contactCRUDRepo.deleteById(id);
    }

    @Override
    public void delete(Contact entity) {
        contactCRUDRepo.delete(entity);
    }

    @Override
    public void deleteAll(Iterable<? extends Contact> entities) {
        contactCRUDRepo.deleteAll(entities);
    }

    @Override
    public void deleteAll() {
        contactCRUDRepo.deleteAll();
    }
    
}
