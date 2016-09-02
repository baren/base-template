package com.enwu.mb.repository.hibernate;

import com.enwu.mb.model.hibernate.Employee;
import com.enwu.mb.repository.IEmployeeRepository;
import org.hibernate.Criteria;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by user on 16/7/10.
 */
@Repository
public class EmployeeRepositoryImpl extends AbstractRepository<Integer, Employee> implements IEmployeeRepository {



    @Override
    public List<Employee> getAllUser() {
        Criteria criteria = createEntityCriteria();
        return (List<Employee>) criteria.list();
    }
}
