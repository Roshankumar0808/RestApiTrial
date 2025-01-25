package com.RESTAPI1.RESTAPI1.controllers;

import com.RESTAPI1.RESTAPI1.dto.EmployeeDto;
import com.RESTAPI1.RESTAPI1.entities.EmployeeEntity;
import com.RESTAPI1.RESTAPI1.repositories.EmployeeRepositry;
import com.RESTAPI1.RESTAPI1.services.EmployeeService;
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
    private final EmployeeService employeeService;
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }




    @GetMapping(path = "/{employeeId}")
        public EmployeeDto getEmployeeById(@PathVariable(name = "employeeId") Long id ){
           return employeeService.getEmployeeById(id);
        }

        @GetMapping
       public List<EmployeeDto> getEmployee(@RequestParam(required=false,name="inputAge") Integer age, @RequestParam(required=false) String sortby){
           return employeeService.getEmployee();
        }

        @PostMapping
        public EmployeeDto getPostEmployee(@RequestBody EmployeeDto inputemployee){

           return employeeService.SaveEmployee(inputemployee);
        }
    @PutMapping
    public String getPutEmployee(){
        return "Hi Roshan form Put";
    }


}
