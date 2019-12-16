package com.example.companyservice.repository;

import com.example.companyservice.model.Company;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompanyRepository extends MongoRepository<Company, String> {

    @Query(value = "{ 'name' : { $gte: ?0 } }", sort = "{ 'name' : 1 }")
    List<Company> listCompany(String cursor, Pageable pageable);

}
