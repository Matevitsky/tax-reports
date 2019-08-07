package com.matevitsky.service;

import com.matevitsky.entity.Client;
import com.matevitsky.entity.Employee;
import com.matevitsky.service.interfaces.InspectorService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;


@RunWith(MockitoJUnitRunner.class)
public class ClientServiceImplTest {

    @Mock
    InspectorService inspectorService;


    @Mock
    private ClientServiceImpl clientService;


    @Before
    public void setUp() throws Exception {

        MockitoAnnotations.initMocks(this);
    }


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

        Mockito.when(inspectorService.getFreeInspector()).thenReturn(employee);


        Client actual = clientService.assignInspector(client);

        assertEquals(expected, actual);
    }
}
