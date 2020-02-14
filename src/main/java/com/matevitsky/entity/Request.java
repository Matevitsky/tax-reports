package com.matevitsky.entity;

import java.util.Objects;

public class Request {
    int id;
    int clientId;

    public Request(int id, int clientId) {
        this.id = id;
        this.clientId = clientId;
    }

    public int getId() {
        return id;
    }

    public int getClientId() {
        return clientId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Request)) {
            return false;
        }
        Request request = (Request) o;
        return id == request.id &&
            clientId == request.clientId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, clientId);
    }
}
