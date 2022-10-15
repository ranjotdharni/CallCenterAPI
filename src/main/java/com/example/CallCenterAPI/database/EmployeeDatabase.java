package com.example.CallCenterAPI.database;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.CallCenterAPI.models.EmployeeModel;

@Repository
public interface EmployeeDatabase extends JpaRepository<EmployeeModel, Integer>
{
    @Query("select max(e.id) from EmployeeModel e")
    public Integer findMaxId();
}
