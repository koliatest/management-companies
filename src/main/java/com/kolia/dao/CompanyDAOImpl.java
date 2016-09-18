package com.kolia.dao;

import com.kolia.model.Company;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CompanyDAOImpl implements CompanyDAO {

    @Autowired
    private SessionFactory sessionFactory;

    private Session openSession()
    {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public Company findById(Integer id) {
        return (Company)openSession().get(Company.class, id);
    }

    @Override
    public List<Company> findAll() {
        return (List)openSession().createQuery("from Company").list();
    }

    @Override
    public void create(Company company) {
        openSession().save(company);
    }

    @Override
    public void update(Company company) {
        openSession().update(company);
    }

    @Override
    public void delete(Integer id) {
        openSession().delete(findById(id));
    }
}
