package own.jb.onlinevotingplatform.company.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import own.jb.onlinevotingplatform.company.entities.Company;

@Repository
public interface CompanyRepository extends JpaRepository<Company,Long> {
}
