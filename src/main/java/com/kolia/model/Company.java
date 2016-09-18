package com.kolia.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Table(name = "company")
@Entity
public class Company {

    @GeneratedValue
    @Id
    @Column
    private Integer id;

    @Column
    private String name;

    @Column
    private Double earnings;

    @OneToMany(mappedBy = "parentCompany", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JsonBackReference
    private List<Company> childCompanies = new ArrayList<Company>();

    @ManyToOne
    @JoinColumn(name = "parent_id")
    @JsonManagedReference
    @JsonProperty("parent")
    private Company parentCompany;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getEarnings() {
        return earnings;
    }

    public void setEarnings(Double earnings) {
        this.earnings = earnings;
    }

    public List<Company> getChildCompanies() {
        return childCompanies;
    }

    public void setChildCompanies(List<Company> childCompanies) {
        this.childCompanies = childCompanies;
    }

    public Company getParentCompany() {
        return parentCompany;
    }

    public void setParentCompany(Company childCompany) {
        this.parentCompany = childCompany;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
