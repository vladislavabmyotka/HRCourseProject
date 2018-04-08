package by.bsuir.abmyotkashevtsov.controller.command.impl.accountImpl;

import by.bsuir.abmyotkashevtsov.constant.AccountAttachmentConstant;
import by.bsuir.abmyotkashevtsov.constant.MessageConstant;
import by.bsuir.abmyotkashevtsov.constant.ParameterConstant;
import by.bsuir.abmyotkashevtsov.constant.PathConstant;
import by.bsuir.abmyotkashevtsov.controller.Router;
import by.bsuir.abmyotkashevtsov.controller.command.Command;
import by.bsuir.abmyotkashevtsov.controller.manager.MessageManager;
import by.bsuir.abmyotkashevtsov.domain.Account;
import by.bsuir.abmyotkashevtsov.service.AccountService;
import by.bsuir.abmyotkashevtsov.validator.AccountValidator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Implementation of the "edit_account_data" command
 *
 * <p>
 *     Accepts login, old password, new password, repeated new password for changing account data. If they are
 *     validated, in the database searching account by old password, and the data is updated based on it.
 *     Otherwise - error message.
 * </p>
 *
 * @see Command#execute(HttpServletRequest)
 */
public class AccountEditCommand implements Command {
    private AccountService service;

    /**
     *  Constructs and initialize commands type of 'register'
     *
     * @param service - instance of the service type of "Account" to access the database table "account"
     */
    public AccountEditCommand(AccountService service) {
        this.service = service;
    }

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router(PathConstant.PATH_PAGE_EDIT_ACCOUNT);
        String login = request.getParameter(ParameterConstant.PARAM_LOGIN);
        String oldPassword = request.getParameter(ParameterConstant.PARAM_OLD_PASSWORD);
        String password = request.getParameter(ParameterConstant.PARAM_PASSWORD);
        String repeatPassword = request.getParameter(ParameterConstant.PARAM_REPEAT_PASSWORD);

        HttpSession session = request.getSession(true);
        String attachment = ((Account) session.getAttribute("role")).getAttachment();

        if(AccountValidator.checkLogin(login) && AccountValidator.checkPassword(oldPassword) &&
                AccountValidator.checkPassword(password) && password.equals(repeatPassword)) {
            Account user = new Account(login, password, attachment);
            int accountId = service.findAccountIdByPassword(oldPassword);
            if (accountId != 0) {
                user.setAccountId(accountId);
                if (service.update(user)) {
                    session.setAttribute("role", user);
                    attachment = ((Account) session.getAttribute("role")).getAttachment();
                    if (attachment.equals(AccountAttachmentConstant.CANDIDATE_ATTACHMENT)) {
                        router.setPagePath(PathConstant.PATH_PAGE_CANDIDATE);
                        router.setRoute(Router.RouteType.REDIRECT);
                    } else {
                        router.setPagePath(PathConstant.PATH_PAGE_EMPLOYER);
                        router.setRoute(Router.RouteType.REDIRECT);
                    }
                } else {
                    Object language = request.getSession(true).getAttribute("language");
                    String message = MessageManager.getMessage(language.toString(), MessageConstant.ERROR_ON_WEBSITE);
                    request.setAttribute("errorLoginPassMessage", message);
                }
            } else {
                Object language = request.getSession(true).getAttribute("language");
                String message = MessageManager.getMessage(language.toString(),
                        MessageConstant.INCORRECT_PASSWORD_MESSAGE);
                request.setAttribute("errorLoginPassMessage", message);
            }
        } else {
            Object language = request.getSession(true).getAttribute("language");
            String message = MessageManager.getMessage(language.toString(),
                    MessageConstant.INCORRECT_LOGIN_PASSWORD_MESSAGE);
            request.setAttribute("errorLoginPassMessage", message);
        }
        return router;
    }
}
