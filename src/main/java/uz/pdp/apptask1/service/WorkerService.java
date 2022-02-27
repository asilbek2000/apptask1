package uz.pdp.apptask1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.apptask1.entity.Address;
import uz.pdp.apptask1.entity.Department;
import uz.pdp.apptask1.entity.Worker;
import uz.pdp.apptask1.payload.Apiresponse;
import uz.pdp.apptask1.payload.WorkerDto;
import uz.pdp.apptask1.repository.AddressRepository;
import uz.pdp.apptask1.repository.DepartmentRepository;
import uz.pdp.apptask1.repository.WorkerRepository;

import java.util.List;

@Service
public class WorkerService {
    @Autowired
    WorkerRepository workerRepository;
    @Autowired
    DepartmentRepository departmentRepository;
    @Autowired
    AddressRepository addressRepository;
    public List<Worker> workerList(){
        List<Worker> workers = workerRepository.findAll();
    return workers;
    }
    public Apiresponse addWorker(WorkerDto dto){
        if (workerRepository.existsByPhoneNumber(dto.getPhoneNumber())) {
            return new Apiresponse("Worker with phoneNumber already exist",false);
        }
        if (addressRepository.existsByHomeNumberAndStreet(dto.getHomeNumber(), dto.getStreet())) {
            return new Apiresponse("Worker with this address already exist",false);
        }
        if (departmentRepository.existsById(dto.getDepartmentId())) {
            Department department = departmentRepository.findById(dto.getDepartmentId()).get();
            Address address=new Address();
            address.setStreet(dto.getStreet());
            address.setHomeNumber(dto.getHomeNumber());
            Address savedAddress = addressRepository.save(address);
            Worker worker =new Worker();
            worker.setAddress(savedAddress);
            worker.setDepartment(department);
            worker.setPhoneNumber(dto.getPhoneNumber());
            worker.setFullName(dto.getFullName());
            workerRepository.save(worker);
            return new Apiresponse("Added successfully",true);
        }
        return new Apiresponse("Department not found with this id",false);
    }
    public Apiresponse editWorker(Integer id,WorkerDto dto){
        if (workerRepository.existsById(id)) {
            if (workerRepository.existsByPhoneNumberAndIdNot(dto.getPhoneNumber(),id)) {
                return new Apiresponse("Worker with this phoneNumber already exist",false);
            }
            Department department = departmentRepository.findById(id).get();
            Worker worker = workerRepository.findById(id).get();
            if (addressRepository.existsByHomeNumberAndStreet(dto.getHomeNumber(), dto.getStreet())) {
                return new Apiresponse("Worker already exist with this address",false);
            }
            Address address = worker.getAddress();
            address.setHomeNumber(dto.getHomeNumber());
            address.setStreet(dto.getStreet());
            Address savedAddress = addressRepository.save(address);
            worker.setDepartment(department);
            worker.setAddress(savedAddress);
            worker.setFullName(dto.getFullName());
            worker.setPhoneNumber(dto.getPhoneNumber());
            workerRepository.save(worker);
            return new Apiresponse("Edited",true);
        }
        return new Apiresponse("Not found",false);
    }

    public Apiresponse deleteWorker(Integer id){
        if (workerRepository.existsById(id)) {
            workerRepository.deleteById(id);
            return new Apiresponse("Deleted",true);
        }
        return new Apiresponse("Not found",true);
    }
    public Apiresponse getWorkerById(Integer id){
        if (workerRepository.existsById(id)) {
            Worker worker = workerRepository.findById(id).get();
            return new Apiresponse("Worker",true,worker);
        }
        return new Apiresponse("Not found",false);
    }



}
