package com.matevitsky.service;

import com.matevitsky.entity.Client;
import com.matevitsky.exception.WrongInputException;

import java.sql.SQLException;

public interface ClientService extends CrudService<Client> {

    Client register(Client client) throws WrongInputException, SQLException;

}
