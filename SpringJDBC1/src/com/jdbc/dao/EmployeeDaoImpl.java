package com.jdbc.dao;

import com.jdbc.dto.Employee;
import com.jdbc.mapper.EmployeeRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.*;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class EmployeeDaoImpl extends JdbcDaoSupport implements EmployeeDao{

//    private JdbcTemplate JDBCTemplate;
//    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
//    private DataSource ds;
//
//    public void setDs(DataSource dataSource) {
//        setDataSource(dataSource);
//    }
    //    public void setJDBCTemplate(JdbcTemplate jdbcTemplate) {
//        this.JDBCTemplate = jdbcTemplate;
//    }

//    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
//         this.jdbcTemplate = jdbcTemplate;
//    }
//    public void setNamedParameterJdbcTemplate(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
//        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
//    }

    @Override
    public String add(Employee employee) {
        String status="";
        try {
            Employee emp = search(employee.getEno());
            if(emp == null) {
//                String query = "insert into Employee values(:ENO, :ENAME, :ESAL, :EADDR)";
//                Map<String, Object> mp = new HashMap<>();
//                mp.put("ENO", employee.getEno());
//                mp.put("ENAME", employee.getEname());
//                mp.put("ESAL", employee.getEsal());
//                mp.put("EADDR", employee.getEaddr());
//                int rowCount = getNamedParameterJdbcTemplate().update(query, mp);
////                System.out.println(rowCount);
                int rowCount = getJdbcTemplate().update("insert into Employee values(" + employee.getEno() + ",'" + employee.getEname() + "'," + employee.getEsal() + ",'" + employee.getEaddr() + "')");
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
            // If no row is the emp object is not return, hence there will be a exception. So we will use list
            // Employee emp = (Employee) jdbcTemplate.query("select * from Employee where ENO =" + employee.getEno(), new EmployeeRowMapper());
//            String query = "select * from Employee where ENO=:ENO";
//            Map<String, Object> map = new HashMap<String, Object>();
//            map.put("ENO", Eno);
//            List <Employee> employeeList = getNamedParameterJdbcTemplate().query(query, map, new EmployeeRowMapper());
//
            List <Employee> empList = getJdbcTemplate().query("select * from Employee where ENO=" + Eno, new EmployeeRowMapper());
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
//                String query = "update Employee set ENAME=:Ename, ESAL=:Esal, EADDR=:Eaddr where ENO=:Eno";
//                SqlParameterSource namedParam = new BeanPropertySqlParameterSource(employee);
//                int rowCount = getNamedParameterJdbcTemplate().update(query, namedParam);
//                System.out.println("Rooo " + rowCount);
               int rowCount = getJdbcTemplate().update("update Employee set ENO=" + employee.getEno() + ", " +
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
//                String query = "delete from Employee where ENO=:ENO";
//                SqlParameterSource paramSource = new MapSqlParameterSource("ENO", Eno);
//                int rowCount = getNamedParameterJdbcTemplate().update(query, paramSource);
                int rowCount = getJdbcTemplate().update("delete from Employee where ENO=" + Eno);
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
