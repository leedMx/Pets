package com.udacity.jdnd.course3.critter.user;

import com.udacity.jdnd.course3.critter.entity.Skill;
import com.udacity.jdnd.course3.critter.entity.Weekday;
import lombok.Data;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

/**
 * Represents a request to find available employees by skills. Does not map
 * to the database directly.
 */
@Data
public class EmployeeRequestDTO {
    private Set<EmployeeSkill> skills;
    private LocalDate date;
    public Weekday getWeekday(){
        return new Weekday(getDate().getDayOfWeek().name());
    }
    public Set<Skill> getSkillSet(){
        Set<Skill> skillSet = new HashSet<>();
        for (EmployeeSkill skill : getSkills())
            skillSet.add(new Skill(skill.name()));
        return skillSet;
    }

}
