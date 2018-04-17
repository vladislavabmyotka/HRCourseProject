package by.bsuir.abmyotkashevtsov.controller.command.impl.candidateImpl;

import by.bsuir.abmyotkashevtsov.constant.MessageConstant;
import by.bsuir.abmyotkashevtsov.constant.PathConstant;
import by.bsuir.abmyotkashevtsov.controller.Router;
import by.bsuir.abmyotkashevtsov.controller.command.Command;
import by.bsuir.abmyotkashevtsov.controller.manager.MessageManager;
import by.bsuir.abmyotkashevtsov.domain.Account;
import by.bsuir.abmyotkashevtsov.domain.Candidate;
import by.bsuir.abmyotkashevtsov.service.AccountService;
import by.bsuir.abmyotkashevtsov.service.CandidateService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Implementation of the "candidate_view_edit" command.
 *
 * <p>
 *     It takes an object of the "Account" type from the session, finds the candidates id from the database on
 *     it object and throws to the page for viewing and editing the candidate resume.
 * </p>
 *
 * @see Command#execute(HttpServletRequest)
 */
public class CandidateViewEditCommand implements Command {
    private AccountService accountService;
    private CandidateService candidateService;

    /**
     *  Constructs and initialize commands type of 'candidate_view_edit'
     *
     * @param accountService - instance of the service type of "Account" to access the database table "account"
     * @param candidateService - instance of the service type of "Candidate" to access the database table "candidate"
     */
    public CandidateViewEditCommand(AccountService accountService, CandidateService candidateService) {
        this.accountService = accountService;
        this.candidateService = candidateService;
    }

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router(PathConstant.PATH_PAGE_CANDIDATE_VIEW_EDIT, Router.RouteType.FORWARD);

        HttpSession session = request.getSession(true);
        Account candidateAccount = (Account)session.getAttribute("role");
        int accountId = accountService.findAccountIdByLoginPasswordAttachment(candidateAccount);

        if(accountId != 0) {
            Candidate candidate = candidateService.findByAccountId(accountId);
            if (candidate != null) {
                request.setAttribute("candidate", candidate);
            } else {
                router.setPagePath(PathConstant.PATH_PAGE_CANDIDATE);
                Object language = request.getSession(true).getAttribute("language");
                String message = MessageManager.getMessage(language.toString(), MessageConstant.NON_EXIST_CV);
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
