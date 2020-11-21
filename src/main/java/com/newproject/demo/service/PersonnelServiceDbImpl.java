package com.newproject.demo.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import com.newproject.demo.model.Personnel;
import com.newproject.demo.repository.PersonnelRepository;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Profile("!old")
@Service
@Scope("prototype")
@Slf4j
public class PersonnelServiceDbImpl implements PersonnelService {

    private final PersonnelRepository personnelRepository;

    public PersonnelServiceDbImpl(PersonnelRepository personnelRepository) {
        this.personnelRepository = personnelRepository;
    }

    @Override
    public Personnel getPersonnelById(Long id) {
        return personnelRepository.findById(id).orElse(null);
    }

    @Override
    public List<Personnel> getAllPersonnel() {
        return personnelRepository.findAll();
    }

    @Override
    public boolean removePersonnelById(Long id) {
        personnelRepository.deleteById(id);
        return true;
    }

    @Override
    public Personnel createNewPersonnel(Personnel personnel) {
        log.info("Tworze nowy personel");
        return personnelRepository.save(personnel);
    }

    @Override
    public List<Personnel> createBatchOfPersonnel(List<Personnel> personnels) {
        return personnelRepository.saveAll(personnels);
    }

    @Override
    public Personnel updatePersonnelById(Long id, Personnel personnel) {
        return null;
    }


}
