package by.bsuir.abmyotkashevtsov.controller.command.impl.employerImpl;

import by.bsuir.abmyotkashevtsov.constant.MessageConstant;
import by.bsuir.abmyotkashevtsov.constant.ParameterConstant;
import by.bsuir.abmyotkashevtsov.constant.PathConstant;
import by.bsuir.abmyotkashevtsov.controller.Router;
import by.bsuir.abmyotkashevtsov.controller.command.Command;
import by.bsuir.abmyotkashevtsov.controller.manager.MessageManager;
import by.bsuir.abmyotkashevtsov.domain.Employer;
import by.bsuir.abmyotkashevtsov.service.EmployerService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static by.bsuir.abmyotkashevtsov.validator.CandidateEmployerVacancyValidator.*;

/**
 * Implementation of the "employer_add_information_save" command.
 *
 * <p>
 *     Accepts all information about the HR to add in database, having previously passed validation.
 * </p>
 *
 * @see Command#execute(HttpServletRequest)
 */
public class EmployerAddInformationSaveCommand implements Command {
    private EmployerService service;

    /**
     *  Constructs and initialize commands type of 'employer_add_information_save'
     *
     * @param service - instance of the service type of "Employer" to access the database table "employer"
     */
    public EmployerAddInformationSaveCommand(EmployerService service) {
        this.service = service;
    }

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router(PathConstant.PATH_PAGE_EMPLOYER, Router.RouteType.FORWARD);

        HttpSession session = request.getSession(true);

        String surname = request.getParameter(ParameterConstant.PARAM_SURNAME);
        String name = request.getParameter(ParameterConstant.PARAM_NAME);
        String lastname = request.getParameter(ParameterConstant.PARAM_LASTNAME);
        String address = request.getParameter(ParameterConstant.PARAM_ADDRESS);
        String phone = request.getParameter(ParameterConstant.PARAM_PHONE);
        String email = request.getParameter(ParameterConstant.PARAM_EMAIL);
        String company = request.getParameter(ParameterConstant.PARAM_COMPANY);
        int accountId = (int)session.getAttribute("employerAccountId");

        if(checkNames(surname) && checkNames(name) && checkLastname(lastname) && checkPhone(phone) &&
                checkEmail(email)) {
            Employer employer = new Employer(surname, name, lastname, address, phone, email, company, accountId);
            if (!service.add(employer)) {
                Object language = request.getSession(true).getAttribute("language");
                String message = MessageManager.getMessage(language.toString(), MessageConstant.ERROR_ON_WEBSITE);
                request.setAttribute("errorMessage", message);
            }
        } else {
            Object language = request.getSession(true).getAttribute("language");
            String message = MessageManager.getMessage(language.toString(), MessageConstant.INCORRECT_DATA);
            request.setAttribute("errorMessage", message);
            router.setPagePath(PathConstant.PATH_PAGE_EMPLOYER_ADD_INFORMATION);
        }

        return router;
    }
}
