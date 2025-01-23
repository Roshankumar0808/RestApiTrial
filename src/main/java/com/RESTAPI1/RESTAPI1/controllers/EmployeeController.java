package com.RESTAPI1.RESTAPI1.controllers;

import com.RESTAPI1.RESTAPI1.dto.EmployeeDto;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping(path = "/employees")
public class EmployeeController {
    @GetMapping(path = "/getsecretmessage")
        public String getsecretmessage() {
            return "I am secret";
        }

    @GetMapping(path = "/{employeeId}")
        public EmployeeDto getEmployeeById(@PathVariable(name = "employeeId") Long id ){
           return new EmployeeDto(id,"Roshan","roshan@gmail.com",23,LocalDate.of(2024,5,15),true);
        }

        @GetMapping
       public String getEmployee(@RequestParam(required=false) Integer age,@RequestParam(required=false) String sortby){
           return "Hi"+age+sortby;
        }

        @PostMapping
        public EmployeeDto getPostEmployee(@RequestBody EmployeeDto inputemployee){
           inputemployee.setId(500L);
           return inputemployee;
        }
    @PutMapping
    public String getPutEmployee(){
        return "Hi Roshan form Put";
    }


}
