package com.matevitsky.service.interfaces;

import javax.servlet.http.HttpServletRequest;

public interface AdminService {

    void prepareAdminPage(HttpServletRequest request);

    boolean assignInspector(int clientId, int inspectorId);

    void addRequestAmountToHeader(HttpServletRequest request);

}
