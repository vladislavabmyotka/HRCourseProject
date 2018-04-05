package com.epam.abmyotka.hr.command.impl;

import com.epam.abmyotka.hr.command.Command;
import com.epam.abmyotka.hr.controller.Router;

import javax.servlet.http.HttpServletRequest;

/**
 * Default command in FrontController.
 *
 * The command runs if the hidden field has an empty value. Used to logout from your account.
 *
 * @see Command#execute(HttpServletRequest)
 */
public class ExitCommand implements Command {

    @Override
    public Router execute(HttpServletRequest request) {
        return null;
    }
}
