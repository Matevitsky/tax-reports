package com.matevitsky.controller.command;


public enum CommandList {

    LOGIN(new LoginCommand()),

    GET_REGISTRATION_PAGE(new GetRegistrationPageCommand()),

    GET_CREATE_REPORT_PAGE(new ClientGetCreateReportPageCommand()),

    GET_EDIT_REPORT_PAGE(new ClientGetEditPageCommand()),

    CREATE_REPORT(new ClientCreateReportCommand()),

    EDIT_REPORT(new ClientEditReportCommand()),

    ALL_REPORTS(new ClientAllReportsCommand()),

    GET_CLIENT_PAGE(new GetClientPageCommand()),

    INSPECTOR_NEW_REPORTS(new InspectorGetNewReportsPage()),

    INSPECTOR_GET_ALL_REPORTS(new InspectorGetAllReportsPage()),

    GET_INSPECTOR_REPORT_PAGE(new getInspectorReportPage()),

    INSPECTOR_ACCEPT_REPORT(new InspectorAcceptReport()),

    INSPECTOR_DECLINE_REPORT(new InspectorDeclineCommand()),

    GET_VIEW_REPORT_PAGE(new GetViewReportPage()),

    LOG_OUT(new LogOutCommand()),


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
