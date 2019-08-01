package com.matevitsky.service.interfaces;

import com.matevitsky.entity.Employee;

import javax.servlet.http.HttpServletRequest;

public interface AdminService {

    void prepareAdminPage(HttpServletRequest request);

    boolean assignInspector(int clientId, int inspectorId);

    void addHeaderDataToRequest(HttpServletRequest request);

    Employee getAdminById(int id);
}
