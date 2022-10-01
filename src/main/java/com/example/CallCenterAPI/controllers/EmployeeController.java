package com.example.CallCenterAPI.controllers;

import java.util.ArrayList;

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
    ArrayList<SseEmitter> emitters = null;

    @Autowired
    private ServiceTools service;

    @RequestMapping(value = "/entrance", method = RequestMethod.GET)
    public SseEmitter welcome()
    {   
        if (emitters == null)
        {
            emitters = new ArrayList<SseEmitter>();
            emitters.add(0, service.eventHash());
        }
        return emitters.get(0);
    }

    @RequestMapping(value = "/entrance", method  = RequestMethod.POST)
    public String saveEmployee(@RequestBody EmployeeModel employee)
    {  
        if (emitters == null)
        {
            emitters = new ArrayList<SseEmitter>();
            emitters.add(0, service.eventHash());
        }
        String message = service.saveEmployee(employee, emitters.get(0));
        return message;
    } 
}
