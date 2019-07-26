package com.matevitsky.service.interfaces;

import com.matevitsky.entity.Client;
import com.matevitsky.entity.Inspector;
import com.matevitsky.exception.WrongInputException;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.Optional;

public interface ClientService extends CrudService<Client> {

    Client register(Client client) throws WrongInputException, SQLException;

    Optional<Client> findByEmail(String email);

    boolean addReportToRequest(HttpServletRequest request, int reportId);

    boolean addReportsListToRequest(HttpServletRequest request, int clientId);

    Optional<Inspector> getInspector(int clientId);
}
