package com.matevitsky.dto;

import com.matevitsky.entity.ReportStatus;

public class ReportWithClientName {

    private final int id;
    private final String tittle;
    private final String clientFullName;

    private final ReportStatus reportStatus;


    public ReportWithClientName(int id, String tittle, String clientFirstName, String clientLastName, ReportStatus reportStatus) {
        this.id = id;
        this.tittle = tittle;
        this.clientFullName = clientLastName + " " + clientFirstName;
        this.reportStatus = reportStatus;

    }

    public int getId() {
        return id;
    }

    public String getTittle() {
        return tittle;
    }

    public String getClientFullName() {
        return clientFullName;
    }

    public ReportStatus getReportStatus() {
        return reportStatus;
    }
}
