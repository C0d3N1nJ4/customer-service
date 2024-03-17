package com.application.contact;

import com.application.address.Address;
import com.application.exceptions.ContactNotFoundException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
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
    @Operation(summary = "Retrieve contacts", responses = {
            @ApiResponse(description = "Successful Operation",
                    responseCode = "200",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Contact.class)))
    })
    public List<Contact> getContacts() {
        return contactService.findAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Retrieve contact by id", responses = {
            @ApiResponse(description = "Successful Operation",
                    responseCode = "200",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Contact.class)))
    })
    public Contact getContactById(@PathVariable String id) {
        if (contactService.existsById(id)) {
            return contactService.findById(id);
        } else {
            throw new ContactNotFoundException(id);
        }
    }

    @PostMapping
    @Operation(summary = "Create a new contact", responses = {
            @ApiResponse(description = "Successful Operation",
                    responseCode = "200",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Contact.class)))
    })
    @ResponseStatus(HttpStatus.CREATED)
    public Contact createContact(@RequestBody Contact contact) {
        return contactService.create(contact);
    }

}
