package com.RESTAPI1.RESTAPI1.controllers;

import com.RESTAPI1.RESTAPI1.OwnException.ResourceNotFound;
import com.RESTAPI1.RESTAPI1.dto.EmployeeDto;
import com.RESTAPI1.RESTAPI1.entities.EmployeeEntity;
import com.RESTAPI1.RESTAPI1.repositories.EmployeeRepositry;
import com.RESTAPI1.RESTAPI1.services.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.util.*;

@RestController
@RequestMapping(path = "/employees")

public class EmployeeController {
    @GetMapping(path = "/getsecretmessage")
        public String getsecretmessage() {
            return "I am secret";
        }
    private final EmployeeService employeeService;
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }




    @GetMapping(path = "/{employeeId}")
        public ResponseEntity<EmployeeDto>  getEmployeeById(@PathVariable(name = "employeeId") Long id ){
            Optional<EmployeeDto> employeedto=employeeService.getEmployeeById(id);
           return employeedto.map(employeeDto -> ResponseEntity.ok(employeeDto)).orElseThrow(()->new ResourceNotFound("Employee Not Found "+id));
//            if(employeedto==null){
//                return ResponseEntity.notFound().build();
//            }
//            return ResponseEntity.ok(employeedto);
        }


        @GetMapping
       public ResponseEntity<List<EmployeeDto>> getEmployee(@RequestParam(required=false,name="inputAge") Integer age, @RequestParam(required=false) String sortby){
           return ResponseEntity.ok(employeeService.getEmployee());
        }

        @PostMapping
        public ResponseEntity<EmployeeDto> getPostEmployee(@RequestBody @Valid EmployeeDto inputemployee){
           EmployeeDto employeeDto=employeeService.SaveEmployee(inputemployee);
           return new ResponseEntity<>(employeeDto, HttpStatus.CREATED);
        }
    @PutMapping(path = "/{employeeId}")
    public ResponseEntity<EmployeeDto> updateemployee(@RequestBody  @Valid EmployeeDto employeeDto,@PathVariable Long employeeId){
        return ResponseEntity.ok(employeeService.updateemployee(employeeDto,employeeId));
    }

    @DeleteMapping(path = "/{employeeId}")
    public ResponseEntity<Boolean> deleteemployee(@PathVariable Long employeeId) {
        boolean getdel = employeeService.deleteemployee(employeeId);
        if (getdel) {
            return ResponseEntity.ok(employeeService.deleteemployee(employeeId));
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }

    @PatchMapping(path="/{employeeId}")
    public ResponseEntity<EmployeeDto> patchemployeedata(@RequestBody Map<String,Object> employeeData,@PathVariable Long employeeId){
        EmployeeDto employeeDto=employeeService.patchemployeedata(employeeData,employeeId);
        if(employeeDto==null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(employeeDto);
    }


}
