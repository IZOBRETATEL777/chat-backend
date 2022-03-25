package com.izobretatel777.chat.dao.repo;

import com.izobretatel777.chat.dao.entity.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContactRepo extends JpaRepository<Contact, Long> {

    @Query(value = "select contacts.id from contacts join users u on u.id = contacts.owner_id where owner_id = ?1",
            nativeQuery = true)
    List<Long> findIdByOwnerId(Long id);

    @Query(value = "select contacts.id, contacts.login, contacts.name, contacts.phone_number, contacts.surname, contacts.owner_id " +
            "from contacts join users u on u.id = contacts.owner_id where contacts.id = ?1 and owner_id = ?2",
            nativeQuery = true)
    Contact findByIdAndOwnerId(Long contactId, Long ownerId);
}
