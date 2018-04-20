package by.bsuir.abmyotkashevtsov.controller.command.impl.employerImpl;

import by.bsuir.abmyotkashevtsov.constant.MessageConstant;
import by.bsuir.abmyotkashevtsov.constant.ParameterConstant;
import by.bsuir.abmyotkashevtsov.constant.PathConstant;
import by.bsuir.abmyotkashevtsov.controller.Router;
import by.bsuir.abmyotkashevtsov.controller.command.Command;
import by.bsuir.abmyotkashevtsov.controller.manager.MessageManager;
import by.bsuir.abmyotkashevtsov.domain.Vacancy;
import by.bsuir.abmyotkashevtsov.service.AccountService;
import by.bsuir.abmyotkashevtsov.service.EmployerService;
import by.bsuir.abmyotkashevtsov.service.VacancyService;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;

import static by.bsuir.abmyotkashevtsov.validator.CandidateEmployerVacancyValidator.*;

/**
 * Implementation of the "employer_vacancy_edit_save" command.
 *
 * <p>
 *     Accepts all information about the vacancy to update by HR, having previously passed validation.
 * </p>
 *
 * @see Command#execute(HttpServletRequest)
 */
public class EmployerVacancyEditSaveCommand implements Command {
    private final static Logger LOGGER = LogManager.getLogger(EmployerVacancyEditSaveCommand.class);

    private AccountService accountService;
    private EmployerService employerService;
    private VacancyService vacancyService;

    /**
     *  Constructs and initialize commands type of 'employer_vacancy_edit_save'
     *
     * @param accountService - instance of the service type of "Account" to access the database table "account"
     * @param employerService - instance of the service type of "Employer" to access the database table "employer"
     * @param vacancyService - instance of the service type of "Vacancy" to access the database table "vacancy"
     */
    public EmployerVacancyEditSaveCommand(AccountService accountService, EmployerService employerService,
                                          VacancyService vacancyService) {
        this.accountService = accountService;
        this.employerService = employerService;
        this.vacancyService = vacancyService;
    }

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router(PathConstant.PATH_PAGE_EMPLOYER_VIEW_VACANCY, Router.RouteType.FORWARD);

        String stringVacancyId = request.getParameter(ParameterConstant.PARAM_VACANCY_ID);
        String post = request.getParameter(ParameterConstant.PARAM_POST);
        String company = request.getParameter(ParameterConstant.PARAM_COMPANY);
        String salary = request.getParameter(ParameterConstant.PARAM_SALARY);
        String location = request.getParameter(ParameterConstant.PARAM_LOCATION);
        String experience = request.getParameter(ParameterConstant.PARAM_EXPERIENCE);
        String english = request.getParameter(ParameterConstant.PARAM_ENGLISH);
        String text = request.getParameter(ParameterConstant.PARAM_TEXT);
        String conditionVacancy = request.getParameter(ParameterConstant.PARAM_CONDITION_VACANCY);

        if(checkID(stringVacancyId) && checkSalary(salary) && checkExperience(experience)) {
            Vacancy vacancy = new Vacancy(Integer.parseInt(stringVacancyId), post, company, new BigDecimal(salary),
                    location, Integer.parseInt(experience), english, text, conditionVacancy);
            if (vacancyService.update(vacancy)) {
                Command command = new EmployerViewVacancyCommand(accountService, employerService, vacancyService);
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
            router.setPagePath(PathConstant.PATH_PAGE_EMPLOYER_EDIT_VACANCY);
            int vacancyId = 0;
            try {
                vacancyId = Integer.parseInt(stringVacancyId);
            } catch (NumberFormatException e) {
                LOGGER.log(Level.ERROR, "Error while parsing string value vacancyId to integer! Detail: " +
                        e.getMessage());
            }
            Vacancy vacancy = vacancyService.findById(vacancyId);
            if (vacancy != null) {
                request.setAttribute("vacancy", vacancy);
            } else {
                router.setPagePath(PathConstant.PATH_PAGE_EMPLOYER_VIEW_VACANCY);
                language = request.getSession(true).getAttribute("language");
                message = MessageManager.getMessage(language.toString(), MessageConstant.ERROR_ON_WEBSITE);
                request.setAttribute("errorMessage", message);
            }
        }

        return router;
    }
}
