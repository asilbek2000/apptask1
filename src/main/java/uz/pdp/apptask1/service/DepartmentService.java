package uz.pdp.apptask1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.apptask1.entity.Company;
import uz.pdp.apptask1.entity.Department;
import uz.pdp.apptask1.payload.Apiresponse;
import uz.pdp.apptask1.payload.DepartmentDto;
import uz.pdp.apptask1.repository.CompanyRepository;
import uz.pdp.apptask1.repository.DepartmentRepository;

import java.util.List;

@Service
public class DepartmentService {
    @Autowired
    DepartmentRepository departmentRepository;
    @Autowired
    CompanyRepository companyRepository;

    public List<Department> getAll(){
        List<Department> departments = departmentRepository.findAll();
    return departments;
    }


    public Apiresponse addDepartment(DepartmentDto dto){
        if (departmentRepository.existsByNameAndCompany(dto.getName(),
                companyRepository.findById(dto.getCompanyId()).get())) {
            return new Apiresponse("Department with this name in this company already exist",
                    false);
        }
        if (companyRepository.existsById(dto.getCompanyId())) {
            Company company = companyRepository.findById(dto.getCompanyId()).get();
            Department department =new Department();
          department.setName(dto.getName());
          department.setCompany(company);
            Department save = departmentRepository.save(department);
            return new Apiresponse("added successfully",true);
        }


        return new Apiresponse("company not found with this id",false);

    }
    public Apiresponse editDepartMent(Integer id,DepartmentDto dto){
        if (departmentRepository.existsByNameAndCompany(dto.getName(),
                companyRepository.findById(dto.getCompanyId()).get())) {
            return new Apiresponse("Department with this name in this company already exist",
                    false);
        }
        if (departmentRepository.existsById(id)) {
            Company company = companyRepository.findById(dto.getCompanyId()).get();
            Department department = departmentRepository.findById(id).get();
            department.setCompany(company);
            department.setName(dto.getName());
            departmentRepository.save(department);

            return new Apiresponse("Edited",true);
        }
        return new Apiresponse("Not found",false);
    }
    public Apiresponse deleteDepartment(Integer id){
        if (departmentRepository.existsById(id)) {
            departmentRepository.deleteById(id);
            return new Apiresponse("Deleted",true);
        }
        return new Apiresponse("Not found",false);

    }

    public Apiresponse getDepartmentById(Integer id){
        if (departmentRepository.existsById(id)) {
            Department department = departmentRepository.findById(id).get();
            return new Apiresponse("department",true,department);
        }
        return new Apiresponse("not found",false);
    }
}
