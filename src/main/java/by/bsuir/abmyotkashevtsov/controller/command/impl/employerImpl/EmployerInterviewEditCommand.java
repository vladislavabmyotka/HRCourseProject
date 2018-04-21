package by.bsuir.abmyotkashevtsov.controller.command.impl.employerImpl;

import by.bsuir.abmyotkashevtsov.constant.MessageConstant;
import by.bsuir.abmyotkashevtsov.constant.ParameterConstant;
import by.bsuir.abmyotkashevtsov.constant.PathConstant;
import by.bsuir.abmyotkashevtsov.controller.Router;
import by.bsuir.abmyotkashevtsov.controller.command.Command;
import by.bsuir.abmyotkashevtsov.controller.manager.MessageManager;
import by.bsuir.abmyotkashevtsov.domain.Interview;
import by.bsuir.abmyotkashevtsov.service.InterviewService;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

/**
 * Implementation of the "employer_interview_edit" command.
 *
 * <p>
 *     According to the interview id from the request is searched in the database and the formation of the object,
 *     which is stored in an attribute request to display on a new page of editing the information of the interview
 *     by HR.
 * </p>
 *
 * @see Command#execute(HttpServletRequest)
 */
public class EmployerInterviewEditCommand implements Command {
    private final static Logger LOGGER = LogManager.getLogger(EmployerInterviewEditCommand.class);

    private InterviewService service;

    /**
     *  Constructs and initialize commands type of 'employer_interview_edit'
     *
     * @param service - instance of the service type of "Interview" to access the database table "interview"
     */
    public EmployerInterviewEditCommand(InterviewService service) {
        this.service = service;
    }

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router(PathConstant.PATH_PAGE_EMPLOYER_INTERVIEW_EDIT, Router.RouteType.FORWARD);

        String stringInterviewId = request.getParameter(ParameterConstant.PARAM_EMPLOYER_INTERVIEW_EDIT);
        int interviewId = 0;
        try {
            interviewId = Integer.parseInt(stringInterviewId);
        } catch (NumberFormatException e) {
            LOGGER.log(Level.ERROR, "Error while parsing string value interviewId to integer! Detail: " +
                    e.getMessage());
        }

        Interview interview = service.findById(interviewId);
        if (interview != null) {
            request.setAttribute("interview", interview);
        } else {
            router.setPagePath(PathConstant.PATH_PAGE_EMPLOYER);
            Object language = request.getSession(true).getAttribute("language");
            String message = MessageManager.getMessage(language.toString(), MessageConstant.ERROR_ON_WEBSITE);
            request.setAttribute("errorMessage", message);
        }

        return router;
    }
}
