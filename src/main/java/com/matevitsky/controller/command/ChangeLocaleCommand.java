package com.matevitsky.controller.command;

import com.matevitsky.controller.command.client.GetClientPageCommand;
import com.matevitsky.service.ResourceManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;


public class ChangeLocaleCommand implements Command {
    private static final Logger LOGGER = Logger.getLogger(ChangeLocaleCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOGGER.debug("Method execute started");

        Locale locale = (Locale) request.getSession().getAttribute("locale");
        ResourceManager.INSTANCE.changeResource(locale);
        request.getSession().setAttribute("locale", locale);
        LOGGER.info("Locale: " + locale);


        Integer userId = (Integer) request.getSession().getAttribute("userId");

        return new GetClientPageCommand().execute(request, response);
    }

}
