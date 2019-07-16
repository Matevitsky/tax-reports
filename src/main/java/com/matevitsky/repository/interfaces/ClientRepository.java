package com.matevitsky.repository.interfaces;

import com.matevitsky.entity.Client;

import java.util.Optional;

public interface ClientRepository extends CrudRepository<Client> {

    Optional<Client> findByEmail(String email);
}
