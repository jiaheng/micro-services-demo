package com.example.companyservice.controller;

import com.example.companyservice.model.Company;
import com.example.companyservice.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/")
public class HomeController {

    private final CompanyService companyService;

    @Autowired
    public HomeController(final CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping("/companies")
    public List<Company> getCompanies(
            @RequestParam(value = "cursor", required = false) final String cursor) {
        return companyService.listCompany(cursor);
    }
}
