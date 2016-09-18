package com.kolia.controller;

import com.kolia.dto.CompanyDTO;
import com.kolia.model.Company;
import com.kolia.service.CompanyService;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class CompanyCRUDController {

    @Autowired
    CompanyService companyService;

    @RequestMapping(value = "/companies", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<List<Company>> getJSON() {
        List<Company> todoItemList = companyService.findAll();

        if(todoItemList.isEmpty()) {
            return new ResponseEntity<List<Company>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Company>>(todoItemList, HttpStatus.OK);
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView getIndex() {

        ModelAndView modelAndView = new ModelAndView("index");
        modelAndView.addObject("companies", companyService.findAll());
        modelAndView.addObject("dto", new CompanyDTO());
        modelAndView.addObject("currentCompany", new Company());

        return modelAndView;
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public String createCompany(@ModelAttribute(value = "dto") CompanyDTO dto,
                                @RequestParam(value = "checked", required = false) boolean checked) {

        if(companyService.findAll().size() == 0) {
            checked = true;
        }
        Company newCompany = new Company();
        newCompany.setName(dto.getName());
        newCompany.setEarnings(dto.getEarnings());
        if(checked == false) {
            newCompany.setParentCompany(companyService.findById(dto.getParent_id()));
        }

        companyService.create(newCompany);

        if(checked == false) {
            companyService.findById(dto.getParent_id()).getChildCompanies().add(newCompany);
        }
        return "redirect:/";
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public ModelAndView selectToUpdate(@PathVariable(value = "id") Integer id) {

        ModelAndView modelAndView = new ModelAndView("index");

        Company company = companyService.findById(id);

        modelAndView.addObject("currentCompany", company);
        modelAndView.addObject("companies", companyService.findAll());
        modelAndView.addObject("dto", new CompanyDTO());

        return modelAndView;
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
    public String Update(@PathVariable(value = "id") Integer id,
                               @ModelAttribute(value = "dto") CompanyDTO dto,
                               @RequestParam(value = "checked", required = false) Boolean checked) {

        Company company = companyService.findById(id);
        company.setName(dto.getName());
        company.setEarnings(dto.getEarnings());

        if(checked == false) {
            company.setParentCompany(companyService.findById(dto.getParent_id()));
        }

        companyService.update(company);

        if(checked == false) {
            companyService.findById(dto.getParent_id()).getChildCompanies().add(company);
        }

        return "redirect:/";
    }
}
