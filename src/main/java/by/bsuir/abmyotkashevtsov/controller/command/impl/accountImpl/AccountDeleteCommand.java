package by.bsuir.abmyotkashevtsov.controller.command.impl.accountImpl;

import by.bsuir.abmyotkashevtsov.constant.MessageConstant;
import by.bsuir.abmyotkashevtsov.constant.PathConstant;
import by.bsuir.abmyotkashevtsov.controller.Router;
import by.bsuir.abmyotkashevtsov.controller.command.Command;
import by.bsuir.abmyotkashevtsov.controller.manager.MessageManager;
import by.bsuir.abmyotkashevtsov.domain.Account;
import by.bsuir.abmyotkashevtsov.service.AccountService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Implementation of the "delete_account" command
 *
 * <p>
 *     It takes an object of type "Account" from the session and, based on it, removes the corresponding account
 *     from the database.
 * </p>
 *
 * @see Command#execute(HttpServletRequest)
 */
public class AccountDeleteCommand implements Command {
    private AccountService accountService;

    /**
     *  Constructs and initialize commands type of 'register'
     *
     * @param accountService - instance of the service type of "Account" to access the database table "account"
     */
    public AccountDeleteCommand(AccountService accountService) {
        this.accountService = accountService;
    }

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router(PathConstant.PATH_PAGE_DELETE_ACCOUNT);
        HttpSession session = request.getSession(true);
        Account account = (Account) session.getAttribute("role");

        if (accountService.delete(account)) {
            router.setPagePath(PathConstant.PATH_PAGE_MAIN);
            router.setRoute(Router.RouteType.REDIRECT);
            session.invalidate();
        } else {
            Object language = request.getSession(true).getAttribute("language");
            String message = MessageManager.getMessage(language.toString(), MessageConstant.ERROR_ON_WEBSITE);
            request.setAttribute("errorMessage", message);
        }

        return router;
    }
}
