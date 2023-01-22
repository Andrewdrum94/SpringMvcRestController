package org.example.controller;

import org.example.model.Employee;
import org.example.service.EmployeesDB;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeRestController {

    private final EmployeesDB db;

    public EmployeeRestController(EmployeesDB db) {
        this.db = db;
    }

    //http://localhost:8080/employee/1
    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id) {
        final Employee employee = db.getEmployeeById(id);
        return new ResponseEntity<>(employee, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<Employee> addEmployee(@RequestBody Employee employee) {
        db.addEmployee(employee);

        return new ResponseEntity<>(employee, HttpStatus.CREATED);
    }

    @GetMapping()
    public ResponseEntity<List<Employee>> getEmployee() {
        final List<Employee> employeeList = db.getEmployees();

        return employeeList != null &&  !employeeList.isEmpty()
                ? new ResponseEntity<>(employeeList, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteEmployee(@PathVariable Long id) {
        final boolean deleted = db.deleteEmployee(id);

        return deleted
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateEmployee(@PathVariable Long id, @RequestBody Employee employee) {
        final boolean updated = db.updateEmployee(employee, id);

        return updated
                ? new ResponseEntity<>(db.getEmployeeById(id), HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }
}
