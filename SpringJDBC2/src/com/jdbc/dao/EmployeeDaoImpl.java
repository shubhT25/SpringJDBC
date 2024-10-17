package com.jdbc.dao;

import com.jdbc.dto.Employee;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.support.AbstractLobCreatingPreparedStatementCallback;
import org.springframework.jdbc.core.support.AbstractLobStreamingResultSetExtractor;
import org.springframework.jdbc.support.lob.LobCreator;
import org.springframework.jdbc.support.lob.LobHandler;
import org.springframework.util.FileCopyUtils;

import java.io.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeeDaoImpl implements EmployeeDao{
    private JdbcTemplate jdbcTemplate;
    private LobHandler lobHandler;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void setLobHandler(LobHandler lobHandler) {
        this.lobHandler = lobHandler;
    }

    public LobHandler getLobHandler() {
        return lobHandler;
    }

    @Override
    public void insertEmployee(Employee emp) {
        String query = "insert into EMP values (?, ?, ?, ?)";
        jdbcTemplate.execute(query, new AbstractLobCreatingPreparedStatementCallback(lobHandler) {
            @Override
            protected void setValues(PreparedStatement ps, LobCreator lobCreator) throws SQLException, DataAccessException {
                try {
                    ps.setInt(1, emp.getEno());
                    ps.setString(2, emp.getEname());
                    FileInputStream fis = new FileInputStream(emp.getEimg());
                    FileReader fr = new FileReader(emp.getEres());
                    lobCreator.setBlobAsBinaryStream(ps, 3, fis, (int) emp.getEimg().length());
                    lobCreator.setClobAsCharacterStream(ps, 4, fr, (int) emp.getEres().length());
                    System.out.println("Employee Inserted");
                } catch (Exception e) {
                    System.out.println("Problem");
                    e.printStackTrace();
                }
            }
        });

    }

    @Override
    public Employee readEmployee(int Eno) {
        Employee emp = new Employee();
        try {
            String query = "select * from emp where eno=" + Eno;
            jdbcTemplate.query(query, new AbstractLobStreamingResultSetExtractor<>() {
                @Override
                protected void streamData(ResultSet rs) throws SQLException, IOException, DataAccessException {
                    emp.setEno(rs.getInt("ENO"));
                    emp.setEname(rs.getString("ENAME"));
                    File file1 = new File("D:\\Workspace\\SpringJDBC\\SpringJDBC2\\src\\my_img.jpeg");
                    FileOutputStream fos = new FileOutputStream(file1);
                    FileCopyUtils.copy(lobHandler.getBlobAsBinaryStream(rs, 3), fos);
                    emp.setEimg(file1);

                    File file2 = new File("D:\\Workspace\\SpringJDBC\\SpringJDBC2\\src\\my_resume.pdf");
                    FileWriter fw = new FileWriter(file2);
                    FileCopyUtils.copy(lobHandler.getClobAsCharacterStream(rs, 4), fw);
                    emp.setEres(file2);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        };
        return emp;
    }
}
