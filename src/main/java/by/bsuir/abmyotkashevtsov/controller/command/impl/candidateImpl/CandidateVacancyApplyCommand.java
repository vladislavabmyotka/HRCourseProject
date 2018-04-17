package by.bsuir.abmyotkashevtsov.controller.command.impl.candidateImpl;

import by.bsuir.abmyotkashevtsov.constant.MessageConstant;
import by.bsuir.abmyotkashevtsov.constant.ParameterConstant;
import by.bsuir.abmyotkashevtsov.constant.PathConstant;
import by.bsuir.abmyotkashevtsov.controller.Router;
import by.bsuir.abmyotkashevtsov.controller.command.Command;
import by.bsuir.abmyotkashevtsov.controller.manager.MessageManager;
import by.bsuir.abmyotkashevtsov.domain.Account;
import by.bsuir.abmyotkashevtsov.domain.Candidate;
import by.bsuir.abmyotkashevtsov.domain.Interview;
import by.bsuir.abmyotkashevtsov.service.*;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Implementation of the "candidate_vacancy_apply" command.
 *
 * <p>
 *     Represents the implementation of clicking on the "Apply" button to the vacancy for the candidate.
 *     After clicking on the button, in the database searching information about this candidate, retrieved his id
 *     and created a new interview based on the candidate's id and the id of the vacancy that was apply.
 * </p>
 *
 * @see Command#execute(HttpServletRequest)
 */
public class CandidateVacancyApplyCommand implements Command {
    private final static Logger LOGGER = LogManager.getLogger(CandidateVacancyApplyCommand.class);

    private AccountService accountService;
    private CandidateService candidateService;
    private VacancyService vacancyService;
    private EmployerService employerService;
    private InterviewService interviewService;

    /**
     *  Constructs and initialize commands type of 'candidate_vacancy_apply'
     *
     * @param accountService - instance of the service type of "Account" to access the database table "account"
     * @param candidateService - instance of the service type of "Candidate" to access the database table "candidate"
     * @param vacancyService - instance of the service type of "Vacancy" to access the database table "vacancy"
     * @param employerService - instance of the service type of "Employer" to access the database table "employer"
     * @param interviewService - instance of the service type of "Interview" to access the database table "interview"
     */
    public CandidateVacancyApplyCommand(AccountService accountService, CandidateService candidateService,
                                        VacancyService vacancyService, EmployerService employerService,
                                        InterviewService interviewService) {
        this.accountService = accountService;
        this.candidateService = candidateService;
        this.vacancyService = vacancyService;
        this.employerService = employerService;
        this.interviewService = interviewService;
    }

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router(PathConstant.PATH_PAGE_CANDIDATE_VACANCY, Router.RouteType.FORWARD);

        HttpSession session = request.getSession(true);
        Account candidateAccount = (Account)session.getAttribute("role");
        int accountId = accountService.findAccountIdByLoginPasswordAttachment(candidateAccount);

        if(accountId != 0) {
            Candidate candidate = candidateService.findByAccountId(accountId);
            if (candidate != null) {
                String stringVacancyId = request.getParameter(ParameterConstant.PARAM_CANDIDATE_VACANCY_APPLY);
                int vacancyId = 0;
                try {
                    vacancyId = Integer.parseInt(stringVacancyId);
                } catch (NumberFormatException e) {
                    LOGGER.log(Level.ERROR, "Error while parsing string value vacancyId to integer! Detail: " +
                            e.getMessage());
                }

                Interview interview = new Interview(candidate.getCandidateId(), vacancyId);
                if (!interviewService.checkForExist(interview)) {
                    if (interviewService.add(interview)) {
                        Command command = new CandidateVacancyViewCommand(vacancyService, employerService);
                        router = command.execute(request);
                        Object object = request.getSession(true).getAttribute("language");
                        String message = MessageManager.getMessage(object.toString(),
                                MessageConstant.CANDIDATE_SUCCESSFULLY_RESPONDED);
                        request.setAttribute("notificationMessage", message);
                    } else {
                        router.setPagePath(PathConstant.PATH_PAGE_CANDIDATE);
                        Object object = request.getSession(true).getAttribute("language");
                        String message = MessageManager.getMessage(object.toString(),
                                MessageConstant.ERROR_ON_WEBSITE);
                        request.setAttribute("errorMessage", message);
                    }
                } else {
                    Object object = request.getSession(true).getAttribute("language");
                    String message = MessageManager.getMessage(object.toString(),
                            MessageConstant.CANDIDATE_ALREADY_RESPONDED_TO_VACANCY);
                    request.setAttribute("errorMessage", message);
                    Command command = new CandidateVacancyViewCommand(vacancyService, employerService);
                    router = command.execute(request);
                }
            } else {
                router.setPagePath(PathConstant.PATH_PAGE_CANDIDATE);
                Object object = request.getSession(true).getAttribute("language");
                String message = MessageManager.getMessage(object.toString(),
                        MessageConstant.NON_EXIST_CV);
                request.setAttribute("errorMessage", message);
            }
        } else {
            router.setPagePath(PathConstant.PATH_PAGE_CANDIDATE);
            Object object = request.getSession(true).getAttribute("language");
            String message = MessageManager.getMessage(object.toString(),
                    MessageConstant.ERROR_ON_WEBSITE);
            request.setAttribute("errorMessage", message);
        }

        return router;
    }
}
