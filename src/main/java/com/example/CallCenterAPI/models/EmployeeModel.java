package com.example.CallCenterAPI.models;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class EmployeeModel {

    @Id
    private int id;
    private String status;
    private String time;  
    
    public void setId(int id)
    {
        this.id = id;
    }

    public void setStatus(String status)
    {
        this.status = status;
    }

    public void setTime(String time)
    {
        this.time = time;
    }

    public int getId()
    {
        return this.id;
    }

    public String getStatus()
    {
        return this.status;
    }

    public String getTime()
    {
        return this.time;
    }

    @Override
    public String toString()
    {
        return "{\"id\": " + this.id + ", \"status\": \"" + this.status + "\", \"time\": \"" + this.time + "\"}";
    }
}
