package com.kolia.service;

import com.kolia.model.Company;

import java.util.List;

public interface CompanyService {

    Company findById(final Integer id);

    List<Company> findAll();

    void create(final Company company);

    void update(final Company company);

    void delete(final Integer id);
}
