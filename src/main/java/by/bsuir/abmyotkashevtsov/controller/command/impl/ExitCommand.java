package by.bsuir.abmyotkashevtsov.controller.command.impl;

import by.bsuir.abmyotkashevtsov.controller.command.Command;
import by.bsuir.abmyotkashevtsov.controller.Router;

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
