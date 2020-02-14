package com.matevitsky.controller.command;


import com.matevitsky.controller.command.admin.*;
import com.matevitsky.controller.command.client.*;
import com.matevitsky.controller.command.inspector.*;
import com.matevitsky.repository.implementation.*;
import com.matevitsky.repository.interfaces.*;
import com.matevitsky.service.*;
import com.matevitsky.service.interfaces.*;

public enum CommandList {

    LOGIN(new LoginCommand(Context.loginService, Context.adminService, Context.inspectorService, Context.reportService)),

    CLIENT_GET_CREATE_REPORT_PAGE(new ClientGetCreateReportPageCommand()),

    CLIENT_CREATE_REPORT(new ClientCreateReportCommand(Context.reportService)),

    CLIENT_DELETE_REPORT(new ClientDeleteReport(Context.reportService)),

    CLIENT_EDIT_REPORT(new ClientEditReportCommand(Context.reportService)),

    CLIENT_ALL_REPORTS(new ClientAllReportsCommand(Context.reportService)),

    GET_CLIENT_PAGE(new GetMainClientPageCommand(Context.reportService)),

    CLIENT_VIEW_REPORT_PAGE(new ClientGetViewReportPage(Context.clientService)),

    CLIENT_GET_EDIT_REPORT_PAGE(new ClientGetEditReportPageCommand(Context.reportService)),

    CLIENT_CHANGE_SUPERVISOR(new ClientChangeInspectorCommand(Context.requestService, Context.reportService)),

    INSPECTOR_NEW_REPORTS(new InspectorGetNewReportsCommand(Context.inspectorService)),

    INSPECTOR_GET_ALL_REPORTS(new InspectorGetAllReportsCommand(Context.inspectorService)),

    GET_INSPECTOR_REPORT_PAGE(new InspectorGetReportPageCommand(Context.reportService)),

    GET_INSPECTOR_NEW_REPORT_PAGE(new InspectorGetNewReportPageCommand(Context.reportService)),

    INSPECTOR_ACCEPT_REPORT(new InspectorAcceptReportCommand(Context.inspectorService)),

    INSPECTOR_DECLINE_REPORT(new InspectorDeclineReportCommand(Context.inspectorService)),

    ADMIN_MAIN_PAGE(new AdminMainPageCommand(Context.adminService)),

    ADMIN_ASSIGN_SUPERVISOR(new AdminAssignInspectorCommand(Context.adminService)),

    ADMIN_GET_ALL_SUPERVISORS(new AdminGetAllInspectorsCommand(Context.inspectorService, Context.adminService)),

    ADMIN_GET_ALL_CUSTOMERS(new AdminGetAllClientsCommand(Context.clientService, Context.adminService)),

    ADMIN_CUSTOMER_LIST(new AdminInspectorClientsCommand(Context.clientService, Context.adminService)),

    ADMIN_CUSTOMER_REPORTS(new AdminClientReportsCommand(Context.reportService, Context.adminService)),

    ADMIN_EDIT_REPORT(new AdminEditReportCommand(Context.reportService, Context.adminService)),

    ADMIN_DELETE_REPORT(new AdminDeleteReport(Context.reportService, Context.adminService)),

    ADMIN_SAVE_REPORT(new AdminSaveReport(Context.reportService, Context.adminService)),

    LOG_OUT(new LogOutCommand()),

    ADMIN_CANCEL(new AdminCancelCommand(Context.reportService, Context.adminService)),

    CHANGE_LOCALE(new ChangeLocaleCommand(Context.reportService, Context.adminService, Context.inspectorService)),

    REGISTER(new RegisterCommand(Context.clientService, Context.reportService, Context.companyService, Context.inspectorService));

    private Command command;

    CommandList(Command command) {
        this.command = command;

    }

    public Command getCommand() {
        return this.command;

    }

    static class Context {

        private static final ClientRepository clientRepository = new ClientRepositoryImpl();
        private static final ReportRepository reportRepository = new ReportRepositoryImpl();
        private static final InspectorRepository inspectorRepository = new InspectorRepositoryImpl();
        private static final ReportService reportService = new ReportServiceImpl(reportRepository);
        private static final RequestInspectorChangeRepository requestInspectorChangeRepository = new RequestInspectorChangeRepositoryImpl();

        private static final InspectorService inspectorService = new InspectorServiceImpl
                (inspectorRepository, new ClientRepositoryImpl(), reportService);

        private final static CompanyRepository companyRepository = new CompanyRepositoryImpl();
        private final static CompanyService companyService = new CompanyServiceImpl(companyRepository);

        private static final ClientService clientService = new ClientServiceImpl(clientRepository
                , inspectorService, reportService);

        private static final RequestService requestService = new RequestServiceImpl(requestInspectorChangeRepository);
        private static final LoginService loginService = new LoginService(clientService, inspectorService);
        private static final AdminService adminService = new AdminServiceImpl(
                requestInspectorChangeRepository
                , requestService, new ClientRepositoryImpl()
                , inspectorService
                , new ClientServiceImpl(new ClientRepositoryImpl(), inspectorService, reportService));

        private Context() {
        }
    }
}
