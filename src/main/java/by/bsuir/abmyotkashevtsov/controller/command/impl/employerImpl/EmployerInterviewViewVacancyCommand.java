package by.bsuir.abmyotkashevtsov.controller.command.impl.employerImpl;

import by.bsuir.abmyotkashevtsov.constant.MessageConstant;
import by.bsuir.abmyotkashevtsov.constant.ParameterConstant;
import by.bsuir.abmyotkashevtsov.constant.PathConstant;
import by.bsuir.abmyotkashevtsov.controller.Router;
import by.bsuir.abmyotkashevtsov.controller.command.Command;
import by.bsuir.abmyotkashevtsov.controller.manager.MessageManager;
import by.bsuir.abmyotkashevtsov.domain.Vacancy;
import by.bsuir.abmyotkashevtsov.service.VacancyService;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

/**
 * Implementation of the "employer_interview_view_vacancy" command.
 *
 * <p>
 *     Retrieves from request vacancy id, finds vacancy in the database, writes it into the request attribute and
 *     throws to the page view information about the vacancy from the view of the interview page.
 * </p>
 *
 * @see Command#execute(HttpServletRequest)
 */
public class EmployerInterviewViewVacancyCommand implements Command {
    private final static Logger LOGGER = LogManager.getLogger(EmployerInterviewViewVacancyCommand.class);

    private VacancyService service;

    /**
     *  Constructs and initialize commands type of 'employer_interview_view_vacancy'
     *
     * @param service - instance of the service type of "Vacancy" to access the database table "vacancy"
     */
    public EmployerInterviewViewVacancyCommand(VacancyService service) {
        this.service = service;
    }

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router(PathConstant.PATH_PAGE_EMPLOYER_INTERVIEW_VIEW_VACANCY, Router.RouteType.FORWARD);

        String stringVacancyId = request.getParameter(ParameterConstant.PARAM_EMPLOYER_INTERVIEW_VIEW_VACANCY);
        int vacancyId = 0;
        try {
            vacancyId = Integer.parseInt(stringVacancyId);
        } catch (NumberFormatException e) {
            LOGGER.log(Level.ERROR, "Error while parsing string value vacancyId to integer! Detail: " +
                    e.getMessage());
        }

        Vacancy vacancy = service.findById(vacancyId);
        if (vacancy != null) {
            request.setAttribute("vacancy", vacancy);
        } else {
            router.setPagePath(PathConstant.PATH_PAGE_EMPLOYER);
            Object language = request.getSession(true).getAttribute("language");
            String message = MessageManager.getMessage(language.toString(), MessageConstant.ERROR_ON_WEBSITE);
            request.setAttribute("errorMessage", message);
        }

        return router;
    }
}
