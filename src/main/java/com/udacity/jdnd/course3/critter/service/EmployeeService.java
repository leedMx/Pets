package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.entity.Employee;
import com.udacity.jdnd.course3.critter.entity.Skill;
import com.udacity.jdnd.course3.critter.entity.Weekday;
import com.udacity.jdnd.course3.critter.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Transactional
public class EmployeeService {
    private final EmployeeRepository repository;

    public Employee save(Employee employee) {
        return repository.save(employee);
    }

    public Employee getEmployee(long employeeId) {
        return repository.findById(employeeId)
                .orElseThrow(EntityNotFoundException::new);
    }

    public List<Employee> findForService(Weekday day, Set<Skill> skillSet) {
        List<Employee> employees = new LinkedList<>();
        for(Employee e : repository.findAllByDaysAvailableContains(day))
            if (e.getSkills().containsAll(skillSet))
                employees.add(e);
        return employees;
    }
}
