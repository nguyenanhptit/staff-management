package com.codegym.service;

import com.codegym.model.Group;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface GroupService {
Page<Group> findAll(Pageable pageable);

Optional<Group> findById(Long id);

void save (Group group);

void delete(Long id);
}
