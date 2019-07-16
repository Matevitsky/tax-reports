package com.matevitsky.service;

import com.matevitsky.entity.Client;
import com.matevitsky.entity.Inspector;
import com.matevitsky.entity.User;
import com.matevitsky.util.MD5Util;

import java.util.Optional;

public class LoginService {

    public User login(String email, String password) {
        User user = null;
        ClientService clientService = new ClientServiceImpl();
        InspectorService inspectorService = new InspectorServiceImpl();
        Optional<Inspector> inspector = inspectorService.findByEmail(email);

        if (inspector.isPresent()) {
            user = inspector.get();
            if (user.getPassword().equals(MD5Util.encryptPassword(password))) {
                return
            }
        } else {
            Optional<Client> client = clientService.findByEmail(email);
            if (client.isPresent()) {
                user = client.get();
            }
        }
        return user;

        //TODO: поменять имена в базе на единственное число
    }
}
