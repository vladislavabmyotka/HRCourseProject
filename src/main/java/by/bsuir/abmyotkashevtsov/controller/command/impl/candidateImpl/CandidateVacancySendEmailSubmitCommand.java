package by.bsuir.abmyotkashevtsov.controller.command.impl.candidateImpl;

import by.bsuir.abmyotkashevtsov.constant.MessageConstant;
import by.bsuir.abmyotkashevtsov.constant.ParameterConstant;
import by.bsuir.abmyotkashevtsov.constant.PathConstant;
import by.bsuir.abmyotkashevtsov.controller.Router;
import by.bsuir.abmyotkashevtsov.controller.command.Command;
import by.bsuir.abmyotkashevtsov.controller.mail.MailThread;
import by.bsuir.abmyotkashevtsov.controller.manager.MessageManager;
import by.bsuir.abmyotkashevtsov.domain.Account;
import by.bsuir.abmyotkashevtsov.domain.Candidate;
import by.bsuir.abmyotkashevtsov.service.AccountService;
import by.bsuir.abmyotkashevtsov.service.CandidateService;
import by.bsuir.abmyotkashevtsov.service.EmployerService;
import by.bsuir.abmyotkashevtsov.service.VacancyService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Objects;
import java.util.Properties;

/**
 * Implementation of the "candidate_vacancy_send_email_submit" command.
 *
 * <p>
 *     Accepts the data: to whom to send the letter, the subject of the letter, the message of the letter and
 *     the password from the e-mail of the candidate. Based on these data, it forms an object of the MailTread
 *     class to send a message.
 * </p>
 *
 * @see Command#execute(HttpServletRequest)
 */
public class CandidateVacancySendEmailSubmitCommand implements Command {
    private AccountService accountService;
    private CandidateService candidateService;
    private EmployerService employerService;
    private VacancyService vacancyService;

    /**
     *  Constructs and initialize commands type of 'candidate_vacancy_send_email_submit'
     *
     * @param accountService - instance of the service type of "Account" to access the database table "account"
     * @param candidateService - instance of the service type of "Candidate" to access the database table "candidate"
     * @param employerService - instance of the service type of "Employer" to access the database table "employer"
     * @param vacancyService - instance of the service type of "Vacancy" to access the database table "vacancy"
     */
    public CandidateVacancySendEmailSubmitCommand(AccountService accountService, CandidateService candidateService,
                                                  EmployerService employerService, VacancyService vacancyService) {
        this.accountService = accountService;
        this.candidateService = candidateService;
        this.employerService = employerService;
        this.vacancyService = vacancyService;
    }

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router(PathConstant.PATH_PAGE_CANDIDATE_VACANCY_SEND_EMAIL, Router.RouteType.FORWARD);

        String to = request.getParameter(ParameterConstant.PARAM_TO);
        String theme = request.getParameter(ParameterConstant.PARAM_THEME);
        String message = request.getParameter(ParameterConstant.PARAM_MESSAGE);
        String password = request.getParameter(ParameterConstant.PARAM_PASSWORD);

        if (!Objects.equals(to, "") & !Objects.equals(theme, "") & !Objects.equals(message, "")) {
            HttpSession session = request.getSession(true);
            Account candidateAccount = (Account) session.getAttribute("role");
            int accountId = accountService.findAccountIdByLoginPasswordAttachment(candidateAccount);

            if (accountId != 0) {
                Candidate candidate = candidateService.findByAccountId(accountId);
                Properties properties = new Properties();
                properties.setProperty("mail.smtp.host", "smtp.gmail.com");
                properties.setProperty("mail.smtp.port", "465");
                properties.setProperty("mail.user.name", candidate.getEmail());
                properties.setProperty("mail.user.password", password);

                MailThread mailOperator = new MailThread(to, candidate.getEmail(), theme, message, properties);
                mailOperator.start();

                Command command = new CandidateVacancyViewCommand(vacancyService, employerService);
                router = command.execute(request);
            } else {
                router.setPagePath(PathConstant.PATH_PAGE_CANDIDATE);
                Object language = request.getSession(true).getAttribute("language");
                String errorMessage = MessageManager.getMessage(language.toString(), MessageConstant.ERROR_ON_WEBSITE);
                request.setAttribute("errorMessage", errorMessage);
            }
        } else {
            Command command = new CandidateVacancySendEmailCommand(employerService);
            router = command.execute(request);
            Object language = request.getSession(true).getAttribute("language");
            String errorMessage = MessageManager.getMessage(language.toString(), MessageConstant.INCORRECT_DATA);
            request.setAttribute("errorMessage", errorMessage);
        }

        return router;
    }
}
