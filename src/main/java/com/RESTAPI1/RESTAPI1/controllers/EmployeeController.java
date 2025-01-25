package com.RESTAPI1.RESTAPI1.controllers;

import com.RESTAPI1.RESTAPI1.dto.EmployeeDto;
import com.RESTAPI1.RESTAPI1.entities.EmployeeEntity;
import com.RESTAPI1.RESTAPI1.repositories.EmployeeRepositry;
import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(path = "/employees")

public class EmployeeController {
    @GetMapping(path = "/getsecretmessage")
        public String getsecretmessage() {
            return "I am secret";
        }

    private final EmployeeRepositry employeeRepositry;
    public EmployeeController(EmployeeRepositry employeeRepositry) {
        this.employeeRepositry = employeeRepositry;
    }


    @GetMapping(path = "/{employeeId}")
        public EmployeeEntity getEmployeeById(@PathVariable(name = "employeeId") Long id ){
           return employeeRepositry.findById(id).orElse(null);
        }

        @GetMapping
       public List<EmployeeEntity> getEmployee(@RequestParam(required=false,name="inputAge") Integer age, @RequestParam(required=false) String sortby){
           return employeeRepositry.findAll();
        }

        @PostMapping
        public EmployeeEntity getPostEmployee(@RequestBody EmployeeEntity inputemployee){

           return employeeRepositry.save(inputemployee);
        }
    @PutMapping
    public String getPutEmployee(){
        return "Hi Roshan form Put";
    }


}
