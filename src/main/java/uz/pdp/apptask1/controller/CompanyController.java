package uz.pdp.apptask1.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import uz.pdp.apptask1.entity.Company;
import uz.pdp.apptask1.payload.Apiresponse;
import uz.pdp.apptask1.payload.CompanyDto;
import uz.pdp.apptask1.service.CompanyService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/companies")
public class CompanyController {

    @Autowired
    ModelMapper modelMapper;
    @Autowired
    CompanyService companyService;




    @GetMapping
    public HttpEntity<?> all(){
//        List<Company> companies = companyService.getAll();
//     return ResponseEntity.ok(companies);
     /*
      List<DepartmentDTO> collect = departmentService.getAll().stream().map(
                        department -> modelMapper.map(department, DepartmentDTO.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok().body(collect);
      */
        List<CompanyDto> collect = companyService.getAll().stream().map(company -> modelMapper.map(company,
                CompanyDto.class)).collect(Collectors.toList());
        return ResponseEntity.ok().body(collect);
    }
    @PostMapping
    public HttpEntity<?> add(@RequestBody CompanyDto dto){
        Apiresponse apiresponse = companyService.addCompany(dto);
        if (apiresponse.getSuccess()) {
            return ResponseEntity.status(201).body(apiresponse);
        }
        return ResponseEntity.status(HttpStatus.CONFLICT).body(apiresponse);
    }
@PutMapping("/{id}")
  public HttpEntity<?> edit(@PathVariable Integer id,@RequestBody CompanyDto dto){
    Apiresponse apiresponse = companyService.editCompany(id, dto);
    if (apiresponse.getSuccess()) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(apiresponse);
    }
    return ResponseEntity.status(HttpStatus.CONFLICT).body(apiresponse);
}
  @DeleteMapping("/{id}")
  public HttpEntity<?> delete(@PathVariable Integer id){
      Apiresponse apiresponse = companyService.deleteCompany(id);
      if (apiresponse.getSuccess()) {
          return ResponseEntity.status(202).body(apiresponse);
      }
      return ResponseEntity.status(409).body(apiresponse);
  }



    @GetMapping("/{id}")
    public HttpEntity<?> getOne(@PathVariable Integer id){
        Apiresponse apiresponse = companyService.getCompanyById(id);
        if (apiresponse.getSuccess()) {
            return ResponseEntity.status(202).body(apiresponse);
        }
        return ResponseEntity.status(409).body(apiresponse);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }
}
