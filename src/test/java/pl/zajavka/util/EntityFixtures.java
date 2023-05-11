package pl.zajavka.util;

import lombok.experimental.UtilityClass;
import pl.zajavka.infrastructure.database.entity.EmployeeEntity;

import java.math.BigDecimal;

@UtilityClass
public class EntityFixtures {
    public static EmployeeEntity someEmployee1() {
        return EmployeeEntity.builder()
                .name("Stefan")
                .surname("Zajavka")
                .salary(new BigDecimal("52322.00"))
                .phone("+48 854 145 332")
                .email("stefan@mail.com")
                .build();
    }

    public static EmployeeEntity someEmployee2() {
        return EmployeeEntity.builder()
                .name("Agnieszka")
                .surname("Spring")
                .salary(new BigDecimal("62341.00"))
                .phone("+48 854 143 332")
                .email("agnieszka@mail.com")
                .build();
    }

    public static EmployeeEntity someEmployee3() {
        return EmployeeEntity.builder()
                .name("Tomasz")
                .surname("Hibernate")
                .salary(new BigDecimal("53231.00"))
                .phone("+48 854 166 332")
                .email("tomasz@mail.com")
                .build();
    }

}
