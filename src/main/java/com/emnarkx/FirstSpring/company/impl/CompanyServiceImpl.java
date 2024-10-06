package com.emnarkx.FirstSpring.company.impl;

import com.emnarkx.FirstSpring.company.Company;
import com.emnarkx.FirstSpring.company.CompanyRepository;
import com.emnarkx.FirstSpring.company.CompanyService;
import com.emnarkx.FirstSpring.job.Job;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyServiceImpl implements CompanyService {

    private final CompanyRepository companyRepository;

    public CompanyServiceImpl(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @Override
    public List<Company> getAllompanies() {
        return companyRepository.findAll();
    }

    @Override
    public boolean updateCompany(Company newCompany, Long id) {
        Optional<Company> companyOptional = companyRepository.findById(id);

        if (companyOptional.isPresent()) {
            Company company = companyOptional.get();
            company.setName(newCompany.getName());
            company.setDescription(newCompany.getDescription());
            company.setJobs(newCompany.getJobs());
            companyRepository.save(company);
            return true;
        }
        return false;
    }

    @Override
    public void createCompany(Company company) {
        companyRepository.save(company);
    }

    @Override
    public void deleteCompany(Long id) {
        companyRepository.deleteById(id);
    }

    @Override
    public Company getCompanyById(Long id) {
        return companyRepository.findById(id).orElse(null);
    }


}
