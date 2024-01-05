package com.application.address;

import com.application.exceptions.AddressNotFoundException;
import com.application.exceptions.CustomerNotFoundException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import java.util.Optional;

@RestController
@RequestMapping("/address")
public class AddressController {

    private final AddressServiceImpl addressService;

    private final AddressRepository addressRepo;

    public AddressController(AddressServiceImpl addressService, AddressRepository addressRepo) {
        this.addressService = addressService;
        this.addressRepo = addressRepo;
    }

    @GetMapping("/{address-id}")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Retrieve an address by it's id", responses = {
            @ApiResponse(description = "Successful Operation", responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Address.class)))
    })
    public Optional<Address> getAddressById(@PathVariable("address-id") String id) {
        if (addressService.existsById(id)) {
            return addressService.findById(id);
        } else {
            throw new AddressNotFoundException(id);
        }
    }

    @PostMapping
    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Create an address", responses = {
            @ApiResponse(description = "Successful Operation", responseCode = "201", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Address.class)))
    })
    public Address createAddress(@RequestBody Address address) {
        return addressService.create(address);
    }

    @GetMapping("/filter/city/{city}")
    @Operation(summary = "Retrieve an address by city", responses = {
            @ApiResponse(description = "Successful Operation", responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Address.class)))
    })
    public Iterable<Address> getAddressByCity(@PathVariable("city") String city) {
        if (city == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST);
        } else {
            return addressService.getAddressByCity(city);
        }
    }
}
