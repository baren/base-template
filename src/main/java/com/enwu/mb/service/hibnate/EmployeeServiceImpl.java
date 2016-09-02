package com.enwu.mb.service.hibnate;

import com.enwu.mb.model.hibernate.Employee;
import com.enwu.mb.repository.IEmployeeRepository;
import com.enwu.mb.service.IEmployeeService;
import com.google.code.ssm.api.CacheKeyMethod;
import com.google.code.ssm.api.ReadThroughMultiCache;
import com.google.code.ssm.api.ReadThroughSingleCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by user on 16/7/11.
 */
@Service
@Transactional
public class EmployeeServiceImpl implements IEmployeeService {

    @Autowired
    private IEmployeeRepository employeeRepository;

    @Override
    @ReadThroughMultiCache
    public List<Employee> getAllUser() {
        return employeeRepository.getAllUser();
    }
}
