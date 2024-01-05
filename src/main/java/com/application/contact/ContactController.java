package com.application.contact;

import com.application.exceptions.ContactNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/contact")
public class ContactController {

    private final ContactServiceImpl contactService;


    public ContactController(ContactServiceImpl contactService) {
        this.contactService = contactService;
    }

    @GetMapping
    public List<Contact> getContacts() {
        return contactService.findAll();
    }

    @GetMapping("/{id}")
    public Contact getContactById(@PathVariable String id) {
        if (contactService.existsById(id)) {
            return contactService.findById(id);
        } else {
            throw new ContactNotFoundException(id);
        }
    }

    @PostMapping
    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    public Contact createContact(@RequestBody Contact contact) {
        return contactService.create(contact);
    }


}
