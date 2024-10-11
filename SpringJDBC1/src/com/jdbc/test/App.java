package com.jdbc.test;

import com.jdbc.dao.EmployeeDao;
import com.jdbc.dao.EmployeeDaoImpl;
import com.jdbc.dto.Employee;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("/com/jdbc/resources/applicationContext.xml");
        EmployeeDao empDao = (EmployeeDaoImpl) context.getBean("employeeDaoImpl");
        Employee emp1 = new Employee();

        emp1.setEno(2);
        emp1.setEname("Shubham");
        emp1.setEsal(80000);
        emp1.setEaddr("Wakad, Pune");
        System.out.println(empDao.add(emp1));

        System.out.println(empDao.search(2).getEname());

        Employee emp2 = new Employee();
        emp2.setEno(2);
        emp2.setEname("Shubham");
        emp2.setEsal(100000);
        emp2.setEaddr("Malkapur");

        System.out.println(empDao.update(emp2));

        System.out.println(empDao.delete(2));

    }
}
