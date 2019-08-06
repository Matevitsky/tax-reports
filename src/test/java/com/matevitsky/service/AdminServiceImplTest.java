package com.matevitsky.service;

import com.matevitsky.entity.Client;
import com.matevitsky.repository.interfaces.ClientRepository;
import com.matevitsky.repository.interfaces.RequestInspectorChangeRepository;
import com.matevitsky.service.interfaces.InspectorService;
import com.matevitsky.service.interfaces.RequestService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Optional;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;

public class AdminServiceImplTest {
    @Mock
    private Connection connection;

    @Mock
    private ClientRepository clientRepository;

    @Mock
    private InspectorService inspectorService;

    @Mock
    private RequestService requestService;

    @Mock
    private RequestInspectorChangeRepository requestInspectorChangeRepository;

    @InjectMocks
    private AdminServiceImpl adminService;

    @InjectMocks
    private ClientServiceImpl clientService;

    @Before
    public void setUp() throws Exception {

        MockitoAnnotations.initMocks(this);
        System.out.println();
    }


    @Test
    public void assignInspector() throws SQLException {
        int clientId = 1;
        int inspectorId = 25;
        Client client = Client.newClientBuilder()
                .withId(clientId)
                .build();

        //when(clientService.getById(clientId)).thenReturn(Optional.of(client));
        doReturn(Optional.of(client)).when(clientService).getById(clientId);

        doNothing().when(clientRepository).update(client, connection);
        doNothing().when(requestInspectorChangeRepository).deleteByClientID(clientId, connection);

        Assert.assertTrue(adminService.assignInspector(clientId, inspectorId));

    }
}
