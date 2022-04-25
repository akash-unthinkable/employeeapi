package com.employee.project.controllers;

import com.employee.project.entity.Employee;
import com.employee.project.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequestMapping("/api2/v1")
@RestController
public class EmployeeControllar {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/employees")
    public ResponseEntity<List<Employee>> getEmployee() {

        List<Employee> list= employeeService.getAllEmployee();
        if(list.size()<=0)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        return ResponseEntity.status(HttpStatus.CREATED).body(list);
    }

    @GetMapping("/employees/desc/{page}")
    public ResponseEntity<List<Employee>> getAllSortedAscending(@PathVariable("page") int page) {

        List<Employee> list= employeeService.findAllSortingDesc(page);
        if(list.size()<=0)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        return ResponseEntity.status(HttpStatus.CREATED).body(list);
    }
    @GetMapping("/employees/ascn/{page}")
    public ResponseEntity<List<Employee>> getAllSortedEmployee(@PathVariable("page") int page) {

        List<Employee> list= employeeService.findAllSortingAscn(page);
        if(list.size()<=0)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        return ResponseEntity.status(HttpStatus.CREATED).body(list);
    }
    @GetMapping("/employee/{id}")
    public ResponseEntity<Employee> getEmployee(@PathVariable("id") int id ){

        Optional <Employee>book= employeeService.getBookById(id);
        if (!book.isPresent())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        return ResponseEntity.of(book);
    }
    // add new employee
//    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/employee")
    public ResponseEntity<Employee> addEmployee(@RequestBody Employee employee){
        Employee b= null;
        try {
            b = this.employeeService.addEmployee(employee);
            return ResponseEntity.of(Optional.of(b));
        }catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    // delete employee
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_ADMIN')")
    @DeleteMapping("/employee/{empId}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable("empId") int empId){
        try {
            this.employeeService.deleteEmployee(empId);
            return   ResponseEntity.ok().build();
        } catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }

    }
    //update employee
    @PutMapping("/employee/{empId}")
    public ResponseEntity<Employee> updateEmployee(@RequestBody Employee employee, @PathVariable("empId") int empId){
        try {
            this.employeeService.updateEmployee(employee, empId);
            return ResponseEntity.ok().build();
        }
        catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
