package com.jdbc.mapper;

import com.jdbc.dto.Employee;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeeRowMapper implements RowMapper {

    @Override
    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
        Employee emp = new Employee();
        emp.setEno(rs.getInt("ENO"));
        emp.setEname(rs.getString("ENAME"));
        emp.setEsal(rs.getFloat("ESAL"));
        emp.setEaddr(rs.getString("EADDR"));
        return emp;
    }
}
