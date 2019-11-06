package com.github.zurcariem.mybatisdemo;

import com.github.zurcariem.mybatisdemo.model.Contact;
import com.github.zurcariem.mybatisdemo.repo.ContactRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ApplicationTests {

    @Autowired
    private ContactRepository contactRepository;
    
    private static void log(String txt) {
        System.out.println("TEST LOG: " + txt);
    }
    
    /**
     * Test getting all contacts from database.
     * This use CrudRepository method, so, via JdbcTemplate
     */
    @Test
    public void testGetAll() {

        log("testGetAll");
        
        Iterable<Contact> all = contactRepository.findAll();
        Assertions.assertNotNull(all);
        
        all.forEach(contact -> log(contact.getFullname()));
        
    }
    
    /**
     * Test a single select from database, querying by fullname.
     * This method use Mybatis operation. See log.
     */
    @Test
    public void testFindByName() {

        log("testFindByName");
        
        Contact contact = contactRepository.getByFullname("Sarah Connor");
        Assertions.assertNotNull(contact);
        
    }
    
    
    /**
     * Test an UPDATE operation in database.
     * Updates the contact via CrudRepository (JdbcTemplate, see log), 
     * then get data via mybatis.
     */
    @Test
    public void testUpdateContact() {
        
        log("testUpdateContact");

        final String newPhone = "99999999";
        
        Contact contact = contactRepository.getByFullname("Sarah Connor");
        contact.setPhone(newPhone);
        
        contactRepository.save(contact);
        
        Assertions.assertEquals(newPhone, 
                contactRepository.getByFullname("Sarah Connor").getPhone());
        
    }
    
    
    /**
     * This method count how many contacts there are via 
     * CrudRepository (JdbcTemplate) then compare it getting data via Mybatis.
     */
    @Test
    public void testCount() {
        
        log("testCount");
        
        Assertions.assertEquals(
                contactRepository.count(), contactRepository.countContacts());
        
    }
    
    
    /**
     * This method insert a new contact via CrudRepository (JdbcTemplate).
     * Then retrieves ID via mybatis and compare it getting data via 
     * CrudRepository.
     */
    @Test
    public void testInsertContact() {
        
        log("testInsertContact");
        
        Contact contact = new Contact();
        contact.setFullname("Jack Ryan");
        contact.setEmail("ryan@acme.com");
        contact.setPhone("11977665544");
        
        // The object saved will have ID
        contact = contactRepository.save(contact);
        Integer id = contact.getId();
        
        Assertions.assertNotNull(id);
        
        Assertions.assertEquals(
                contactRepository.findById(id).get().getId(), 
                contactRepository.getByFullname("Jack Ryan").getId());
        
    }
    
    
    /**
     * Test DELETE operation via CrudRepository getting data via Mybatis.
     */
    @Test
    public void testDeleteContact() {
        
        log("testDeleteContact");
        
        Contact contact = contactRepository.getByFullname("Randle McMurphy");
        contactRepository.delete(contact);
        
        Assertions.assertNull(contactRepository.getByFullname("Randle McMurphy"));
    }

}
