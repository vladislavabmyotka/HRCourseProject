package by.bsuir.abmyotkashevtsov.controller.command.impl.employerImpl;

import by.bsuir.abmyotkashevtsov.constant.MessageConstant;
import by.bsuir.abmyotkashevtsov.constant.ParameterConstant;
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
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Implementation of the "employer_interview_delete" command.
 *
 * <p>
 *     Removes the interview by it's id by HR.
 * </p>
 *
 * @see Command#execute(HttpServletRequest)
 */
public class EmployerInterviewDeleteCommand implements Command {
    private final static Logger LOGGER = LogManager.getLogger(EmployerInterviewDeleteCommand.class);

    private AccountService accountService;
    private EmployerService employerService;
    private InterviewService interviewService;

    /**
     *  Constructs and initialize commands type of 'employer_interview_delete'
     *
     * @param accountService - instance of the service type of "Account" to access the database table "account"
     * @param employerService - instance of the service type of "Employer" to access the database table "employer"
     * @param interviewService - instance of the service type of "Interview" to access the database table "interview"
     */
    public EmployerInterviewDeleteCommand(AccountService accountService, EmployerService employerService,
                                          InterviewService interviewService) {
        this.accountService = accountService;
        this.employerService = employerService;
        this.interviewService = interviewService;
    }

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router(PathConstant.PATH_PAGE_EMPLOYER_INTERVIEW, Router.RouteType.FORWARD);

        String stringInterviewId = request.getParameter(ParameterConstant.PARAM_EMPLOYER_INTERVIEW_DELETE);

        int interviewId = 0;
        try {
            interviewId = Integer.parseInt(stringInterviewId);
        } catch (NumberFormatException e) {
            LOGGER.log(Level.ERROR, "Error while parsing string value interviewId to integer! Detail: " +
                    e.getMessage());
        }

        if (!interviewService.delete(interviewId)) {
            Object language = request.getSession(true).getAttribute("language");
            String message = MessageManager.getMessage(language.toString(), MessageConstant.ERROR_ON_WEBSITE);
            request.setAttribute("errorMessage", message);
        }

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
                }
            } else {
                router.setPagePath(PathConstant.PATH_PAGE_EMPLOYER);
                Object language = request.getSession(true).getAttribute("language");
                String message = MessageManager.getMessage(language.toString(),
                        MessageConstant.NON_EMPLOYER_INFORMATION);
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
