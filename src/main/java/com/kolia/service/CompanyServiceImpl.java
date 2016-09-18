package com.kolia.service;

import com.kolia.dao.CompanyDAO;
import com.kolia.model.Company;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CompanyServiceImpl implements CompanyService {

    @Autowired
    CompanyDAO companyDAO;

    @Override
    public Company findById(Integer id) {
        return companyDAO.findById(id);
    }

    @Override
    public List<Company> findAll() {
        return companyDAO.findAll();
    }

    @Override
    public void create(Company company) {
        companyDAO.create(company);
    }

    @Override
    public void update(Company company) {
        companyDAO.update(company);
    }

    @Override
    public void delete(Integer id) {
        companyDAO.delete(id);
    }
}
