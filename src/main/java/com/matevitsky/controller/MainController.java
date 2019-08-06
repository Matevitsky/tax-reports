package com.matevitsky.controller;

import com.matevitsky.controller.command.ActionFactory;
import com.matevitsky.controller.command.Command;
import com.matevitsky.controller.constant.PageConstant;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

@WebServlet(urlPatterns = "/app/*")
public class MainController extends HttpServlet {
    private final ActionFactory factory = new ActionFactory();


    private static final Logger LOGGER = Logger.getLogger(MainController.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Command command = factory.defineCommand(request);

        String goTo = command.execute(request, response);

        if (Objects.nonNull(goTo)) {
            request.getRequestDispatcher(goTo).forward(request, response);
        } else {
            LOGGER.error("command.execute returned null");
            response.sendRedirect(PageConstant.ERROR_PAGE);
        }
    }
}
