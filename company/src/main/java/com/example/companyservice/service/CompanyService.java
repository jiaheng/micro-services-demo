package com.example.companyservice.service;

import com.example.companyservice.model.Company;

import java.util.List;

public interface CompanyService {

    List<Company> listCompany();

    List<Company> listCompany(String cursor);

}
