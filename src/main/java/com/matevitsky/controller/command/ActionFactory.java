package com.matevitsky.controller.command;

import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

import static com.matevitsky.controller.constant.ParameterConstant.COMMAND;

public class ActionFactory {

    private static final Logger LOGGER = Logger.getLogger(ActionFactory.class);

    public Command defineCommand(HttpServletRequest request) {

        Command current = null;
        String action = request.getParameter(COMMAND);
        if (Objects.isNull(action)) {
            LOGGER.warn("command is NULL");
            return current;
        }
        try {
            CommandList command = CommandList.valueOf(action.toUpperCase());
            current = command.getCommand();

        } catch (IllegalArgumentException e) {
            LOGGER.warn("The command " + action.toUpperCase() + " does not exist");
        }
        return current;
    }
}
