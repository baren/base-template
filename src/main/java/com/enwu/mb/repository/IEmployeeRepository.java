package com.enwu.mb.repository;

import com.enwu.mb.model.hibernate.Employee;

import java.util.List;

/**
 * Created by user on 16/7/6.
 */
public interface IEmployeeRepository {

    List<Employee> getAllUser();

}
