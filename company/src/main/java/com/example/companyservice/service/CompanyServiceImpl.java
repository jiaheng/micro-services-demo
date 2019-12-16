package com.example.companyservice.service;

import com.example.companyservice.model.Company;
import com.example.companyservice.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyServiceImpl implements CompanyService {

    private final CompanyRepository companyRepository;

    @Autowired
    public CompanyServiceImpl(final CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @Override
    public List<Company> listCompany() {
        return null;//companyRepository.listCompany(PageRequest.of(0, 6));
    }

    @Override
    public List<Company> listCompany(final String cursor) {
        return companyRepository.listCompany(cursor, PageRequest.of(0, 6));
    }

}
