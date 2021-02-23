package com.udacity.jdnd.course3.critter.user;

import com.udacity.jdnd.course3.critter.entity.Employee;
import com.udacity.jdnd.course3.critter.entity.Skill;
import com.udacity.jdnd.course3.critter.entity.Weekday;
import lombok.Data;

import java.time.DayOfWeek;
import java.util.HashSet;
import java.util.Set;

/**
 * Represents the form that employee request and response data takes. Does not map
 * to the database directly.
 */
@Data
public class EmployeeDTO {
    private long id;
    private String name;
    private Set<EmployeeSkill> skills = new HashSet<>();
    private Set<DayOfWeek> daysAvailable = new HashSet<>();

    public Employee toEmployee() {
        Employee employee = new Employee();
        employee.setId(getId());
        employee.setName(getName());
        for (EmployeeSkill skill : skills)
            employee.getSkills().add(new Skill(skill.name()));
        for (DayOfWeek day : daysAvailable)
            employee.getDaysAvailable().add(new Weekday(String.valueOf(day)));
        return employee;
    }

    public EmployeeDTO fromEmployee(Employee employee) {
        setId(employee.getId());
        setName(employee.getName());
        for (Skill skill : employee.getSkills())
            getSkills().add(EmployeeSkill.valueOf(skill.getSkill()));
        for (Weekday day : employee.getDaysAvailable())
            getDaysAvailable().add(DayOfWeek.valueOf(day.getWeekday()));
        return this;
    }
}
