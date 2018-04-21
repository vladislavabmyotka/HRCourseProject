package by.bsuir.abmyotkashevtsov.controller.command.impl.employerImpl;

import by.bsuir.abmyotkashevtsov.constant.MessageConstant;
import by.bsuir.abmyotkashevtsov.constant.PathConstant;
import by.bsuir.abmyotkashevtsov.controller.Router;
import by.bsuir.abmyotkashevtsov.controller.command.Command;
import by.bsuir.abmyotkashevtsov.controller.manager.MessageManager;
import by.bsuir.abmyotkashevtsov.domain.Account;
import by.bsuir.abmyotkashevtsov.domain.Employer;
import by.bsuir.abmyotkashevtsov.domain.Interview;
import by.bsuir.abmyotkashevtsov.service.AccountService;
import by.bsuir.abmyotkashevtsov.service.EmployerService;
import by.bsuir.abmyotkashevtsov.service.InterviewService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Implementation of the "employer_interview_view" command.
 *
 * <p>
 *     Retrieves from the database information about all the interviews, and brief information about the candidate and
 *     the HR for each interview and puts in a request attribute to display on the page to view information about
 *     the interviews.
 * </p>
 *
 * @see Command#execute(HttpServletRequest)
 */
public class EmployerInterviewViewCommand implements Command {
    private AccountService accountService;
    private EmployerService employerService;
    private InterviewService interviewService;

    /**
     *  Constructs and initialize commands type of 'employer_interview_view'
     *
     * @param accountService - instance of the service type of "Account" to access the database table "account"
     * @param employerService - instance of the service type of "Employer" to access the database table "employer"
     * @param interviewService - instance of the service type of "Interview" to access the database table "interview"
     */
    public EmployerInterviewViewCommand(AccountService accountService, EmployerService employerService,
                                        InterviewService interviewService) {
        this.accountService = accountService;
        this.employerService = employerService;
        this.interviewService = interviewService;
    }

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router(PathConstant.PATH_PAGE_EMPLOYER_INTERVIEW, Router.RouteType.FORWARD);

        HttpSession session = request.getSession(true);
        Account employerAccount = (Account)session.getAttribute("role");
        int accountId = accountService.findAccountIdByLoginPasswordAttachment(employerAccount);

        if(accountId != 0) {
            Employer employer = employerService.findByAccountId(accountId);
            if (employer != null) {
                int employerId = employer.getEmployerId();
                List<Interview> interviews = interviewService.findAllByEmployerId(employerId);
                if (interviews.size() != 0) {
                    request.setAttribute("interviewList", interviews);
                } else {
                    router.setPagePath(PathConstant.PATH_PAGE_EMPLOYER);
                    Object language = request.getSession(true).getAttribute("language");
                    String message = MessageManager.getMessage(language.toString(),
                            MessageConstant.EMPLOYER_NON_INTERVIEW);
                    request.setAttribute("errorMessage", message);
                }
            } else {
                router.setPagePath(PathConstant.PATH_PAGE_EMPLOYER);
                Object language = request.getSession(true).getAttribute("language");
                String message = MessageManager.getMessage(language.toString(),
                        MessageConstant.EMPLOYER_NON_INFO_BEFORE_VIEW_INTERVIEW);
                request.setAttribute("errorMessage", message);
            }
        } else {
            router.setPagePath(PathConstant.PATH_PAGE_EMPLOYER);
            Object language = request.getSession(true).getAttribute("language");
            String message = MessageManager.getMessage(language.toString(), MessageConstant.ERROR_ON_WEBSITE);
            request.setAttribute("errorMessage", message);
        }

        return router;
    }
}
