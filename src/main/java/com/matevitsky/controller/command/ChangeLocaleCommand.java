package com.matevitsky.controller.command;

import com.matevitsky.controller.command.admin.AdminMainPageCommand;
import com.matevitsky.controller.command.client.GetMainClientPageCommand;
import com.matevitsky.controller.command.inspector.InspectorGetNewReportsCommand;
import com.matevitsky.service.ResourceManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;
import java.util.Objects;


public class ChangeLocaleCommand implements Command {
    private static final Logger LOGGER = Logger.getLogger(ChangeLocaleCommand.class);
    private static final String CLIENT = "client";
    private static final String ADMIN = "admin";
    private static final String INSPECTOR = "inspector";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        String locale = request.getParameter("locale");
        ResourceManager.INSTANCE.changeResource(Locale.forLanguageTag(locale));
        request.getSession().setAttribute("locale", locale);
        LOGGER.info("Locale: " + locale);

        String role = (String) request.getSession().getAttribute("role");
        if (Objects.nonNull(role)) {
            switch (role) {
                case CLIENT:
                    return new GetMainClientPageCommand().execute(request, response);
                case ADMIN:
                    return new AdminMainPageCommand().execute(request, response);
                case INSPECTOR:
                    return new InspectorGetNewReportsCommand().execute(request, response);
            }
        }
        return request.getParameter("uri");

    }

}
