package own.jb.onlinevotingplatform.Service;

import own.jb.onlinevotingplatform.Entities.Company;

import java.util.List;

public interface CompanyService {

    List<Company> findAll();

    Company findById(Long id);

    void saveCompany(Company company);

    void deleteComapny(Company company);
}
