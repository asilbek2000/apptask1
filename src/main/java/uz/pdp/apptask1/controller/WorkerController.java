package uz.pdp.apptask1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.apptask1.entity.Worker;
import uz.pdp.apptask1.payload.Apiresponse;
import uz.pdp.apptask1.payload.WorkerDto;
import uz.pdp.apptask1.service.WorkerService;

import java.util.List;

@RestController
@RequestMapping("/api/worker")
public class WorkerController {
    @Autowired
    WorkerService workerService;
    @GetMapping
    public HttpEntity<?>all(){
        List<Worker> workers = workerService.workerList();
        return ResponseEntity.ok(workers);
    }
    @PostMapping
    public HttpEntity<?>add(@RequestBody WorkerDto dto){
        Apiresponse apiresponse = workerService.addWorker(dto);
        if (apiresponse.getSuccess()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(apiresponse);
        }
        return ResponseEntity.status(HttpStatus.CONFLICT).body(apiresponse);
    }
    @PutMapping("/{id}")
    public HttpEntity<?>edit(@PathVariable Integer id,@RequestBody WorkerDto dto){
        Apiresponse apiresponse = workerService.editWorker(id, dto);
        if (apiresponse.getSuccess()) {
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(apiresponse);
        }
        return ResponseEntity.status(HttpStatus.CONFLICT).body(apiresponse);
    }
    @DeleteMapping("/{id}")
    public HttpEntity<?> delete(@PathVariable Integer id){
        Apiresponse apiresponse = workerService.deleteWorker(id);
        if (apiresponse.getSuccess()) {
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(apiresponse);
        }
        return ResponseEntity.status(HttpStatus.CONFLICT).body(apiresponse);
    }


}
