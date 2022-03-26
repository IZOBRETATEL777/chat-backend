package com.izobretatel777.chat.dao.repo;

import com.izobretatel777.chat.dao.entity.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContactRepo extends JpaRepository<Contact, Long> {

    @Query(value = "select contact.id from contact join user u on u.id = contact.owner_id where owner_id = ?1",
            nativeQuery = true)
    List<Long> findIdByOwnerId(Long id);

    @Query(value = "select contact.id, contact.user_id, contact.owner_id " +
            "from contact join user u on u.id = contact.owner_id where contact.id = ?1 and owner_id = ?2",
            nativeQuery = true)
    Contact findByIdAndOwnerId(Long contactId, Long ownerId);
}
