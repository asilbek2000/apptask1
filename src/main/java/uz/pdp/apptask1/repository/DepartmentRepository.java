package uz.pdp.apptask1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.apptask1.entity.Company;
import uz.pdp.apptask1.entity.Department;

public interface DepartmentRepository extends JpaRepository<Department,Integer> {
boolean existsByNameAndCompany(String name,Company company);
}
