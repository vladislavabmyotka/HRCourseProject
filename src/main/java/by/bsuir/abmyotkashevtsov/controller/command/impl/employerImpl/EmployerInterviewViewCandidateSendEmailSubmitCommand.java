package by.bsuir.abmyotkashevtsov.controller.command.impl.employerImpl;

import by.bsuir.abmyotkashevtsov.constant.MessageConstant;
import by.bsuir.abmyotkashevtsov.constant.ParameterConstant;
import by.bsuir.abmyotkashevtsov.constant.PathConstant;
import by.bsuir.abmyotkashevtsov.controller.Router;
import by.bsuir.abmyotkashevtsov.controller.command.Command;
import by.bsuir.abmyotkashevtsov.controller.mail.MailThread;
import by.bsuir.abmyotkashevtsov.controller.manager.MessageManager;
import by.bsuir.abmyotkashevtsov.domain.Account;
import by.bsuir.abmyotkashevtsov.domain.Employer;
import by.bsuir.abmyotkashevtsov.service.AccountService;
import by.bsuir.abmyotkashevtsov.service.CandidateService;
import by.bsuir.abmyotkashevtsov.service.EmployerService;
import by.bsuir.abmyotkashevtsov.service.InterviewService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Objects;
import java.util.Properties;

/**
 * Implementation of the "employer_interview_view_candidate_send_email_submit" command.
 *
 * <p>
 *     Accepts the data: to whom to send the letter, the subject of the letter, the message of the letter and
 *     the password from the e-mail of the HR. Based on these data, it forms an object of the MailTread
 *     class to send a message to Candidate.
 * </p>
 *
 * @see Command#execute(HttpServletRequest)
 */
public class EmployerInterviewViewCandidateSendEmailSubmitCommand implements Command {
    private AccountService accountService;
    private CandidateService candidateService;
    private EmployerService employerService;
    private InterviewService interviewService;

    /**
     *  Constructs and initialize commands type of 'employer_interview_view_candidate_send_email_submit'
     *
     * @param accountService - instance of the service type of "Account" to access the database table "account"
     * @param candidateService - instance of the service type of "Candidate" to access the database table "candidate"
     * @param employerService - instance of the service type of "Employer" to access the database table "employer"
     * @param interviewService - instance of the service type of "Interview" to access the database table "interview"
     */
    public EmployerInterviewViewCandidateSendEmailSubmitCommand(AccountService accountService,
                                                                CandidateService candidateService, EmployerService employerService, InterviewService interviewService) {
        this.accountService = accountService;
        this.candidateService = candidateService;
        this.employerService = employerService;
        this.interviewService = interviewService;
    }

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router(PathConstant.PATH_PAGE_EMPLOYER_INTERVIEW_VIEW_CANDIDATE_SEND_EMAIL,
                Router.RouteType.FORWARD);

        String to = request.getParameter(ParameterConstant.PARAM_TO);
        String theme = request.getParameter(ParameterConstant.PARAM_THEME);
        String message = request.getParameter(ParameterConstant.PARAM_MESSAGE);
        String password = request.getParameter(ParameterConstant.PARAM_PASSWORD);

        if (!Objects.equals(to, "") & !Objects.equals(theme, "") & !Objects.equals(message, "")) {
            HttpSession session = request.getSession(true);
            Account employerAccount = (Account) session.getAttribute("role");
            int accountId = accountService.findAccountIdByLoginPasswordAttachment(employerAccount);

            if (accountId != 0) {
                Employer employer = employerService.findByAccountId(accountId);
                Properties properties = new Properties();
                properties.setProperty("mail.smtp.host", "smtp.gmail.com");
                properties.setProperty("mail.smtp.port", "465");
                properties.setProperty("mail.user.name", employer.getEmail());
                properties.setProperty("mail.user.password", password);

                MailThread mailOperator = new MailThread(to, employer.getEmail(), theme, message, properties);
                mailOperator.start();

                Command command = new EmployerInterviewViewCommand(accountService, employerService,
                        interviewService);
                router = command.execute(request);
            } else {
                router.setPagePath(PathConstant.PATH_PAGE_EMPLOYER);
                Object language = request.getSession(true).getAttribute("language");
                String errorMessage = MessageManager.getMessage(language.toString(), MessageConstant.ERROR_ON_WEBSITE);
                request.setAttribute("errorMessage", errorMessage);
            }
        } else {
            Command command = new EmployerInterviewViewCandidateSendEmailCommand(candidateService);
            router = command.execute(request);
            Object language = request.getSession(true).getAttribute("language");
            String errorMessage = MessageManager.getMessage(language.toString(), MessageConstant.INCORRECT_DATA);
            request.setAttribute("errorMessage", errorMessage);
        }

        return router;
    }
}
