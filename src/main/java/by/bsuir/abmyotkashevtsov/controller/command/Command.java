package by.bsuir.abmyotkashevtsov.controller.command;

import by.bsuir.abmyotkashevtsov.controller.Router;

import javax.servlet.http.HttpServletRequest;

/**
 * The root interface in the command hierarchy.
 */
public interface Command {
    Router execute(HttpServletRequest request);
}
