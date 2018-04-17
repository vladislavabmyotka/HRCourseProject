package by.bsuir.abmyotkashevtsov.controller.command.impl.candidateImpl;

import by.bsuir.abmyotkashevtsov.constant.MessageConstant;
import by.bsuir.abmyotkashevtsov.constant.ParameterConstant;
import by.bsuir.abmyotkashevtsov.constant.PathConstant;
import by.bsuir.abmyotkashevtsov.controller.Router;
import by.bsuir.abmyotkashevtsov.controller.command.Command;
import by.bsuir.abmyotkashevtsov.controller.manager.MessageManager;
import by.bsuir.abmyotkashevtsov.domain.Employer;
import by.bsuir.abmyotkashevtsov.service.EmployerService;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

/**
 * Implementation of the "candidate_vacancy_send_email" command.
 *
 * <p>
 *     Goes to the page for sending a letter to a HR.
 * </p>
 *
 * @see Command#execute(HttpServletRequest)
 */
public class CandidateVacancySendEmailCommand implements Command {
    private final static Logger LOGGER = LogManager.getLogger(CandidateVacancySendEmailCommand.class);

    private EmployerService employerService;

    /**
     *  Constructs and initialize commands type of 'candidate_vacancy_send_email'
     *
     * @param employerService - instance of the service type of "Employer" to access the database table "employer"
     */
    public CandidateVacancySendEmailCommand(EmployerService employerService) {
        this.employerService = employerService;
    }

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router(PathConstant.PATH_PAGE_CANDIDATE_VACANCY_SEND_EMAIL, Router.RouteType.FORWARD);

        String stringEmployerId = request.getParameter(ParameterConstant.PARAM_EMPLOYER_ID);
        int employerId = 0;
        try {
            employerId = Integer.parseInt(stringEmployerId);
        } catch (NumberFormatException e) {
            LOGGER.log(Level.ERROR, "Error while parsing string value employerId to integer! Detail: " +
                    e.getMessage());
        }

        Employer employer = employerService.findById(employerId);
        if (employer != null) {
            request.setAttribute("employer", employer);
        } else {
            Object language = request.getSession(true).getAttribute("language");
            String message = MessageManager.getMessage(language.toString(), MessageConstant.ERROR_ON_WEBSITE);
            request.setAttribute("errorMessage", message);
            router.setPagePath(PathConstant.PATH_PAGE_CANDIDATE);
        }

        return router;
    }
}
