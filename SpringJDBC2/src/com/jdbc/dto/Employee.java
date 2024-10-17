package com.jdbc.dto;

import java.io.File;

public class Employee {
    private int Eno;
    private String Ename;
    private File Eimg;
    private File Eres;

    public File getEimg() {
        return Eimg;
    }

    public void setEimg(File eimg) {
        Eimg = eimg;
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

    public File getEres() {
        return Eres;
    }

    public void setEres(File eres) {
        Eres = eres;
    }
}
