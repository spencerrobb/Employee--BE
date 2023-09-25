package com.example.demo.controller;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.demo.repository.EmployeeRespository;

import java.util.List;

@CrossOrigin(origins= "http://localhost:3000")
@RestController
@RequestMapping("/api/v1/")
public class EmployeeController {

    @Autowired
    private EmployeeRespository employeeRespository;

    //get all employess
    @GetMapping("/employees")
    public List<Employee> getAllEmployees(){
        return employeeRespository.findAll();
    }

    //create employee rest api
    @PostMapping("/employees")
    public Employee createEmployee(@RequestBody Employee employee){
        return employeeRespository.save(employee);
    }

    //get employee be id rest api
    @GetMapping("/employees/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable long id){
        Employee employee = employeeRespository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Employee does not exist with id: "+id));
        return ResponseEntity.ok(employee);
    }

    /// update employee rest api
    @PutMapping("/employees/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable Long id,@RequestBody Employee employeeDetails){
        Employee employee = employeeRespository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Employee does not exist with id: "+id));
        employee.setFirstName(employeeDetails.getFirstName());
        employee.setLastName(employeeDetails.getLastName());
        employee.setEmailId(employeeDetails.getEmailId());

        Employee updatedEmployee = employeeRespository.save(employee);
        return ResponseEntity.ok(updatedEmployee);
    }

}
