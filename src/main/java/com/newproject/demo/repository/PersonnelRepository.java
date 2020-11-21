package com.newproject.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.newproject.demo.model.Personnel;

@Repository
public interface PersonnelRepository extends JpaRepository<Personnel, Long> {
}