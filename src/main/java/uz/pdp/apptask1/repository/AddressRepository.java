package uz.pdp.apptask1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.apptask1.entity.Address;
import uz.pdp.apptask1.entity.Company;

public interface AddressRepository extends JpaRepository<Address,Integer> {
 boolean existsByHomeNumberAndStreet(Integer homeNumber,String street);
}
