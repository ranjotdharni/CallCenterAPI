package com.example.CallCenterAPI.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import com.example.CallCenterAPI.models.EmployeeModel;
import com.example.CallCenterAPI.runtime.ServiceTools;

@RestController
public class EmployeeController 
{
    @Autowired
    private ServiceTools service;

    ArrayList<SseEmitter> emitters = new ArrayList<SseEmitter>(Arrays.asList(ServiceTools.eventHash()));

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
