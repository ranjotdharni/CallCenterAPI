package com.example.CallCenterAPI.runtime;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import com.example.CallCenterAPI.models.EmployeeModel;

public interface ServiceTools 
{
    void saveEmployee(EmployeeModel e, SseEmitter emitter);
    static SseEmitter eventHash()
    {
        SseEmitter emitter = new SseEmitter(-1L);

        ExecutorService exe = Executors.newSingleThreadExecutor();
        exe.execute(() -> {
            try
            {
                //emitter.send("establish_success");
            }
            catch (Exception exc)
            {
                //emitter.completeWithError(exc);
            }
            finally
            {
                //emitter.complete();
            }
        });

        exe.shutdown();
        return emitter;
    }
}
