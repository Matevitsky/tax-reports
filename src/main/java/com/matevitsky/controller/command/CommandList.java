package com.matevitsky.controller.command;


public enum CommandList {

    LOGIN(new LoginCommand()),

    GET_REGISTRATION_PAGE(new GetRegistrationPageCommand()),

    GET_CREATE_REPORT_PAGE(new GetCreateReportPageCommand()),

    GET_EDIT_VIEW_REPORT_PAGE(new GetEditViewPageCommand()),

    CREATE_REPORT(new CreateReportCommand()),

    EDIT_REPORT(new EditReportCommand()),

    ALL_REPORTS(new AllReportsCommand()),

    GET_CLIENT_PAGE(new GetClientPageCommand()),

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
