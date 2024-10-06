package com.emnarkx.FirstSpring.company;

import java.util.List;

public interface CompanyService {
    List<Company> getAllompanies();
    boolean updateCompany(Company company, Long id);
    void createCompany(Company company);
    void deleteCompany(Long id);
    Company getCompanyById(Long id);
}
