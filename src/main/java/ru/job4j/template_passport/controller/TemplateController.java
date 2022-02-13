package ru.job4j.template_passport.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.job4j.template_passport.domain.Passport;
import ru.job4j.template_passport.service.PassportService;

import java.util.List;

@RestController
@RequestMapping("/")
public class TemplateController {
    private final PassportService service;

    public TemplateController(PassportService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Passport> save(@RequestBody Passport passport) {
        return new ResponseEntity(service.save(passport), HttpStatus.OK);
    }

    @GetMapping
    public List<Passport> findAll() {
        return service.findAll();
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable int id) {
        service.deleteById(id);
    }

    @GetMapping("/series/{series}")
    public List<Passport> findBySeries(@PathVariable int series) {
        return service.findPassportBySeries(series);
    }

    @GetMapping("/expired")
    public List<Passport> getExpired() {
        return service.findUnavailable();
    }

    @GetMapping("/replaceable")
    public List<Passport> getReplaceable() {
        return service.findReplaceable();
    }
}
