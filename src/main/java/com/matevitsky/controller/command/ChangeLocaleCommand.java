package com.matevitsky.controller.command;

import com.matevitsky.controller.command.admin.AdminMainPageCommand;
import com.matevitsky.controller.command.client.GetClientPageCommand;
import com.matevitsky.controller.command.inspector.InspectorGetNewReportsCommand;
import com.matevitsky.service.ResourceManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;


public class ChangeLocaleCommand implements Command {
    private static final Logger LOGGER = Logger.getLogger(ChangeLocaleCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        String locale = request.getParameter("locale");
        ResourceManager.INSTANCE.changeResource(Locale.forLanguageTag(locale));
        request.getSession().setAttribute("locale", locale);
        LOGGER.info("Locale: " + locale);

        String role = (String) request.getSession().getAttribute("role");
        switch (role) {
            case ("client"):
                return new GetClientPageCommand().execute(request, response);
            case ("admin"):
                return new AdminMainPageCommand().execute(request, response);
            case ("inspector"):
                return new InspectorGetNewReportsCommand().execute(request, response);
            default:
                return null;
        }

    }

}
