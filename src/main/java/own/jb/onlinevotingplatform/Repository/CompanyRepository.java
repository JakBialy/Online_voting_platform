package own.jb.onlinevotingplatform.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import own.jb.onlinevotingplatform.Entities.Company;

@Repository
public interface CompanyRepository extends JpaRepository<Company,Long> {
}
