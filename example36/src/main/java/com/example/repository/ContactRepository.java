package com.example.repository;

import com.example.model.Contact;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

/*
@Repository stereotype annotation is used to add a bean of this class
type to the Spring context and indicate that given Bean is used to perform
DB related operations
* */
@Repository
public interface ContactRepository extends JpaRepository<Contact, Integer> {

    //@Query(value = "SELECT * FROM contact_msg WHERE status = ?1", nativeQuery = true) // Native query
    List<Contact> findByStatus(String status);

    @Query("SELECT c FROM Contact c WHERE c.status = :status")  // JPQL(Java Persistence Query Language) query
    Page<Contact> findByStatusWithQuery(@Param("status") String status, Pageable pageable);

    @Transactional  // Creating a transaction while trying to update
    @Modifying // claims that data inside database will be modified(inserted, updated or deleted)
    @Query("UPDATE Contact c SET c.status = ?1 WHERE c.contactId = ?2")
    int updateStatusById(String status, int id);

    Page<Contact> findOpenMessages(@Param("status") String status, Pageable pageable);  // NamedQuery inside Contact class
    //NamedQuery does NOT support sorting

    @Transactional
    @Modifying
    int updateMessageStatus(String status, int id); // NamedQuery inside Contact class

    @Query(nativeQuery = true)
    Page<Contact> findOpenMessagesNative(@Param("status") String status, Pageable pageable);

    @Transactional
    @Modifying
    @Query(nativeQuery = true)
    int updateMessageStatusNative(String status, int id);


}

