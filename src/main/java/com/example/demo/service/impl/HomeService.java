package com.example.demo.service.impl;

import com.example.demo.model.Home;
import com.example.demo.repository.IHomeRepository;
import com.example.demo.service.IHomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class HomeService implements IHomeService {
    @Autowired
    private IHomeRepository homeRepository;

    @Override
    public void save(Home home) {
        homeRepository.save(home);
    }

    @Override
    public Page<Home> findAll(Pageable pageable) {
        return homeRepository.findAll(pageable);
    }

    @Override
    public Optional<Home> findById(Long id) {
        return homeRepository.findById(id);
    }

    @Override
    public void remove(Long id) {
        homeRepository.deleteById(id);
    }
}