package com.example.CallCenterAPI.runtime;

import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import com.example.CallCenterAPI.models.EmployeeModel;

public interface ServiceTools 
{
    String saveEmployee(EmployeeModel e, SseEmitter emitter);
    SseEmitter eventHash();
}
