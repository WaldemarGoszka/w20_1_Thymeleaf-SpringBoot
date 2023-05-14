package pl.zajavka.infrastructure.database;

import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;
import pl.zajavka.infrastructure.database.entity.EmployeeEntity;
import pl.zajavka.infrastructure.database.repository.EmployeeRepository;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static pl.zajavka.util.EntityFixtures.someEmployee1;
import static pl.zajavka.util.EntityFixtures.someEmployee2;
import static pl.zajavka.util.EntityFixtures.someEmployee3;


//@DataJpaTest
//@TestPropertySource(locations = "classpath:application-test.yml")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class EmployeeRepositoryDataJpaTest  extends AbstractJpaIT{

    private EmployeeRepository employeeRepository;
    @Test
    void thatEmployeeCanBeSavedCorrectly() {
        // given
        List<EmployeeEntity> employees = List.of(someEmployee1(), someEmployee2(), someEmployee3());
        employeeRepository.saveAllAndFlush(employees);
        // when
        List<EmployeeEntity> employeesFound = employeeRepository.findAll();
        // then
        assertEquals(3, employeesFound.size());
    }
}
