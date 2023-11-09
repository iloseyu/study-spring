package com.first.recruIT.domain;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Company {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String company_id;
    private String pw;
    private String area;
    private String initiate;
    private String tel;
    private int employee;
    private String introduce;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getCompany_id() {
        return company_id;
    }
    public void setCompany_id(String company_id) {
        this.company_id = company_id;
    }

    public String getPw() {
        return pw;
    }
    public void setPw() {
        this.pw = pw;
    }


    public String getArea() {
        return area;
    }
    public void setArea(String area) {
        this.area = area;
    }

    public String getInitiate() {
        return initiate;
    }
    public void setInitiate(String initiate) {
        this.initiate = initiate;
    }

    public String getTel() {
        return tel;
    }
    public void setTel(String tel) {
        this.tel = tel;
    }

    public int getEmployee() {
        return employee;
    }
    public void setEmployee(int employee) {
        this.employee = employee;
    }

    public String getIntroduce() {
        return introduce;
    }
    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }
}
