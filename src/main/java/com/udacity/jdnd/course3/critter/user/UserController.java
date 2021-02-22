package com.udacity.jdnd.course3.critter.user;

import com.udacity.jdnd.course3.critter.entity.Customer;
import com.udacity.jdnd.course3.critter.entity.Employee;
import com.udacity.jdnd.course3.critter.entity.Weekday;
import com.udacity.jdnd.course3.critter.service.CustomerService;
import com.udacity.jdnd.course3.critter.service.EmployeeService;
import com.udacity.jdnd.course3.critter.service.PetService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.DayOfWeek;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * Handles web requests related to Users.
 *
 * Includes requests for both customers and employees. Splitting this into separate user and customer controllers
 * would be fine too, though that is not part of the required scope for this class.
 */
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final CustomerService customerService;
    private final EmployeeService employeeService;
    private final PetService petService;

    @PostMapping("/customer")
    public CustomerDTO saveCustomer(@RequestBody CustomerDTO customerDTO){
        Customer customer = customerService.save(customerDTO.toCustomer());
        for (Long petId : customerDTO.getPetIds())
            customer.getPets().add(petService.getPet(petId));
        return new CustomerDTO().fromCustomer(customer);
    }

    @GetMapping("/customer")
    public List<CustomerDTO> getAllCustomers(){
        List<CustomerDTO> customers = new LinkedList<>();
        for(Customer customer : customerService.getAllCustomers())
            customers.add(new CustomerDTO().fromCustomer(customer));
        return customers;
    }

    @GetMapping("/customer/pet/{petId}")
    public CustomerDTO getOwnerByPet(@PathVariable long petId){
         Customer customer = petService.getOwnerByPet(petId);
         return new CustomerDTO().fromCustomer(customer);
    }

    @PostMapping("/employee")
    public EmployeeDTO saveEmployee(@RequestBody EmployeeDTO employeeDTO) {
        Employee employee = employeeService.save(employeeDTO.toEmployee());
        return new EmployeeDTO().fromEmployee(employee);
    }

    @PostMapping("/employee/{employeeId}")
    public EmployeeDTO getEmployee(@PathVariable long employeeId) {
        Employee employee = employeeService.getEmployee(employeeId);
        return new EmployeeDTO().fromEmployee(employee);
    }

    @PutMapping("/employee/{employeeId}")
    public void setAvailability
            (@RequestBody Set<DayOfWeek> daysAvailable,
             @PathVariable long employeeId) {
        Employee employee = employeeService.getEmployee(employeeId);
        Set<Weekday> days = new HashSet<>();
        for (DayOfWeek day : daysAvailable)
            days.add(new Weekday(String.valueOf(day)));
        employee.setDaysAvailable(days);
    }

    @GetMapping("/employee/availability")
    public List<EmployeeDTO> findEmployeesForService(@RequestBody EmployeeRequestDTO employeeDTO) {
        throw new UnsupportedOperationException();
    }

}
