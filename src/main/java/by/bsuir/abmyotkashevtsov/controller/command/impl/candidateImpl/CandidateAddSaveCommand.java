package by.bsuir.abmyotkashevtsov.controller.command.impl.candidateImpl;

import by.bsuir.abmyotkashevtsov.constant.MessageConstant;
import by.bsuir.abmyotkashevtsov.constant.ParameterConstant;
import by.bsuir.abmyotkashevtsov.constant.PathConstant;
import by.bsuir.abmyotkashevtsov.controller.Router;
import by.bsuir.abmyotkashevtsov.controller.command.Command;
import by.bsuir.abmyotkashevtsov.controller.manager.MessageManager;
import by.bsuir.abmyotkashevtsov.domain.Candidate;
import by.bsuir.abmyotkashevtsov.service.CandidateService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static by.bsuir.abmyotkashevtsov.validator.CandidateEmployerVacancyValidator.*;

/**
 * Implementation of the "candidate_add_save" command.
 *
 * <p>
 *     Accepts all information about the candidate to add in database, having previously passed validation.
 * </p>
 *
 * @see Command#execute(HttpServletRequest)
 */
public class CandidateAddSaveCommand implements Command {
    private CandidateService service;

    /**
     *  Constructs and initialize commands type of 'candidate_add_save'
     *
     * @param service - instance of the service type of "Candidate" to access the database table "candidate"
     */
    public CandidateAddSaveCommand(CandidateService service) {
        this.service = service;
    }

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router(PathConstant.PATH_PAGE_CANDIDATE, Router.RouteType.FORWARD);

        HttpSession session = request.getSession(true);

        String surname = request.getParameter(ParameterConstant.PARAM_SURNAME);
        String name = request.getParameter(ParameterConstant.PARAM_NAME);
        String lastname = request.getParameter(ParameterConstant.PARAM_LASTNAME);
        String age = request.getParameter(ParameterConstant.PARAM_AGE);
        String email = request.getParameter(ParameterConstant.PARAM_EMAIL);
        String address = request.getParameter(ParameterConstant.PARAM_ADDRESS);
        String citizenship = request.getParameter(ParameterConstant.PARAM_CITIZENSHIP);
        String phone = request.getParameter(ParameterConstant.PARAM_PHONE);
        String post = request.getParameter(ParameterConstant.PARAM_POST);
        String education = request.getParameter(ParameterConstant.PARAM_EDUCATION);
        String experience = request.getParameter(ParameterConstant.PARAM_EXPERIENCE);
        String english = request.getParameter(ParameterConstant.PARAM_ENGLISH);
        String skill = request.getParameter(ParameterConstant.PARAM_SKILL);
        int accountId = (int)session.getAttribute("candidateAccountId");

        if(checkNames(surname) && checkNames(name) && checkLastname(lastname) && checkAge(age) && checkEmail(email) &&
                checkCitizenship(citizenship) && checkPhone(phone) && checkExperience(experience)) {
            Candidate candidate = new Candidate(surname, name, lastname, Integer.parseInt(age), email, address,
                    citizenship, phone, post, education, Integer.parseInt(experience), english, skill, accountId);
            if (!service.add(candidate)) {
                Object language = request.getSession(true).getAttribute("language");
                String message = MessageManager.getMessage(language.toString(), MessageConstant.ERROR_ON_WEBSITE);
                request.setAttribute("errorMessage", message);
            }
        } else {
            Object language = request.getSession(true).getAttribute("language");
            String message = MessageManager.getMessage(language.toString(), MessageConstant.INCORRECT_DATA);
            request.setAttribute("errorMessage", message);
            router.setPagePath(PathConstant.PATH_PAGE_CANDIDATE_ADD);
        }

        return router;
    }
}
