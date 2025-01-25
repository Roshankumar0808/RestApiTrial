package com.RESTAPI1.RESTAPI1.services;

import com.RESTAPI1.RESTAPI1.dto.EmployeeDto;
import com.RESTAPI1.RESTAPI1.entities.EmployeeEntity;
import com.RESTAPI1.RESTAPI1.repositories.EmployeeRepositry;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeService {
    private final EmployeeRepositry employeeRepositry;
    private final ModelMapper modelMapper;
    public EmployeeService(EmployeeRepositry employeeRepositry, ModelMapper modelMapper) {
        this.employeeRepositry = employeeRepositry;
        this.modelMapper = modelMapper;
    }

    public EmployeeDto getEmployeeById(Long id) {
        EmployeeEntity employeeEntity= employeeRepositry.findById(id).orElse(null);
        return modelMapper.map(employeeEntity,EmployeeDto.class);

    }

    public List<EmployeeDto> getEmployee() {
        List<EmployeeEntity> employeeEntityList=employeeRepositry.findAll();
        return employeeEntityList.stream().map(employeeEntity ->modelMapper.map(employeeEntity,EmployeeDto.class) ).collect(Collectors.toList());
    }

    public EmployeeDto SaveEmployee(EmployeeDto inputemployee) {
        EmployeeEntity inputemployeeconvert=modelMapper.map(inputemployee,EmployeeEntity.class);
        EmployeeEntity employeeEntitysave=employeeRepositry.save(inputemployeeconvert);
        return modelMapper.map(employeeEntitysave,EmployeeDto.class);
    }
}
