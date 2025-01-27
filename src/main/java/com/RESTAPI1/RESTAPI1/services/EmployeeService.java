package com.RESTAPI1.RESTAPI1.services;

import com.RESTAPI1.RESTAPI1.OwnException.ResourceNotFound;
import com.RESTAPI1.RESTAPI1.dto.EmployeeDto;
import com.RESTAPI1.RESTAPI1.entities.EmployeeEntity;
import com.RESTAPI1.RESTAPI1.repositories.EmployeeRepositry;
import org.aspectj.util.Reflection;
import org.modelmapper.ModelMapper;
import org.springframework.data.util.ReflectionUtils;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeService {
    private final EmployeeRepositry employeeRepositry;
    private final ModelMapper modelMapper;
    public EmployeeService(EmployeeRepositry employeeRepositry, ModelMapper modelMapper) {
        this.employeeRepositry = employeeRepositry;
        this.modelMapper = modelMapper;
    }
    public void isExistCheck(Long EmployeeId){
        boolean isExits=employeeRepositry.existsById(EmployeeId);
        if(!isExits){
            throw new ResourceNotFound("Employee with id "+ EmployeeId +" NotFound");
        }

    }

    public Optional<EmployeeDto>  getEmployeeById(Long id) {
        //Optional<EmployeeEntity> employeeEntity= employeeRepositry.findById(id);
        return employeeRepositry.findById(id).map(employeeEntity1 ->modelMapper.map(employeeEntity1,EmployeeDto.class) ) ;

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

    public EmployeeDto updateemployee(EmployeeDto employeeDto, Long employeeId) {
        isExistCheck(employeeId);
        EmployeeEntity employeeEntitytypecast=modelMapper.map(employeeDto,EmployeeEntity.class);
        employeeEntitytypecast.setId(employeeId);
        EmployeeEntity employeeEntitytyupdate= employeeRepositry.save(employeeEntitytypecast);
        return modelMapper.map(employeeEntitytyupdate,EmployeeDto.class);

    }

    public boolean deleteemployee(Long employeeId) {
        isExistCheck(employeeId);
        employeeRepositry.deleteById(employeeId);
        return true;
    }

    public EmployeeDto patchemployeedata(Map<String, Object> employeeData, Long employeeId) {
        boolean isExits=employeeRepositry.existsById(employeeId);
        if(isExits) {
            EmployeeEntity employeeEntity = employeeRepositry.findById(employeeId).get();
            employeeData.forEach((key,value)->{
                Field toupdate= ReflectionUtils.findRequiredField(EmployeeEntity.class,key);
                toupdate.setAccessible(true);
                ReflectionUtils.setField(toupdate,employeeEntity,value);
            });
            return modelMapper.map(employeeRepositry.save(employeeEntity),EmployeeDto.class);
        }
        else{
            isExistCheck(employeeId);
            return null;
        }

    }
}
