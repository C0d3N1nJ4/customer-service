package com.application.contact;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ContactService {

List<Contact> findAll();

Contact create(Contact contact);

boolean existsById(String id);

Contact findById(String id);

Contact update(Contact contact);

void deleteById(String id);

}
