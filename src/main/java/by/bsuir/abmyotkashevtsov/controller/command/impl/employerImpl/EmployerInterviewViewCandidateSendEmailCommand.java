package by.bsuir.abmyotkashevtsov.controller.command.impl.employerImpl;

import by.bsuir.abmyotkashevtsov.constant.MessageConstant;
import by.bsuir.abmyotkashevtsov.constant.ParameterConstant;
import by.bsuir.abmyotkashevtsov.constant.PathConstant;
import by.bsuir.abmyotkashevtsov.controller.Router;
import by.bsuir.abmyotkashevtsov.controller.command.Command;
import by.bsuir.abmyotkashevtsov.controller.manager.MessageManager;
import by.bsuir.abmyotkashevtsov.domain.Candidate;
import by.bsuir.abmyotkashevtsov.service.CandidateService;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

/**
 * Implementation of the "employer_interview_view_candidate_send_email" command.
 *
 * <p>
 *     Goes to the page for sending a letter to a Candidate.
 * </p>
 *
 * @see Command#execute(HttpServletRequest)
 */
public class EmployerInterviewViewCandidateSendEmailCommand implements Command {
    private final static Logger LOGGER = LogManager.getLogger(EmployerInterviewViewCandidateSendEmailCommand.class);

    private CandidateService service;

    /**
     *  Constructs and initialize commands type of 'employer_interview_view_candidate_send_email'
     *
     * @param service - instance of the service type of "Candidate" to access the database table "candidate"
     */
    public EmployerInterviewViewCandidateSendEmailCommand(CandidateService service) {
        this.service = service;
    }

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router(PathConstant.PATH_PAGE_EMPLOYER_INTERVIEW_VIEW_CANDIDATE_SEND_EMAIL,
                Router.RouteType.FORWARD);

        String stringCandidateId = request.getParameter(ParameterConstant.PARAM_CANDIDATE_ID);
        int candidateId = 0;
        try {
            candidateId = Integer.parseInt(stringCandidateId);
        } catch (NumberFormatException e) {
            LOGGER.log(Level.ERROR, "Error while parsing string value candidateId to integer! Detail: " +
                    e.getMessage());
        }

        Candidate candidate = service.findById(candidateId);
        if (candidate != null) {
            request.setAttribute("candidate", candidate);
        } else {
            Object language = request.getSession(true).getAttribute("language");
            String message = MessageManager.getMessage(language.toString(), MessageConstant.ERROR_ON_WEBSITE);
            request.setAttribute("errorMessage", message);
            router.setPagePath(PathConstant.PATH_PAGE_EMPLOYER);
        }

        return router;
    }
}
