package by.bsuir.abmyotkashevtsov.controller.command.impl.employerImpl;

import by.bsuir.abmyotkashevtsov.constant.MessageConstant;
import by.bsuir.abmyotkashevtsov.constant.PathConstant;
import by.bsuir.abmyotkashevtsov.controller.Router;
import by.bsuir.abmyotkashevtsov.controller.command.Command;
import by.bsuir.abmyotkashevtsov.controller.manager.MessageManager;
import by.bsuir.abmyotkashevtsov.domain.Account;
import by.bsuir.abmyotkashevtsov.domain.Employer;
import by.bsuir.abmyotkashevtsov.domain.Vacancy;
import by.bsuir.abmyotkashevtsov.service.AccountService;
import by.bsuir.abmyotkashevtsov.service.EmployerService;
import by.bsuir.abmyotkashevtsov.service.VacancyService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Implementation of the "employer_view_vacancy" command.
 *
 * <p>
 *     Retrieves from the database information about all the vacancies created by this HR, and brief information
 *     about the candidate for each interview and puts in a request attribute to display on the page to view
 *     information about the interviews.
 * </p>
 *
 * @see Command#execute(HttpServletRequest)
 */
public class EmployerViewVacancyCommand implements Command {
    private AccountService accountService;
    private EmployerService employerService;
    private VacancyService vacancyService;

    /**
     *  Constructs and initialize commands type of 'employer_view_vacancy'
     *
     * @param accountService - instance of the service type of "Account" to access the database table "account"
     * @param employerService - instance of the service type of "Employer" to access the database table "employer"
     * @param vacancyService - instance of the service type of "Vacancy" to access the database table "vacancy"
     */
    public EmployerViewVacancyCommand(AccountService accountService, EmployerService employerService,
                                      VacancyService vacancyService) {
        this.accountService = accountService;
        this.employerService = employerService;
        this.vacancyService = vacancyService;
    }

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router(PathConstant.PATH_PAGE_EMPLOYER_VIEW_VACANCY, Router.RouteType.FORWARD);

        HttpSession session = request.getSession(true);
        Account employerAccount = (Account)session.getAttribute("role");
        int accountId = accountService.findAccountIdByLoginPasswordAttachment(employerAccount);

        if(accountId != 0) {
            Employer employer = employerService.findByAccountId(accountId);
            if (employer != null) {
                int employerId = employer.getEmployerId();
                List<Vacancy> vacancies = vacancyService.findAllByEmployerId(employerId);
                if (vacancies.size() != 0) {
                    request.setAttribute("vacancyList", vacancies);
                } else {
                    router.setPagePath(PathConstant.PATH_PAGE_EMPLOYER);
                    Object language = request.getSession(true).getAttribute("language");
                    String message = MessageManager.getMessage(language.toString(),
                            MessageConstant.EMPLOYER_NOT_ADD_VACANCIES);
                    request.setAttribute("errorMessage", message);
                }
            } else {
                router.setPagePath(PathConstant.PATH_PAGE_EMPLOYER);
                Object language = request.getSession(true).getAttribute("language");
                String message = MessageManager.getMessage(language.toString(),
                        MessageConstant.EMPLOYER_NON_INFO_BEFORE_VIEW_EDIT_VACANCY);
                request.setAttribute("errorMessage", message);
            }
        } else {
            router.setPagePath(PathConstant.PATH_PAGE_EMPLOYER);
            Object language = request.getSession(true).getAttribute("language");
            String message = MessageManager.getMessage(language.toString(), MessageConstant.ERROR_ON_WEBSITE);
            request.setAttribute("errorMessage", message);
        }

        return router;
    }
}
