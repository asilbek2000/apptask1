package uz.pdp.apptask1.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import uz.pdp.apptask1.entity.Department;
import uz.pdp.apptask1.payload.Apiresponse;
import uz.pdp.apptask1.payload.DepartmentDto;
import uz.pdp.apptask1.service.DepartmentService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/department")
public class DepartmentController {
    @Autowired
     ModelMapper modelMapper;
    @Autowired
    DepartmentService departmentService;
    @GetMapping
    public HttpEntity<?> all(){
        List<Department> departments = departmentService.getAll();
        return ResponseEntity.ok().body(departments);
    }

    @PostMapping
    public HttpEntity<?> add(@RequestBody DepartmentDto dto){
        Apiresponse apiresponse = departmentService.addDepartment(dto);
        if (apiresponse.getSuccess()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(apiresponse);
        }
        return ResponseEntity.status(HttpStatus.CONFLICT).body(apiresponse);
    }
    @PutMapping
    public HttpEntity<?> edit(@PathVariable Integer id,@RequestBody DepartmentDto dto){
        Apiresponse apiresponse = departmentService.editDepartMent(id, dto);
        if (apiresponse.getSuccess()) {
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(apiresponse);
        }
        return ResponseEntity.status(HttpStatus.CONFLICT).body(apiresponse);
    }
    @DeleteMapping("/{id}")
    public HttpEntity<?> delete(@PathVariable Integer id){
        Apiresponse apiresponse = departmentService.deleteDepartment(id);
        if (apiresponse.getSuccess()) {
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(apiresponse);
        }
        return ResponseEntity.status(HttpStatus.CONFLICT).body(apiresponse);
    }

    @GetMapping("/{id}")
    public HttpEntity<?>getOne(@PathVariable Integer id){
        Apiresponse apiresponse = departmentService.getDepartmentById(id);
        if (apiresponse.getSuccess()) {
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(apiresponse);
        }
        return ResponseEntity.status(HttpStatus.CONFLICT).body(apiresponse);
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
