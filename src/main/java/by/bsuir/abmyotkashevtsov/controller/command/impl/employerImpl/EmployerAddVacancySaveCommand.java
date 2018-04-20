package by.bsuir.abmyotkashevtsov.controller.command.impl.employerImpl;

import by.bsuir.abmyotkashevtsov.constant.MessageConstant;
import by.bsuir.abmyotkashevtsov.constant.ParameterConstant;
import by.bsuir.abmyotkashevtsov.constant.PathConstant;
import by.bsuir.abmyotkashevtsov.controller.Router;
import by.bsuir.abmyotkashevtsov.controller.command.Command;
import by.bsuir.abmyotkashevtsov.controller.manager.MessageManager;
import by.bsuir.abmyotkashevtsov.domain.Employer;
import by.bsuir.abmyotkashevtsov.domain.Vacancy;
import by.bsuir.abmyotkashevtsov.service.EmployerService;
import by.bsuir.abmyotkashevtsov.service.VacancyService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;

import static by.bsuir.abmyotkashevtsov.validator.CandidateEmployerVacancyValidator.*;

/**
 * Implementation of the "employer_add_vacancy_save" command.
 *
 * <p>
 *     Accepts all information about the vacancy to add in database, having previously passed validation.
 * </p>
 *
 * @see Command#execute(HttpServletRequest)
 */
public class EmployerAddVacancySaveCommand implements Command {
    private EmployerService employerService;
    private VacancyService vacancyService;

    /**
     *  Constructs and initialize commands type of 'employer_add_vacancy_save'
     *
     * @param employerService - instance of the service type of "Employer" to access the database table "employer"
     * @param vacancyService - instance of the service type of "Vacancy" to access the database table "vacancy"
     */
    public EmployerAddVacancySaveCommand(EmployerService employerService, VacancyService vacancyService) {
        this.employerService = employerService;
        this.vacancyService = vacancyService;
    }

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router(PathConstant.PATH_PAGE_EMPLOYER, Router.RouteType.FORWARD);

        HttpSession session = request.getSession(true);

        String post = request.getParameter(ParameterConstant.PARAM_POST);
        String company = request.getParameter(ParameterConstant.PARAM_COMPANY);
        String salary = request.getParameter(ParameterConstant.PARAM_SALARY);
        String location = request.getParameter(ParameterConstant.PARAM_LOCATION);
        String experience = request.getParameter(ParameterConstant.PARAM_EXPERIENCE);
        String english = request.getParameter(ParameterConstant.PARAM_ENGLISH);
        String text = request.getParameter(ParameterConstant.PARAM_TEXT);
        String conditionVacancy = request.getParameter(ParameterConstant.PARAM_CONDITION_VACANCY);
        int accountId = (int)session.getAttribute("employerAccountId");

        Employer employer = employerService.findByAccountId(accountId);
        if (employer != null) {
            if (checkSalary(salary) && checkExperience(experience)) {
                int employerId = employer.getEmployerId();
                Vacancy vacancy = new Vacancy(post, company, new BigDecimal(salary), location,
                        Integer.parseInt(experience), english, text, conditionVacancy, employerId);
                if (!vacancyService.add(vacancy)) {
                    Object language = request.getSession(true).getAttribute("language");
                    String message = MessageManager.getMessage(language.toString(), MessageConstant.ERROR_ON_WEBSITE);
                    request.setAttribute("errorMessage", message);
                }
            } else {
                Object language = request.getSession(true).getAttribute("language");
                String message = MessageManager.getMessage(language.toString(), MessageConstant.INCORRECT_DATA);
                request.setAttribute("errorMessage", message);
                router.setPagePath(PathConstant.PATH_PAGE_EMPLOYER_ADD_VACANCY);
            }
        } else {
            Object language = request.getSession(true).getAttribute("language");
            String message = MessageManager.getMessage(language.toString(), MessageConstant.ERROR_ON_WEBSITE);
            request.setAttribute("errorMessage", message);
        }

        return router;
    }
}
