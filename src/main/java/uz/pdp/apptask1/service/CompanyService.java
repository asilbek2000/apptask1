package uz.pdp.apptask1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.apptask1.entity.Address;
import uz.pdp.apptask1.entity.Company;
import uz.pdp.apptask1.payload.Apiresponse;
import uz.pdp.apptask1.payload.CompanyDto;
import uz.pdp.apptask1.repository.AddressRepository;
import uz.pdp.apptask1.repository.CompanyRepository;

import java.util.List;

@Service
public class CompanyService {
    @Autowired
    CompanyRepository companyRepository;
    @Autowired
    AddressRepository addressRepository;
    public List<Company> getAll(){
        List<Company> companies = companyRepository.findAll();
        return companies;
    }

    public Apiresponse addCompany(CompanyDto dto){
        if (companyRepository.existsByName(dto.getName())) {
            return new Apiresponse("Company with this name already exist",false);
        }


        if (addressRepository.existsByHomeNumberAndStreet( dto.getHomeNumber(),dto.getStreet()
                )) {
            return new Apiresponse("Company with this address already exist",false);

        }
        Address address =new Address();
        address.setHomeNumber(dto.getHomeNumber());
        address.setStreet(dto.getStreet());
        Address save = addressRepository.save(address);
        Company company =new Company();
        company.setName(dto.getName());
        company.setDirector(dto.getDirector());
        company.setAddress(save);
        companyRepository.save(company);
              return new Apiresponse("Added successfully",true);
    }

    public Apiresponse editCompany(Integer id,CompanyDto dto){
        if (companyRepository.existsByNameAndIdNot(dto.getName(),id)) {
            return new Apiresponse("Company with this name already exist",false);
        }
        if (companyRepository.existsById(id)) {
            Company company = companyRepository.findById(id).get();
            Address address = company.getAddress();
            address.setStreet(dto.getStreet());
            address.setHomeNumber(dto.getHomeNumber());
            Address savedAddress = addressRepository.save(address);
            company.setAddress(savedAddress);
            company.setDirector(dto.getDirector());
            company.setName(dto.getName());
            companyRepository.save(company);
            return new Apiresponse("Edited",true);
        }
        return new Apiresponse("Not found",false);
    }
    public Apiresponse getCompanyById(Integer id){
        if (companyRepository.existsById(id)) {
            Company company = companyRepository.findById(id).get();
            Address address = company.getAddress();
            CompanyDto dto =new CompanyDto(company.getName(),company.getDirector(),
                    address.getStreet(),address.getHomeNumber());
            return new Apiresponse("Company",true,dto);
        }
        return new Apiresponse("Not found ",false);
    }
    public Apiresponse deleteCompany(Integer id){
        if (companyRepository.existsById(id)) {
            companyRepository.deleteById(id);
            return new Apiresponse("Deleted",true);
        }
        return new Apiresponse("Not found",false);
    }
}
