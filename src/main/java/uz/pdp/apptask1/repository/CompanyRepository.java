package uz.pdp.apptask1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.apptask1.entity.Company;

public interface CompanyRepository extends JpaRepository<Company,Integer> {
    boolean existsByName(String name);
    boolean existsByNameAndIdNot(String name,Integer id);
}
