package com.example.CallCenterAPI.runtime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Function;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import com.example.CallCenterAPI.database.EmployeeDatabase;
import com.example.CallCenterAPI.models.EmployeeModel;

@Service
public class RuntimeService implements ServiceTools
{
    //private static List<EmployeeModel> list = new ArrayList<>();

    @Autowired
    private EmployeeDatabase database;

    @Transactional
    public String createEmployee(EmployeeModel employee)
    {
        try
        {
            if (!database.existsById(employee.getId()))
            {
                database.save(employee);
                return "employee saved";
            }
            else
            {
                return "employee exists by id";
            }
        }
        catch (Exception e)
        {
            throw e;
        }
    }

    public List<EmployeeModel> readEmployees()
    {
        return database.findAll();
    }

    @Transactional
    public String updateEmployee(EmployeeModel employee)
    {
        if (database.existsById(employee.getId()))
        {
            try
            {
                EmployeeModel update = database.findById(employee.getId()).get();
                update.setStatus(employee.getStatus());
                update.setTime(employee.getTime());

                database.save(update);
                return "employee record updated";
            }
            catch (Exception e)
            {
                throw e;
            }
        }
        else
        {
            return "employee does not exist in database by id";
        }
    }

    @Transactional
    public String deleteEmployee(EmployeeModel employee)
    {
        if (database.existsById(employee.getId()))
        {
            try
            {
                database.delete(employee);
                return "employee record deleted";
            }
            catch (Exception e)
            {
                throw e;
            }
        }
        else
        {
            return "employee record failed to delete";
        }
    }

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
