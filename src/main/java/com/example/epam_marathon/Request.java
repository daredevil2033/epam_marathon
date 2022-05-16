package com.example.epam_marathon;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class Request {
    private int id;
    private BigDecimal phone;
    private String name;
    private String request;
    private String status;
    private Timestamp date;

    public Request(int id, BigDecimal phone, String name, String request, String status, Timestamp date) {
        this.id = id;
        this.phone = phone;
        this.name = name;
        this.request = request;
        this.status = status;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public BigDecimal getPhone() {
        return phone;
    }

    public void setPhone(BigDecimal phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRequest() {
        return request;
    }

    public void setRequest(String request) {
        this.request = request;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }
}
