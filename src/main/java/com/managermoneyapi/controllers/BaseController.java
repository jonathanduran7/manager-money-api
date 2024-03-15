package com.managermoneyapi.controllers;

import com.managermoneyapi.services.BaseService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Optional;

public abstract class BaseController<T,D,S extends BaseService<T,D>> {
    @Autowired
    protected S service;
    private final Class<T> entityType;

    @SuppressWarnings("unchecked")
    public BaseController() {
        this.entityType = (Class<T>) ((ParameterizedType) getClass()
                .getGenericSuperclass()).getActualTypeArguments()[0];
    }

    @GetMapping
    @ResponseBody
    public List<T> list(){
        return service.findAll();
    }

    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<?> show(@PathVariable Long id) {
        Optional<T> entityOptional = service.findById(id);

        if (entityOptional.isPresent()){
            T entity = entityOptional.get();
            return ResponseEntity.ok(entity);
        } else {
            String entityName = entityType.getSimpleName();
            String errorMessage = entityName + " not found";
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
        }
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity<T> create(@RequestBody @Valid D dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(dto));
    }

    @PutMapping("/{id}")
    @ResponseBody
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody @Valid D dto) {
        Optional<T> entityOptional = service.update(id, dto);

        if (entityOptional.isPresent()) {
            T entity = entityOptional.get();
            return ResponseEntity.ok(entity);
        }

        String entityName = entityType.getSimpleName();
        String errorMessage = entityName + " not found";
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
    }
}
