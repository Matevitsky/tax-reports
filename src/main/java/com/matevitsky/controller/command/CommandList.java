package com.matevitsky.controller.command;


public enum CommandList {

    LOGIN(new LoginCommand()),

    GET_REGISTRATION_PAGE(new GetRegistrationPageCommand()),

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

    static class Context {
        //    private static final UserService userService = new UserServiceImpl(new UserRepositoryImpl());
        //  private static final ActivityRequestService activityRequestService = new ActivityRequestServiceImpl(new ActivityRequestRepositoryImpl());
        // private static final ActivityService activityService = new ActivityServiceImpl(new ActivityRepositoryImpl());

        private Context() {
        }
    }
}
