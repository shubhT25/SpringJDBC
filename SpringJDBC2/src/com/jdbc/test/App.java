package com.jdbc.test;

import com.jdbc.dao.EmployeeDao;
import com.jdbc.dao.EmployeeDaoImpl;
import com.jdbc.dto.Employee;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.File;

public class App {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("/com/jdbc/resources/applicationContext.xml");
        EmployeeDao empDao = (EmployeeDao) context.getBean("employeeDaoImpl");
        Employee emp = new Employee();
        emp.setEno(222);
        emp.setEname("Kaustubh");
        emp.setEimg(new File("D:\\Workspace\\SpringJDBC\\SpringJDBC2\\src\\img.jpeg"));
        emp.setEres(new File("D:\\Workspace\\SpringJDBC\\SpringJDBC2\\src\\demo.pdf"));
        empDao.insertEmployee(emp);

        Employee emp2 = empDao.readEmployee(222);
        System.out.println("Employee Details:");
        System.out.println("------------------------------");
        System.out.println("Employee no: " + emp2.getEno());
        System.out.println("Employee name: " + emp2.getEname());
        System.out.println("Employee image: " + emp2.getEimg().getAbsolutePath());
        System.out.println("Employee Resume: " + emp2.getEres().getAbsolutePath());
    }
}
