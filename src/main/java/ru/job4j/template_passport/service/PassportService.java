package ru.job4j.template_passport.service;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.job4j.template_passport.domain.Passport;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class PassportService {
    private static final String URL = "http://localhost:8080/passport/";

    private RestTemplate template;

    public PassportService(RestTemplate template) {
        this.template = template;
    }

    public Passport save(Passport passport) {
        return template.postForObject(URL+"save", passport, Passport.class);
    }

    private List<Passport> getList(String url) {
        List<Passport> body = template.exchange(
                url, HttpMethod.GET, null,
                new ParameterizedTypeReference<List<Passport>>() {
                }
        ).getBody();
        return body == null ? Collections.emptyList() : body;
    }


    public List<Passport> findAll() {
       return getList(URL);
    }

    public void deleteById(int id) {
        template.delete(URL+"delete/" + id);
    }

    public List<Passport> findPassportBySeries(int series) {
        return getList(URL + "series/" + series);

    }

    public List<Passport> findUnavailable() {
        return getList(URL + "expired");
    }

    public List<Passport> findReplaceable() {
        return getList(URL + "replaceable");
    }
}
