package com.udacity.jdnd.course3.critter.schedule;

import com.udacity.jdnd.course3.critter.entity.*;
import com.udacity.jdnd.course3.critter.service.CustomerService;
import com.udacity.jdnd.course3.critter.service.EmployeeService;
import com.udacity.jdnd.course3.critter.service.PetService;
import com.udacity.jdnd.course3.critter.service.ScheduleService;
import com.udacity.jdnd.course3.critter.user.EmployeeSkill;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;
import java.util.List;

/**
 * Handles web requests related to Schedules.
 */
@RestController
@RequestMapping("/schedule")
@RequiredArgsConstructor
public class ScheduleController {
    private final ScheduleService scheduleService;
    private final EmployeeService employeeService;
    private final PetService petService;

    @PostMapping
    public ScheduleDTO createSchedule(@RequestBody ScheduleDTO scheduleDTO) {
        Schedule schedule = scheduleDTO.toSchedule();
        for (Long id : scheduleDTO.getEmployeeIds())
            schedule.getEmployees().add(employeeService.getEmployee(id));
        for (Long id : scheduleDTO.getPetIds())
            schedule.getPets().add(petService.getPet(id));
        for (EmployeeSkill skill : scheduleDTO.getActivities())
            schedule.getActivities().add(new Skill(skill.name()));
        Schedule saved = scheduleService.createSchedule(schedule);
        return new ScheduleDTO().fromSchedule(saved);
    }

    @GetMapping
    public List<ScheduleDTO> getAllSchedules() {
        LinkedList<ScheduleDTO> schedules = new LinkedList<>();
        for (Schedule s : scheduleService.getAllSchedules())
            schedules.add(new ScheduleDTO().fromSchedule(s));
        return schedules;
    }

    @GetMapping("/pet/{petId}")
    public List<ScheduleDTO> getScheduleForPet(@PathVariable long petId) {
        List<ScheduleDTO> schedules = new LinkedList<>();
        Pet pet = petService.getPet(petId);
        for (Schedule s : scheduleService.getScheduleForPet(pet))
            schedules.add(new ScheduleDTO().fromSchedule(s));
        return schedules;
    }

    @GetMapping("/employee/{employeeId}")
    public List<ScheduleDTO> getScheduleForEmployee(@PathVariable long employeeId) {
        List<ScheduleDTO> schedules = new LinkedList<>();
        Employee employee = employeeService.getEmployee(employeeId);
        for (Schedule s : scheduleService.getScheduleForEmployee(employee))
            schedules.add(new ScheduleDTO().fromSchedule(s));
        System.out.println(schedules);
        return schedules;
    }

    @GetMapping("/customer/{customerId}")
    public List<ScheduleDTO> getScheduleForCustomer(@PathVariable long customerId) {
        List<ScheduleDTO> schedules = new LinkedList<>();
        for(Schedule s: scheduleService.getScheduleForCustomer(customerId))
            schedules.add(new ScheduleDTO().fromSchedule(s));
        return schedules;
    }
}
