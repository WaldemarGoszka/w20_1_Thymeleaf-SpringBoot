package pl.zajavka.controller;

import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.ExtendedModelMap;
import pl.zajavka.infrastructure.database.entity.EmployeeEntity;
import pl.zajavka.infrastructure.database.repository.EmployeeRepository;
import pl.zajavka.util.EntityFixtures;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;


@ExtendWith(MockitoExtension.class)
class EmployeesControllerTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeesController employeesController;

    @Test
    public void thatRetrievingEmployeeWorksCorrectly() {
        // given
        Integer employeeId = 10;
        EmployeeEntity employeeEntity = EntityFixtures.someEmployee1();
        ExtendedModelMap model = new ExtendedModelMap();
        Mockito.when(employeeRepository.findById(employeeId)).thenReturn(Optional.of(employeeEntity));
        // when
        String result = employeesController.showEmployeeDetails(employeeId, model);
        // then
        assertThat(result).isEqualTo("employeeDetails");
        assertThat(model.getAttribute("employee")).isEqualTo(employeeEntity);
    }
}
