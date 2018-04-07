package by.bsuir.abmyotkashevtsov.controller.command.impl;

import by.bsuir.abmyotkashevtsov.constant.PathConstant;
import by.bsuir.abmyotkashevtsov.controller.Router;
import by.bsuir.abmyotkashevtsov.controller.command.Command;

import javax.servlet.http.HttpServletRequest;

/**
 * Implementation of the "index" command.
 *
 * It is intended to update the "Authorization" page without any logic
 *
 * @see Command#execute(HttpServletRequest)
 */
public class IndexCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) {
        return new Router(PathConstant.PATH_PAGE_MAIN, Router.RouteType.FORWARD);
    }
}
