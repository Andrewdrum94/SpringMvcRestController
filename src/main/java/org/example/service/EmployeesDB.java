package org.example.service;

import org.example.model.Employee;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

@Component
public class EmployeesDB {

    private final TreeMap<Long, Employee> DATA = new TreeMap<>();

    public void addEmployee(Employee employee) {
        if (employee.getId() != null) {
            throw new AppException("Невозможно добавить работника с уже имеющимся id");
        }
        if (employee.getId() == null && DATA.isEmpty()) {
            Long id = 1L;
            employee.setId(id);
            DATA.put(id, employee);
        } if (employee.getId() == null && !DATA.isEmpty()) {
            employee.setId(DATA.lastKey() + 1);
        }
    }

    public Employee getEmployeeById(Long id) {
        if (DATA.get(id) == null) {
            throw new AppException(String.format("Пользователя с id %s не существует", id));
        } else {
            return DATA.get(id);
        }
    }

    public List<Employee> getEmployees() {
        return new ArrayList<>(DATA.values());
    }

    public boolean deleteEmployee(Long id) {
        return DATA.remove(id) != null;
    }

    public boolean updateEmployee(Employee employee, Long id) {
        if (DATA.containsKey(id)) {
            employee.setId(id);
            DATA.put(id, employee);
            return true;
        }
        return false;
    }

}
