package com.github.zurcariem.mybatisdemo.repo;

import com.github.zurcariem.mybatisdemo.model.Contact;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

/**
 * Interface for CRUD operations on a repository for a Contact type
 * 
 * @author henrique
 */
@Component
interface ContactRepositoryCrud extends CrudRepository<Contact, Integer> {
}
