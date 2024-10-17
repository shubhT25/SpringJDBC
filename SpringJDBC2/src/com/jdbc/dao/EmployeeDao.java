package com.jdbc.dao;

import com.jdbc.dto.Employee;

public interface EmployeeDao {
    public void insertEmployee(Employee emp);
    public Employee readEmployee(int Eno);
}
