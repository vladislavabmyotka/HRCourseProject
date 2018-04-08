package by.bsuir.abmyotkashevtsov.controller.command.impl;

import by.bsuir.abmyotkashevtsov.constant.MessageConstant;
import by.bsuir.abmyotkashevtsov.constant.ParameterConstant;
import by.bsuir.abmyotkashevtsov.constant.PathConstant;
import by.bsuir.abmyotkashevtsov.controller.Router;
import by.bsuir.abmyotkashevtsov.controller.command.Command;
import by.bsuir.abmyotkashevtsov.controller.creator.AdminCreator;
import by.bsuir.abmyotkashevtsov.controller.manager.MessageManager;
import by.bsuir.abmyotkashevtsov.domain.Account;
import by.bsuir.abmyotkashevtsov.service.AccountService;
import by.bsuir.abmyotkashevtsov.validator.AccountValidator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static by.bsuir.abmyotkashevtsov.constant.AccountAttachmentConstant.ADMIN_ATTACHMENT;
import static by.bsuir.abmyotkashevtsov.constant.AccountAttachmentConstant.CANDIDATE_ATTACHMENT;
import static by.bsuir.abmyotkashevtsov.constant.AccountAttachmentConstant.EMPLOYER_ATTACHMENT;

/**
 * Implementation of the "authorization" command
 *
 * <p>
 *     Takes a username and password. If the data are validated, and they are available in the database
 *     (i.e. the account was indeed created earlier) based on them it creates an object of type "Account" and saved
 *     in the session. Otherwise, you will receive a message about invalid data.
 * </p>
 *
 * @see Command#execute(HttpServletRequest)
 */
public class AuthorizationCommand implements Command {
    private AccountService service;

    /**
     *  Constructs and initialize commands type of 'authorization'
     *
     * @param service - instance of the service type of "Account" to access the database table "account"
     */
    public AuthorizationCommand(AccountService service) {
        this.service = service;
    }

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();
        String login = request.getParameter(ParameterConstant.PARAM_LOGIN);
        String password = request.getParameter(ParameterConstant.PARAM_PASSWORD);

        HttpSession session = request.getSession(true);

        String message;

        if(AccountValidator.checkLogin(login) && AccountValidator.checkPassword(password)) {
            Account user = new Account(login, password);
            AdminCreator creator = new AdminCreator();
            Account admin = creator.createAdmin();

            Account potentialAdmin = new Account(user.getLogin(), user.getPassword(), ADMIN_ATTACHMENT);

            if (admin.equals(potentialAdmin)) {
                session.setAttribute("role", potentialAdmin);
                router.setPagePath(PathConstant.PATH_PAGE_ADMIN);
                router.setRoute(Router.RouteType.FORWARD);
            } else {
                Account role = service.find(user);
                router.setRoute(Router.RouteType.FORWARD);
                if (role != null) {
                    session.setAttribute("role", role);
                    if (role.getAttachment().equals(CANDIDATE_ATTACHMENT))  {
                        router.setPagePath(PathConstant.PATH_PAGE_CANDIDATE);
                    } else if (role.getAttachment().equals(EMPLOYER_ATTACHMENT)) {
                        router.setPagePath(PathConstant.PATH_PAGE_EMPLOYER);
                    }
                } else {
                    Object language = request.getSession(true).getAttribute("language");
                    message = MessageManager.getMessage(language.toString(),
                            MessageConstant.INCORRECT_LOGIN_PASSWORD_MESSAGE);
                    request.setAttribute("errorMessage", message);
                    router.setPagePath(PathConstant.PATH_PAGE_MAIN);
                }
            }
        } else {
            Object language = request.getSession(true).getAttribute("language");
            message = MessageManager.getMessage(language.toString(), MessageConstant.INCORRECT_LOGIN_PASSWORD_MESSAGE);
            request.setAttribute("errorMessage", message);
            router.setPagePath(PathConstant.PATH_PAGE_MAIN);
        }

        return router;
    }
}
