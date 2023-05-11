package pl.zajavka.controller;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.zajavka.infrastructure.database.entity.EmployeeEntity;
import pl.zajavka.infrastructure.database.repository.EmployeeRepository;

import java.math.BigDecimal;
import java.util.List;

@Controller
@RequestMapping("/employees")
@AllArgsConstructor
public class EmployeesController {
    private EmployeeRepository employeeRepository;

    @PostMapping("/add")
    public String addEmployee(
            @Valid @ModelAttribute("employeeDTO") EmployeeDTO employeeDTO
    ) {
        EmployeeEntity newEmployee = EmployeeEntity.builder()
                .name(employeeDTO.getName())
                .surname(employeeDTO.getSurname())
                .salary(employeeDTO.getSalary())
                .phone(employeeDTO.getPhone())
                .email(employeeDTO.getEmail())
                        .build();
        employeeRepository.save(newEmployee);
        return "redirect:/employees";
    }

    @PutMapping("/update")
    public String updateEmployee(
            @Valid @ModelAttribute("employeeDTO") EmployeeDTO employeeDTO
    ) {
        EmployeeEntity existingEmployee
                = employeeRepository.findById(employeeDTO.getEmployeeId())
                .orElseThrow(() -> new EntityNotFoundException(
                        String.format("EmployeeEntity not found, employeeId: [%s]",
                                employeeDTO.getEmployeeId())));

        existingEmployee.setName(employeeDTO.getName());
        existingEmployee.setSurname(employeeDTO.getSurname());
        existingEmployee.setSalary(employeeDTO.getSalary());
        existingEmployee.setPhone(employeeDTO.getPhone());
        existingEmployee.setEmail(employeeDTO.getEmail());
        employeeRepository.save(existingEmployee);
        return "redirect:/employees";
    }

    @GetMapping
    public String employeesList(Model model) {
        List<EmployeeEntity> allEmployees = employeeRepository.findAll();
        model.addAttribute("employees", allEmployees);
        model.addAttribute("employeeDTO", new EmployeeDTO());
        return "employees";
    }

    @GetMapping("/show/{employeeId}")
    public String showEmployeeDetails(@PathVariable Integer employeeId, Model model) {
        EmployeeEntity employeeDetails = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new EntityNotFoundException(
                        String.format("EmployeeEntity not found, employeeId: [%s]", employeeId)));
        model.addAttribute("employee", employeeDetails);
        return "employeeDetails";
    }

    @DeleteMapping("/delete/{employeeId}")
    public String deleteEmployee(@PathVariable Integer employeeId) {
        employeeRepository.deleteById(employeeId);
        return "redirect:/employees";
    }


}
