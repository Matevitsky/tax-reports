package com.matevitsky.service;

import com.matevitsky.dto.UserForLogin;
import com.matevitsky.entity.Client;
import com.matevitsky.entity.Employee;
import com.matevitsky.entity.EmployeeRole;
import com.matevitsky.service.interfaces.ClientService;
import com.matevitsky.service.interfaces.InspectorService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class LoginServiceTest {

    @Mock
    private HttpServletRequest request;

    @Mock
    private InspectorService inspectorService;

    @Mock
    private ClientService clientService;

    @Mock
    private HttpSession session;


    @InjectMocks
    private LoginService loginService;

    @Test
    public void shouldReturnAdmin() {

        Employee admin = Employee.newBuilder()
            .withId(1)
            .withEmail("email")
            .withPassword("password")
            .withFirstName("name")
            .withLastName("last name")
            .withEmployeeRole(EmployeeRole.ADMIN)
            .build();

        Mockito.when(inspectorService.findByEmail("admin@test.tes")).thenReturn(Optional.of(admin));
        when(request.getSession()).thenReturn(session);
        UserForLogin expected = new UserForLogin(admin.getId(), admin.getEmail(), admin.getPassword(), UserForLogin.Role.ADMIN);
        UserForLogin actual = loginService.login("admin@test.tes", "password", request);

        Assert.assertEquals(expected, actual);
    }


    @Test
    public void shouldReturnInspector() {
        Employee admin = Employee.newBuilder()
            .withId(1)
            .withEmail("email")
            .withPassword("password")
            .withFirstName("name")
            .withLastName("last name")
            .withEmployeeRole(EmployeeRole.INSPECTOR)
            .build();
        Mockito.when(inspectorService.findByEmail("admin@test.tes")).thenReturn(Optional.of(admin));
        when(request.getSession()).thenReturn(session);
        UserForLogin expected = new UserForLogin(admin.getId(), admin.getEmail(), admin.getPassword(), UserForLogin.Role.INSPECTOR);
        UserForLogin actual = loginService.login("admin@test.tes", "password", request);

        Assert.assertEquals(expected, actual);

    }

    @Test
    public void shouldReturnClient() {
        Client client = Client.newClientBuilder()
            .withId(1)
            .withEmail("email@tes.com")
            .withPassword("password")
            .withFirstName("name")
            .withLastName("last name")
            .withCompanyId(1)
            .withInspectorId(1)
            .build();

        Mockito.when(clientService.findByEmail("email@tes.com")).thenReturn(Optional.of(client));
        when(request.getSession()).thenReturn(session);
        UserForLogin expected = new UserForLogin(client.getId(), client.getEmail(), client.getPassword(), UserForLogin.Role.CLIENT);
        UserForLogin actual = loginService.login("email@tes.com", "password", request);

        Assert.assertEquals(expected, actual);

    }
}
