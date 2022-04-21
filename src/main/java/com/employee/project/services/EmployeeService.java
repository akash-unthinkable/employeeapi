package com.employee.project.services;

import com.employee.project.dao.EmployeeRepository;
import com.employee.project.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class EmployeeService {

    @Autowired
    private EmployeeRepository EmployeeRepository;

    // get all employee
    public List<Employee> getAllEmployee(){

        return   EmployeeRepository.findAll();
    }
    // get single employee by id


    public Optional<Employee> getBookById (int id) {

        Optional<Employee> employee  = Optional.ofNullable(EmployeeRepository.findById(id));
        return employee;
    }

    public Employee addEmployee(Employee employee){
        Employee employeeResult;
        employeeResult = EmployeeRepository.save(employee);
        return employeeResult;
    }
    public void deleteEmployee(int bid){

        EmployeeRepository.deleteById(bid);
    }
    //update employee
    public void updateEmployee(Employee employee, int empId){
        employee.setEmpId(empId);
        EmployeeRepository.save(employee);
    }
    public  List<Employee> findAllSortingAscn(int page){
        Pageable sortedAsc= PageRequest.of(page,3, Sort.by("title"));

        List<Employee> allEmployee=  EmployeeRepository.findAll(sortedAsc).getContent();
        return allEmployee;
    }


    public  List<Employee> findAllSortingDesc(int page){
        Pageable sortedDesc= PageRequest.of(page,3, Sort.by("title").descending());

        List<Employee> allEmployee=  EmployeeRepository.findAll(sortedDesc).getContent();
        return allEmployee;
    }

}
