package com.example.demo.service.impl;

import com.example.demo.model.House;
import com.example.demo.repository.HouseRepository;
import com.example.demo.service.IHouseService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class HouseService implements IHouseService {
    @Autowired
    HouseRepository houseRepository;

    @Override
    public Iterable<House> findAll() {
        return houseRepository.findAll();
    }

    @Override
    public Optional<House> findById(Long id) {
        return houseRepository.findById(id);
    }

    @Override
    public House save(House house) {
        return houseRepository.save(house);
    }

    @Override
    public void remove(Long id) {
        houseRepository.deleteById(id);

    }

    @Override
    public Iterable<House> findAllByNameContaining(String name) {
        return houseRepository.findAllByNameContaining(name);
    }
//    @Override
//    public Iterable<House> findAll() {
//        return houseRepository.findAll();
//    }
//
//    @Override
//    public Optional<House> findById(Long id) {
//        return houseRepository.findById(id);
//    }
//
//    @Override
//    public House save(House house) {
//        return houseRepository.save(house);
//    }
//
//    @Override
//    public void remove(Long id) {
//        houseRepository.deleteById(id);
//    }
//
//    @Override
//    public Iterable<House> findAllByNameContaining(String name) {
//        return houseRepository.findAllByNameContaining(name);
//    }
}