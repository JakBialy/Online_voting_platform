package own.jb.onlinevotingplatform.company.services.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import own.jb.onlinevotingplatform.company.entities.Company;
import own.jb.onlinevotingplatform.company.services.CompanyService;
import own.jb.onlinevotingplatform.company.repositories.CompanyRepository;

import java.util.List;

@Service
public class CompanyServiceImpl implements CompanyService {

    private final
    CompanyRepository companyRepository;

    @Autowired
    public CompanyServiceImpl(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @Override
    public List<Company> findAll() {
        return companyRepository.findAll();
    }

    @Override
    public Company findById(Long id) {
        return companyRepository.getOne(id);
    }

    @Override
    public void saveCompany(Company company) {
        companyRepository.save(company);
    }

    @Override
    public void deleteComapny(Company company) {
        companyRepository.delete(company);
    }
}
