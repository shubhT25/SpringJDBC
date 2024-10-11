package com.jdbc.dto;


public class Employee {
    private int Eno;
    private String Ename;
    private float Esal;
    private String Eaddr;

    public String getEaddr() {
        return Eaddr;
    }

    public void setEaddr(String eaddr) {
        Eaddr = eaddr;
    }

    public String getEname() {
        return Ename;
    }

    public void setEname(String ename) {
        Ename = ename;
    }

    public int getEno() {
        return Eno;
    }

    public void setEno(int eno) {
        Eno = eno;
    }

    public float getEsal() {
        return Esal;
    }

    public void setEsal(float esal) {
        Esal = esal;
    }
}
