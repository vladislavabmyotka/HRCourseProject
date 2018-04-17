package by.bsuir.abmyotkashevtsov.controller.command.impl.candidateImpl;

import by.bsuir.abmyotkashevtsov.constant.MessageConstant;
import by.bsuir.abmyotkashevtsov.constant.PathConstant;
import by.bsuir.abmyotkashevtsov.controller.Router;
import by.bsuir.abmyotkashevtsov.controller.command.Command;
import by.bsuir.abmyotkashevtsov.controller.manager.MessageManager;
import by.bsuir.abmyotkashevtsov.domain.Account;
import by.bsuir.abmyotkashevtsov.service.AccountService;
import by.bsuir.abmyotkashevtsov.service.CandidateService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Implementation of the "candidate_add" command.
 *
 * <p>
 *     Extracts from the session object of type "Account" and it finds a match in the database and returns
 *     account id which is checked if there is already a CV of this candidate in the database and, if successful,
 *     throws to the page adding a CV, otherwise gives an error message that the resume already exists.
 * </p>
 *
 * @see Command#execute(HttpServletRequest)
 */
public class CandidateAddCommand implements Command {
    private AccountService accountService;
    private CandidateService candidateService;

    /**
     *  Constructs and initialize commands type of 'candidate_add'
     *
     * @param accountService - instance of the service type of "Account" to access the database table "account"
     * @param candidateService - instance of the service type of "Candidate" to access the database table "candidate"
     */
    public CandidateAddCommand(AccountService accountService, CandidateService candidateService) {
        this.accountService = accountService;
        this.candidateService = candidateService;
    }

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router(PathConstant.PATH_PAGE_CANDIDATE_ADD, Router.RouteType.FORWARD);

        HttpSession session = request.getSession(true);
        Account candidateAccount = (Account)session.getAttribute("role");
        int accountId = accountService.findAccountIdByLoginPasswordAttachment(candidateAccount);
        if(accountId != 0) {
            if(!candidateService.isExistCandidateByAccountId(accountId)) {
                session.setAttribute("candidateAccountId", accountId);
            } else {
                router.setPagePath(PathConstant.PATH_PAGE_CANDIDATE);
                Object language = request.getSession(true).getAttribute("language");
                String message = MessageManager.getMessage(language.toString(),
                        MessageConstant.CANDIDATE_ALREADY_EXIST);
                request.setAttribute("errorMessage", message);
            }
        } else {
            router.setPagePath(PathConstant.PATH_PAGE_CANDIDATE);
            Object language = request.getSession(true).getAttribute("language");
            String message = MessageManager.getMessage(language.toString(), MessageConstant.ERROR_ON_WEBSITE);
            request.setAttribute("errorMessage", message);
        }

        return router;
    }
}
