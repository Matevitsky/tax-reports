package com.matevitsky.service.interfaces;

import com.matevitsky.entity.Client;
import com.matevitsky.entity.Employee;
import com.matevitsky.exception.WrongInputException;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

public interface ClientService extends CrudService<Client> {

    Optional<Client> register(Client client) throws WrongInputException;

    Optional<Client> findByEmail(String email);

    boolean addReportToRequest(HttpServletRequest request, int reportId);

    boolean addReportsListToRequest(HttpServletRequest request, int clientId);

    Optional<Employee> getInspector(int clientId);

    Optional<List<Client>> getClientsByInspectorId(int clientId);

    Client assignInspector(Client client);

    String getNameById(int clientId);
}
