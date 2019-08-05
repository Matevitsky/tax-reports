package com.matevitsky.controller.command;


import com.matevitsky.controller.command.admin.*;
import com.matevitsky.controller.command.client.*;
import com.matevitsky.controller.command.inspector.*;

public enum CommandList {

    LOGIN(new LoginCommand()),

    GET_REGISTRATION_PAGE(new Command.GetRegistrationPageCommand()),

    CLIENT_GET_CREATE_REPORT_PAGE(new ClientGetCreateReportPageCommand()),

    CLIENT_CREATE_REPORT(new ClientCreateReportCommand()),

    CLIENT_DELETE_REPORT(new ClientDeleteReport()),

    CLIENT_EDIT_REPORT(new ClientEditReportCommand()),

    CLIENT_ALL_REPORTS(new ClientAllReportsCommand()),

    GET_CLIENT_PAGE(new GetClientPageCommand()),

    CLIENT_VIEW_REPORT_PAGE(new ClientGetViewReportPage()),

    CLIENT_GET_EDIT_REPORT_PAGE(new ClientGetEditReportPageCommand()),

    CLIENT_CHANGE_SUPERVISOR(new ClientChangeInspectorCommand()),

    INSPECTOR_NEW_REPORTS(new InspectorGetNewReportsCommand()),

    INSPECTOR_GET_ALL_REPORTS(new InspectorGetAllReportsCommand()),

    GET_INSPECTOR_REPORT_PAGE(new InspectorGetReportPageCommand()),

    GET_INSPECTOR_NEW_REPORT_PAGE(new InspectorGetNewReportPageCommand()),

    INSPECTOR_ACCEPT_REPORT(new InspectorAcceptReportCommand()),

    INSPECTOR_DECLINE_REPORT(new InspectorDeclineReportCommand()),

    ADMIN_MAIN_PAGE(new AdminMainPageCommand()),

    ADMIN_ASSIGN_SUPERVISOR(new AdminAssignInspectorCommand()),

    ADMIN_GET_ALL_SUPERVISORS(new AdminGetAllInspectorsCommand()),

    ADMIN_GET_ALL_CUSTOMERS(new AdminGetAllClientsCommand()),

    ADMIN_CUSTOMER_LIST(new AdminInspectorClientsCommand()),

    ADMIN_CUSTOMER_REPORTS(new AdminClientReportsCommand()),

    ADMIN_EDIT_REPORT(new AdminEditReportCommand()),

    ADMIN_DELETE_REPORT(new AdminDeleteReport()),

    ADMIN_SAVE_REPORT(new AdminSaveReport()),

    LOG_OUT(new LogOutCommand()),

    ADMIN_CANCEL(new AdminCancelCommand()),

    CHANGE_LOCALE(new ChangeLocaleCommand()),


    REGISTER(new RegisterCommand());

    private Command command;

    CommandList(Command command) {
        this.command = command;

    }

    public Command getCommand() {
        return this.command;

    }


}
