package com.jdbc.dao;

import com.jdbc.dto.Employee;

public interface EmployeeDao {
    public String add(Employee employee);
    public Employee search(int Eno);
    public String update(Employee employee);
    public String delete(int Eno);
}
