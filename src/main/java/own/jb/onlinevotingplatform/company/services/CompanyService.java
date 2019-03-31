package own.jb.onlinevotingplatform.company.services;

import own.jb.onlinevotingplatform.company.entities.Company;

import java.util.List;

public interface CompanyService {

    List<Company> findAll();

    Company findById(Long id);

    void saveCompany(Company company);

    void deleteComapny(Company company);
}
