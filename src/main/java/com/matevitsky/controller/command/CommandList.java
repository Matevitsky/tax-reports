package com.matevitsky.controller.command;


import com.matevitsky.controller.command.admin.*;
import com.matevitsky.controller.command.client.*;
import com.matevitsky.controller.command.inspector.*;

public enum CommandList {

    LOGIN(new LoginCommand()),

    GET_REGISTRATION_PAGE(new Command.GetRegistrationPageCommand()),

    GET_CREATE_REPORT_PAGE(new ClientGetCreateReportPageCommand()),

    //  GET_EDIT_REPORT_PAGE(new ClientGetEditPageCommand()),

    CREATE_REPORT(new ClientCreateReportCommand()),

    DELETE_REPORT(new ClientDeleteReport()),

    EDIT_REPORT(new ClientEditReportCommand()),

    ALL_REPORTS(new ClientAllReportsCommand()),

    GET_CLIENT_PAGE(new GetClientPageCommand()),

    CLIENT_CHANGE_INSPECTOR(new ClientChangeInspectorCommand()),

    INSPECTOR_NEW_REPORTS(new InspectorGetNewReportsCommand()),

    INSPECTOR_GET_ALL_REPORTS(new InspectorGetAllReportsCommand()),

    GET_INSPECTOR_REPORT_PAGE(new InspectorGetReportPageCommand()),

    GET_INSPECTOR_NEW_REPORT_PAGE(new InspectorGetNewReportPageCommand()),

    INSPECTOR_ACCEPT_REPORT(new InspectorAcceptReportCommand()),

    INSPECTOR_DECLINE_REPORT(new InspectorDeclineReportCommand()),

    //   GET_VIEW_REPORT_PAGE(new ClientGetViewReportPage()),

    ADMIN_MAIN_PAGE(new AdminMainPageCommand()),

    ADMIN_ASSIGN_INSPECTOR(new AdminAssignInspectorCommand()),

    ADMIN_GET_ALL_INSPECTORS(new AdminGetAllInspectorsCommand()),

    ADMIN_GET_ALL_CLIENTS(new AdminGetAllClientsCommand()),

    ADMIN_INSPECTOR_CLIENTS(new AdminInspectorClientsCommand()),

    ADMIN_CLIENT_REPORTS(new AdminClientReportsCommand()),

    ADMIN_EDIT_REPORT(new AdminEditReportCommand()),

    ADMIN_DELETE_REPORT(new AdminDeleteReport()),

    ADMIN_SAVE_REPORT(new AdminSaveReport()),

    LOG_OUT(new LogOutCommand()),

    ADMIN_CANCEL(new AdminCancelCommand()),

    CHANGE_LOCALE(new ChangeLocaleCommand()),


    REGISTER(new RegisterCommand());

   /* USER_ACTIVITY_REMOVE_REQUEST(new UserActivityRemoveCommand(Context.activityService)),

    USER_REQUEST_ACTIVITY(new UserRequestActivityCommand(Context.activityService, Context.activityRequestService)),

    ADMIN_GET_FINISHED_ACTIVITIES(new AdminGetFinishedActivitiesCommand(Context.activityService, Context.userService)),

    ADMIN_MAIN_PAGE(new AdminMainPageCommand(Context.activityService)),

    ADMIN_CREATE_NEW_ACTIVITY(new AdminCreateNewActivityCommand(Context.activityService)),

    ADMIN_ACTIVITY_REQUESTS(new AdminActivityRequestsPageCommand(Context.userService, Context.activityService, Context.activityRequestService)),

    ADMIN_REMOVE_ACTIVITY(new AdminRemoveActivityCommand(Context.activityService, Context.userService)),

    ADMIN_ASSIGN_ACTIVITY_COMMAND(new AdminAssignActivityCommand(Context.userService, Context.activityService, Context.activityRequestService)),

    ERROR(new ErrorCommand()),

    LOGOUT(new LogOutCommand()),

    CHANGE_LOCALE(new ChangeLocaleCommand(Context.activityService));*/

    private Command command;

    CommandList(Command command) {
        this.command = command;

    }

    public Command getCommand() {
        return this.command;

    }


}
