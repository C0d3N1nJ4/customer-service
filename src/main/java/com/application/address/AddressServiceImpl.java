package com.application.address;

import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AddressServiceImpl implements AddressService {

        private final AddressRepository addressRepository;

        public AddressServiceImpl(AddressRepository addressRepository) {
            this.addressRepository = addressRepository;
        }


    public Optional<Address> findById(String id) {
       return addressRepository.findById(id);
    }

    @Override
    public Address create(Address address) {
        return addressRepository.save(address);
    }

    public Iterable<Address> getAddressByCity(String city) {
        return addressRepository.findAddressByCity(city);
    }

    @Override
    public boolean existsById(String id) {
        return addressRepository.existsById(id);
    }
}
