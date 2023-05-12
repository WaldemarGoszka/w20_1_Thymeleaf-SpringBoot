package pl.zajavka.controller;

import lombok.AllArgsConstructor;
import org.hamcrest.Matchers;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.LinkedMultiValueMap;
import pl.zajavka.infrastructure.database.repository.EmployeeRepository;

import java.util.Map;
import java.util.stream.Stream;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = EmployeesController.class)
@AllArgsConstructor(onConstructor = @__(@Autowired))
class EmployeesControllerWebMvcTest {
    private MockMvc mockMvc;
    @MockBean
    private EmployeeRepository employeeRepository;

    @ParameterizedTest
    @MethodSource
    void thatPhoneValidationWorksCorrectly(Boolean correctPhone, String phone) throws Exception {
        // given
        LinkedMultiValueMap<String, String> parameters = new LinkedMultiValueMap<>();
        Map<String, String> parametersMap = EmployeeDTO.builder().phone(phone).build().asMap();
        parametersMap.forEach(parameters::add);
        //when(employeeRepository.save(any())).thenReturn(null);
        // when, then
        if (correctPhone) {
            mockMvc.perform(post("/employees/add").params(parameters))
                    .andExpect(status().isOk())
                    .andExpect(model().attributeDoesNotExist("errorMessage"))
                    .andExpect(view().name("employees"));
        } else {
            mockMvc.perform(post("/employees/add").params(parameters))
                    .andExpect(status().isBadRequest())
                    .andExpect(view().name("error"))
                    .andExpect(model().attributeExists("errorMessage"))
                    .andExpect(model().attribute("errorMessage", Matchers.containsString(phone)));
        }
    }

    public static Stream<Arguments> thatPhoneValidationWorksCorrectly() {
        return Stream.of(
                Arguments.of(false, ""),
                Arguments.of(false, "+48 504 203 260@@"),
                Arguments.of(false, "+48.504.203.260"),
                Arguments.of(false, "+55(123) 456-78-90-"),
                Arguments.of(false, "+55(123) - 456-78-90"),
                Arguments.of(false, "504.203.260"),
                Arguments.of(false, " "),
                Arguments.of(false, "-"),
                Arguments.of(false, "()"),
                Arguments.of(false, "() + ()"),
                Arguments.of(false, "(21 7777"),
                Arguments.of(false, "+48 (21)"),
                Arguments.of(false, "+"),
                Arguments.of(false, "555-5555-555"),
                Arguments.of(true, "+48 504 203 260")
        );
    }
}

