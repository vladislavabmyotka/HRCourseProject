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

import static by.bsuir.abmyotkashevtsov.validator.CandidateEmployerVacancyValidator.*;

/**
 * Implementation of the "admin_employer_edit_save" command.
 *
 * <p>
 *     Accepts all information about the employer to update by Administrator, having previously passed validation.
 * </p>
 *
 * @see Command#execute(HttpServletRequest)
 */
public class AdminEmployerEditSaveCommand implements Command {
    private final static Logger LOGGER = LogManager.getLogger(AdminEmployerEditSaveCommand.class);

    private EmployerService service;

    /**
     *  Constructs and initialize commands type of 'admin_employer_edit_save'
     *
     * @param service - instance of the service type of "Employer" to access the database table "employer"
     */
    public AdminEmployerEditSaveCommand(EmployerService service) {
        this.service = service;
    }

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router(PathConstant.PATH_PAGE_ADMIN_EMPLOYER, Router.RouteType.FORWARD);

        String stringEmployerId = request.getParameter(ParameterConstant.PARAM_EMPLOYER_ID);
        String surname = request.getParameter(ParameterConstant.PARAM_SURNAME);
        String name = request.getParameter(ParameterConstant.PARAM_NAME);
        String lastname = request.getParameter(ParameterConstant.PARAM_LASTNAME);
        String address = request.getParameter(ParameterConstant.PARAM_ADDRESS);
        String phone = request.getParameter(ParameterConstant.PARAM_PHONE);
        String email = request.getParameter(ParameterConstant.PARAM_EMAIL);
        String company = request.getParameter(ParameterConstant.PARAM_COMPANY);

        if(checkID(stringEmployerId) && checkNames(surname) && checkNames(name) && checkLastname(lastname) &&
                checkPhone(phone) && checkEmail(email)) {
            Employer employer = new Employer(Integer.parseInt(stringEmployerId), surname, name, lastname, address,
                    phone, email, company);
            if (service.update(employer)) {
                Command command = new AdminEmployerViewCommand(service);
                command.execute(request);
            } else {
                Object language = request.getSession(true).getAttribute("language");
                String message = MessageManager.getMessage(language.toString(), MessageConstant.ERROR_ON_WEBSITE);
                request.setAttribute("errorMessage", message);
            }
        } else {
            Object language = request.getSession(true).getAttribute("language");
            String message = MessageManager.getMessage(language.toString(), MessageConstant.INCORRECT_DATA);
            request.setAttribute("errorMessage", message);
            router.setPagePath(PathConstant.PATH_PAGE_ADMIN_EMPLOYER_EDIT);
            int employerId = 0;
            try {
                employerId = Integer.parseInt(stringEmployerId);
            } catch (NumberFormatException e) {
                LOGGER.log(Level.ERROR, "Error while parsing string value employerId to integer! Detail: " +
                        e.getMessage());
            }
            Employer employer = service.findById(employerId);
            if (employer != null) {
                request.setAttribute("employer", employer);
            } else {
                router.setPagePath(PathConstant.PATH_PAGE_ADMIN_EMPLOYER);
                language = request.getSession(true).getAttribute("language");
                message = MessageManager.getMessage(language.toString(), MessageConstant.ERROR_ON_WEBSITE);
                request.setAttribute("errorMessage", message);
            }
        }

        return router;
    }
}
