package com.matevitsky.service;

import com.matevitsky.entity.Client;
import com.matevitsky.entity.Employee;
import com.matevitsky.service.interfaces.InspectorService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class ClientServiceImplTest {

    @Mock
    private InspectorService inspectorService;


    @InjectMocks
    private ClientServiceImpl clientService;


    @Test
    public void assignInspector() {

        Employee employee = Employee.newBuilder()
            .withId(25).build();

        Client client = Client.newClientBuilder()
            .withId(1).build();

        Client expected = Client.newClientBuilder()
            .withId(1)
            .withInspectorId(25)
            .build();

        when(inspectorService.getFreeInspector()).thenReturn(employee);


        Client actual = clientService.assignInspector(client);

        assertTrue((expected.getId() == actual.getId() && expected.getInspectorId() == actual.getInspectorId()));
    }
}
