package by.bsuir.abmyotkashevtsov.controller.command.impl.adminImpl;

import by.bsuir.abmyotkashevtsov.constant.MessageConstant;
import by.bsuir.abmyotkashevtsov.constant.ParameterConstant;
import by.bsuir.abmyotkashevtsov.constant.PathConstant;
import by.bsuir.abmyotkashevtsov.controller.Router;
import by.bsuir.abmyotkashevtsov.controller.command.Command;
import by.bsuir.abmyotkashevtsov.controller.manager.MessageManager;
import by.bsuir.abmyotkashevtsov.domain.Vacancy;
import by.bsuir.abmyotkashevtsov.service.EmployerService;
import by.bsuir.abmyotkashevtsov.service.VacancyService;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;

import static by.bsuir.abmyotkashevtsov.validator.CandidateEmployerVacancyValidator.*;

/**
 * Implementation of the "admin_vacancy_edit_save" command.
 *
 * <p>
 *     Accepts all information about the vacancy to update by Administrator, having previously passed validation.
 * </p>
 *
 * @see Command#execute(HttpServletRequest)
 */
public class AdminVacancyEditSaveCommand implements Command {
    private final static Logger LOGGER = LogManager.getLogger(AdminVacancyEditSaveCommand.class);

    private VacancyService vacancyService;
    private EmployerService employerService;

    /**
     *  Constructs and initialize commands type of 'admin_vacancy_edit_save'
     *
     * @param vacancyService - instance of the service type of "Vacancy" to access the database table "vacancy"
     * @param employerService - instance of the service type of "Employer" to access the database table "employer"
     */
    public AdminVacancyEditSaveCommand(VacancyService vacancyService, EmployerService employerService) {
        this.vacancyService = vacancyService;
        this.employerService = employerService;
    }

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router(PathConstant.PATH_PAGE_ADMIN_VACANCY, Router.RouteType.FORWARD);

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
                Command command = new AdminVacancyViewCommand(vacancyService, employerService);
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
            router.setPagePath(PathConstant.PATH_PAGE_ADMIN_VACANCY_EDIT);
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
                router.setPagePath(PathConstant.PATH_PAGE_ADMIN_VACANCY);
                language = request.getSession(true).getAttribute("language");
                message = MessageManager.getMessage(language.toString(), MessageConstant.ERROR_ON_WEBSITE);
                request.setAttribute("errorMessage", message);
            }
        }

        return router;
    }
}
