package com.example.CallCenterAPI.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import com.example.CallCenterAPI.models.EmployeeModel;
import com.example.CallCenterAPI.runtime.RuntimeService;
import com.example.CallCenterAPI.runtime.ServiceTools;

@RestController
public class EmployeeController 
{
    @Autowired
    private RuntimeService service;


    ArrayList<SseEmitter> emitters = new ArrayList<SseEmitter>(Arrays.asList(ServiceTools.eventHash()));

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String hello()
    {
        return "welcome";
    }

    @RequestMapping(value = "/gate", method = RequestMethod.POST)
    public String createEmployee(@RequestBody EmployeeModel employee)
    {
        return service.createEmployee(employee);
    }

    @RequestMapping(value = "/gate", method = RequestMethod.GET)
    public List<EmployeeModel> readEmployees()
    {
        return service.readEmployees();
    }

    @RequestMapping(value = "/gate", method = RequestMethod.PUT)
    public String updateEmployee(@RequestBody EmployeeModel employee)
    {
        return service.updateEmployee(employee);
    }

    @RequestMapping(value = "/gate", method = RequestMethod.DELETE)
    public String deleteEmployee(@RequestBody EmployeeModel employee)
    {
        return service.deleteEmployee(employee);
    }

    @RequestMapping(value = "/entrance", method = RequestMethod.GET)
    public SseEmitter welcome()
    {   
        try {
            emitters.add(0, ServiceTools.eventHash());
            emitters.get(0).send("establish_success");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return emitters.get(0);
    }

    @RequestMapping(value = "/entrance", method  = RequestMethod.POST)
    public void saveEmployee(@RequestBody EmployeeModel employee)
    {  
        
        service.saveEmployee(employee, emitters.get(0));
        //return message;
    } 
}
