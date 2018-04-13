package by.bsuir.abmyotkashevtsov.controller.command.impl.adminImpl;

import by.bsuir.abmyotkashevtsov.constant.MessageConstant;
import by.bsuir.abmyotkashevtsov.constant.ParameterConstant;
import by.bsuir.abmyotkashevtsov.constant.PathConstant;
import by.bsuir.abmyotkashevtsov.controller.Router;
import by.bsuir.abmyotkashevtsov.controller.command.Command;
import by.bsuir.abmyotkashevtsov.controller.manager.MessageManager;
import by.bsuir.abmyotkashevtsov.domain.Candidate;
import by.bsuir.abmyotkashevtsov.service.CandidateService;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

import static by.bsuir.abmyotkashevtsov.validator.CandidateEmployerVacancyValidator.*;

/**
 * Implementation of the "admin_candidate_edit_save" command.
 *
 * <p>
 *     Accepts all information about the candidate to update by Administrator, having previously passed validation.
 * </p>
 *
 * @see Command#execute(HttpServletRequest)
 */
public class AdminCandidateEditSaveCommand implements Command {
    private final static Logger LOGGER = LogManager.getLogger(AdminCandidateEditSaveCommand.class);

    private CandidateService service;

    /**
     *  Constructs and initialize commands type of 'admin_candidate_edit_save'
     *
     * @param service - instance of the service type of "Candidate" to access the database table "candidate"
     */
    public AdminCandidateEditSaveCommand(CandidateService service) {
        this.service = service;
    }

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router(PathConstant.PATH_PAGE_ADMIN_CANDIDATE, Router.RouteType.FORWARD);

        String stringCandidateId = request.getParameter(ParameterConstant.PARAM_CANDIDATE_ID);
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

        if(checkID(stringCandidateId) && checkNames(surname) && checkNames(name) && checkLastname(lastname) &&
                checkAge(age) && checkEmail(email) && checkCitizenship(citizenship) && checkPhone(phone) &&
                checkExperience(experience)) {
            Candidate candidate = new Candidate(Integer.parseInt(stringCandidateId), surname, name, lastname,
                    Integer.parseInt(age), email, address, citizenship, phone, post, education,
                    Integer.parseInt(experience), english, skill);
            if (service.update(candidate)) {
                Command command = new AdminCandidateViewCommand(service);
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
            router.setPagePath(PathConstant.PATH_PAGE_ADMIN_CANDIDATE_EDIT);
            int candidateId = 0;
            try {
                candidateId = Integer.parseInt(stringCandidateId);
            } catch (NumberFormatException e) {
                LOGGER.log(Level.ERROR, "Error while parsing string value candidateId to integer! Detail: " +
                        e.getMessage());
            }
            Candidate candidate = service.findById(candidateId);
            if (candidate != null) {
                request.setAttribute("candidate", candidate);
            } else {
                router.setPagePath(PathConstant.PATH_PAGE_ADMIN_CANDIDATE);
                language = request.getSession(true).getAttribute("language");
                message = MessageManager.getMessage(language.toString(), MessageConstant.ERROR_ON_WEBSITE);
                request.setAttribute("errorMessage", message);
            }
        }

        return router;
    }
}
