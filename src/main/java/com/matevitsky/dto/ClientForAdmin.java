package com.matevitsky.dto;

import com.matevitsky.entity.Client;

public class ClientForAdmin extends Client {
    private final String inspectorFirstName;
    private final String inspectorLastName;
    private final String companyName;


    public ClientForAdmin(ClientBuilder builder, String inspectorFirstName, String inspectorLastName, String companyName) {
        super(builder);
        this.companyName = companyName;
        this.inspectorFirstName = inspectorFirstName;
        this.inspectorLastName = inspectorLastName;
    }

    public String getInspectorFirstName() {
        return inspectorFirstName;
    }

    public String getInspectorLastName() {
        return inspectorLastName;
    }

    public String getCompanyName() {
        return companyName;
    }
}
