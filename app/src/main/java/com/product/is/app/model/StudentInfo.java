package com.product.is.app.model;

/**
 * Created by liuyidi on 17/5/22.
 */
public class StudentInfo {
    private String name;
    private String studentNo;
    private String className;
    private Integer chinese;
    private Integer math;
    private Integer english;
    private Integer total;

    public StudentInfo(String name, String studentNo,String className, Integer chinese, Integer math, Integer english, Integer total) {
        this.name = name;
        this.studentNo = studentNo;
        this.className = className;
        this.chinese = chinese;
        this.math = math;
        this.english = english;
        this.total = total;
    }

    public String getName() {
        return name;
    }

    public void setName (String name) {
        this.name = name;
    }

    public String getStudentNo() {
        return studentNo;
    }

    public void setStudentNo (String studentNo) {
        this.studentNo = studentNo;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName (String className) {
        this.className = className;
    }

    public Integer getEnglish() {
        return english;
    }

    public void setEnglish (Integer english) {
        this.english = english;
    }

    public Integer getChinese() {
        return chinese;
    }

    public void setChinese (Integer chinese) {
        this.chinese = chinese;
    }

    public Integer getMath() {
        return math;
    }

    public void setMath (Integer math) {
        this.math = math;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal (Integer total) {
        this.total = total;
    }

}

