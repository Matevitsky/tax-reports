package com.matevitsky.service.interfaces;

import java.net.http.HttpRequest;

public interface AdminService {

    void prepareAdminPage(HttpRequest request);
}
