package com.codegym.service;

import com.codegym.model.Staff;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface StaffService {
Page<Staff> findAll(Pageable pageable);

Optional<Staff> findById(Long id);

void save(Staff staff);

void delete(Long id);

Page<Staff> findByName(String name, Pageable pageable);
}
