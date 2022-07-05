package com.example.demo.controller;

import com.example.demo.model.House;
import com.example.demo.service.impl.HouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping ("/houses")
public class HouseController {
    @Autowired
    HouseService houseService;
    @GetMapping
    public ResponseEntity<Iterable<House>> findAllHouse() {
        List<House> houses = (List<House>) houseService.findAll();
        if (houses.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(houses, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<House> findHouseById(@PathVariable Long id) {
        Optional<House> houseOptional = houseService.findById(id);
        if (!houseOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(houseOptional.get(), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<House> update(@RequestParam("fileEdit") MultipartFile file, @PathVariable Long id, House house) {
        Optional<House> houseOptional = houseService.findById(id);
        if(!houseOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        String fileName = file.getOriginalFilename();
        house.setImage(fileName);
        try {
            file.transferTo(new File("D:\\demo1\\src\\main\\resources\\templates\\image\\" + fileName));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        house.setId(houseOptional.get().getId());
        houseService.save(house);
        return new ResponseEntity<>(house, HttpStatus.CREATED);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<House> deleteHouse(@PathVariable Long id) {
        Optional<House> houseOptional = houseService.findById(id);
        if (!houseOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        houseService.remove(id);
        return new ResponseEntity<>(houseOptional.get(), HttpStatus.NO_CONTENT);
    }
    @PostMapping
    public ResponseEntity<House> handleFileUpload (@RequestParam("file") MultipartFile file, House house) {
        String fileName = file.getOriginalFilename();
        house.setImage(fileName);
        try {
            file.transferTo(new File("D:\\demo1\\src\\main\\resources\\templates\\image\\" + fileName));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        return new ResponseEntity<>(houseService.save(house),HttpStatus.CREATED);
    }

    @GetMapping("/search-name")
    public ResponseEntity<Iterable<House>> findAllByNameContaining(@RequestParam String name) {
        return new ResponseEntity<>(houseService.findAllByNameContaining(name), HttpStatus.OK);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }
}