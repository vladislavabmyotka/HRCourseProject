package by.bsuir.abmyotkashevtsov.controller.command.impl.adminImpl;

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
 * Implementation of the "admin_employer_edit" command.
 *
 * <p>
 *     According to the employer id from the request is searched in the database and the formation of the object,
 *     which is stored in an attribute request to display on a new page of editing the information of the employer.
 * </p>
 *
 * @see Command#execute(HttpServletRequest)
 */
public class AdminEmployerEditCommand implements Command {
    private final static Logger LOGGER = LogManager.getLogger(AdminEmployerEditCommand.class);

    private EmployerService service;

    /**
     *  Constructs and initialize commands type of 'admin_employer_edit'
     *
     * @param service - instance of the service type of "Employer" to access the database table "employer"
     */
    public AdminEmployerEditCommand(EmployerService service) {
        this.service = service;
    }

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router(PathConstant.PATH_PAGE_ADMIN_EMPLOYER_EDIT, Router.RouteType.FORWARD);

        String stringEmployerId = request.getParameter(ParameterConstant.PARAM_ADMIN_EMPLOYER_EDIT);
        int employerId = 0;
        try {
            employerId = Integer.parseInt(stringEmployerId);
        } catch (NumberFormatException e) {
            LOGGER.log(Level.ERROR, "Error while parsing string value employer id to integer! Detail: " +
                    e.getMessage());
        }

        Employer employer = service.findById(employerId);
        if (employer != null) {
            request.setAttribute("employer", employer);
        } else {
            router.setPagePath(PathConstant.PATH_PAGE_ADMIN_EMPLOYER);
            Object language = request.getSession(true).getAttribute("language");
            String message = MessageManager.getMessage(language.toString(), MessageConstant.ERROR_ON_WEBSITE);
            request.setAttribute("errorMessage", message);
        }

        return router;
    }
}
