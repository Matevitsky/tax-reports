package com.matevitsky.service.interfaces;

import com.matevitsky.entity.Request;

public interface RequestService extends CrudService<Request> {

    boolean deleteByClientID(int clientId);
}
