package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.entity.Customer;
import com.udacity.jdnd.course3.critter.entity.Employee;
import com.udacity.jdnd.course3.critter.entity.Pet;
import com.udacity.jdnd.course3.critter.entity.Schedule;
import com.udacity.jdnd.course3.critter.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.LinkedList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ScheduleService {
    private final ScheduleRepository repository;
    private final CustomerService customerService;

    public Schedule createSchedule(Schedule schedule) {
        return repository.save(schedule);
    }

    public List<Schedule> getAllSchedules() {
        return repository.findAll();
    }

    public List<Schedule> getScheduleForPet(Pet pet) {
        return repository.findAllByPetsContaining(pet);
    }

    public List<Schedule> getScheduleForEmployee(Employee employee) {
        return repository.findAllByEmployeesContains(employee);
    }

    public List<Schedule> getScheduleForCustomer(long customerId) {
        List<Schedule> schedules = new LinkedList<>();
        Customer customer = customerService.getCustomer(customerId);
        for (Pet pet : customer.getPets())
            schedules.addAll(repository.findAllByPetsContaining(pet));
        return schedules;
    }
}
