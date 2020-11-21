package com.newproject.demo.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.newproject.demo.model.Personnel;
import com.newproject.demo.service.PersonnelService;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping(path = "/hotel/personnel")
@Slf4j
public class PersonnelController {

    private final PersonnelService personnelService;

    // Tu wstrzykujemy potrzebny serwis.
    public PersonnelController(PersonnelService personnelService) {
        this.personnelService = personnelService;
    }

    // Metoda adnotowana jako GetMapping zostanie wywolana na zadanie: localhost:<PORT>/hotel/personnel/<id>, gdzie id to numer pracownika
    @GetMapping("/{id}")
    public ResponseEntity<?> getPersonnelById(@PathVariable Long id) {
        Personnel personnel = personnelService.getPersonnelById(id);
        // Jezeli znalazlo pracownika to zwroc go.
        if (Objects.nonNull(personnel)) {
            return ResponseEntity.ok(personnel);
        }
        // W innym przypadku powiedz ze nie znaleziono
        return ResponseEntity.notFound().build();
    }

    @GetMapping
    public ResponseEntity<List<Personnel>> getPersonnel() {
        return ResponseEntity.ok(personnelService.getAllPersonnel());
    }

    // DeleteMapping powinien sluzyc do usuwania zasobow z serwisu. W tym przypadku jesli sie uda to 202 jesli nie to 400
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePersonnel(@PathVariable Long id) {
        if (personnelService.removePersonnelById(id)) {
            return ResponseEntity.accepted().build();
        }
        return ResponseEntity.badRequest().build();

    }

    // PostMapping powinien sluzyc do tworzenia nowych zasobow. Jedna z metod z ktora mozemy wyslac body.
    @PostMapping("/batch")
    public ResponseEntity<?> createNewPersonnel(@RequestBody List<Personnel> personnels) {
        return ResponseEntity.status(HttpStatus.CREATED).body(personnelService.createBatchOfPersonnel(personnels));
    }

    @PutMapping(path = "{id}")
    public ResponseEntity<Long> updatePersonnelById(@PathVariable Long id, @RequestBody Personnel personnel){
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/personnel/info", method = RequestMethod.PUT)
    public @ResponseBody
    String updatePersonnel(@RequestBody Personnel personnel){
        log.info(personnel.toString());
        return "ok";
    }

}