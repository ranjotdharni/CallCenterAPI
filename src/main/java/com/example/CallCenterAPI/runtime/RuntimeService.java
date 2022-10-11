package com.example.CallCenterAPI.runtime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import com.example.CallCenterAPI.models.EmployeeModel;

@Service
public class RuntimeService implements ServiceTools
{
    //private static List<EmployeeModel> list = new ArrayList<>();

    @Override
    public void saveEmployee(EmployeeModel e, SseEmitter Emitter)
    {
        //list.add(e);

        ExecutorService exe = Executors.newSingleThreadExecutor();
        exe.execute(() -> {
            try
            {
                Emitter.send(/*"add_success:id:" +*/ e.getId() /*+ "\n\n"*/);
            }
            catch (Exception exc)
            {
                exc.printStackTrace();
            }
        });
        
        exe.shutdown();
        //return "proc_succeeded";
    }

    /*@Override
    public SseEmitter eventHash()
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
    }*/
}
