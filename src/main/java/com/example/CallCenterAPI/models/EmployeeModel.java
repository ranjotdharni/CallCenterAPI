package com.example.CallCenterAPI.models;

public class EmployeeModel {
    private String id;
    private String status;
    private String time;  
    
    public void setId(String id)
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

    public String getId()
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

    public String toString()
    {
        return this.id + "\n" + this.status + "\n" + this.time;
    }
}
