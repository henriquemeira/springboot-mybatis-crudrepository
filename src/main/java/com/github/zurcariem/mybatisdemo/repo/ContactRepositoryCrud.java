package com.github.zurcariem.mybatisdemo.repo;

import com.github.zurcariem.mybatisdemo.model.Contact;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

/**
 * Interface for CRUD operations on a repository for a Contact class type.
 * 
 * This Interface just extends @org.springframework.data.repository.CrudRepository 
 * for enable CRUD operation via JdbcTemplate.
 */
@Component
interface ContactRepositoryCrud extends CrudRepository<Contact, Integer> {
}
