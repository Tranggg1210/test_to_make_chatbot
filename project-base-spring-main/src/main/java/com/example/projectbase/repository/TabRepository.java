package com.example.projectbase.repository;

import com.example.projectbase.domain.entity.Tab;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TabRepository extends JpaRepository<Tab,String> {
    List<Tab> findAllByUserId(String userId);
}
