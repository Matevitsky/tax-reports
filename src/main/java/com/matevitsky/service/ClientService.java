package com.matevitsky.service;

import com.matevitsky.entity.Client;
import com.matevitsky.exception.WrongInputException;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface ClientService {

    Client register(Client client) throws WrongInputException, SQLException;

    boolean deleteById(Integer id);

    Client update(Client client);

    Optional<Client> getById(Integer id);

    Optional<List<Client>> getAll();
}
