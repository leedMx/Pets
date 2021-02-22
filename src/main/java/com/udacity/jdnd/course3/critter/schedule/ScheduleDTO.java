package com.udacity.jdnd.course3.critter.schedule;

import com.udacity.jdnd.course3.critter.entity.Employee;
import com.udacity.jdnd.course3.critter.entity.Pet;
import com.udacity.jdnd.course3.critter.entity.Schedule;
import com.udacity.jdnd.course3.critter.entity.Skill;
import com.udacity.jdnd.course3.critter.user.EmployeeSkill;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

/**
 * Represents the form that schedule request and response data takes. Does not map
 * to the database directly.
 */
@Data
public class ScheduleDTO {
    private long id;
    private List<Long> employeeIds;
    private List<Long> petIds;
    private LocalDate date;
    private Set<EmployeeSkill> activities;
    Schedule toSchedule(){
        Schedule schedule = new Schedule();
        schedule.setId(getId());
        schedule.setDate(getDate());
        return schedule;
    }
    ScheduleDTO fromSchedule(Schedule schedule){
        setId(schedule.getId());
        setDate(schedule.getDate());
        for (Employee e : schedule.getEmployees())
            getEmployeeIds().add(e.getId());
        for (Pet p : schedule.getPets())
            getPetIds().add(p.getId());
        for (Skill s : schedule.getActivities())
            getActivities().add(EmployeeSkill.valueOf(String.valueOf(s)));
        return this;
    }
}
