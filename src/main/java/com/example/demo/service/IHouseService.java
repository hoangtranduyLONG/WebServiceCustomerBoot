package com.example.demo.service;

import com.example.demo.model.House;


public interface IHouseService extends IGeneralService<House> {
    Iterable<House> findAllByNameContaining(String name);
}