package com.matevitsky.service;

import com.matevitsky.entity.Client;
import com.matevitsky.exception.WrongInputException;

import java.sql.SQLException;
import java.util.Optional;

public interface ClientService extends CrudService<Client> {

    Client register(Client client) throws WrongInputException, SQLException;

    Optional<Client> findByEmail(String email);

}
