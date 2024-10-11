package com.jdbc.dao;

import com.jdbc.dto.Employee;
import com.jdbc.mapper.EmployeeRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class EmployeeDaoImpl implements EmployeeDao{

    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public String add(Employee employee) {
        String status="";
        try {
            System.out.println("Adding");
//          If no row is the emp object is not return, hence there will be a exception. So we will use list
//          Employee emp = (Employee) jdbcTemplate.query("select * from Employee where ENO =" + employee.getEno(), new EmployeeRowMapper());
            List<Employee> empList = jdbcTemplate.query("select * from Employee where ENO =" + employee.getEno(), new EmployeeRowMapper());
            if(empList.isEmpty()) {
                int rowCount = jdbcTemplate.update("insert into Employee values(" + employee.getEno() + ",'" + employee.getEname() + "'," + employee.getEsal() + ",'" + employee.getEaddr() + "')");
                if(rowCount == 1) {
                    status="Employee inserted successfully";
                } else {
                    status = "Operation Failed";
                }
            } else {
                status="Employee already exist";
            }

        } catch (Exception e) {
            status="Failed";
            e.printStackTrace();
        }
        return status;
    }

    @Override
    public Employee search(int Eno) {
        Employee employee = null;
        try {
            List <Employee> empList = jdbcTemplate.query("select * from Employee where ENO=" + Eno, new EmployeeRowMapper());
            if(empList.isEmpty()) {
                return null;
            } else {
                employee = empList.getFirst();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return employee;
    }

    @Override
    public String update(Employee employee) {
        String status = "";
        try{
            if(search(employee.getEno()) == null) {
                status = "Employee not Found";
                return status;
            } else {
                int rowCount = jdbcTemplate.update("update Employee set ENO=" + employee.getEno() + ", " +
                        "ENAME='" + employee.getEname() + "', ESAL=" + employee.getEsal() + ", EADDR='" + employee.getEaddr() + "' where ENO=" + employee.getEno());
                if(rowCount == 1) {
                     return "Employee Updated";
                } else {
                    return "Operation Failed";
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "Failed";
        }
    }

    @Override
    public String delete(int Eno) {
        String status = "";
        try{
            if(search(Eno) == null) {
                status = "Employee not Found";
                return status;
            } else {
                int rowCount = jdbcTemplate.update("delete from Employee where ENO=" + Eno);
                if(rowCount == 1) {
                    return "Employee Deleted";
                } else {
                    return "Operation Failed";
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "Failed";
        }
    }
}
