package by.bsuir.abmyotkashevtsov.controller.command.impl.candidateImpl;

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
 * Implementation of the "candidate_view_edit_save" command
 *
 * <p>
 *     Accepts all information about the candidate to update, having previously passed validation.
 * </p>
 *
 * @see Command#execute(HttpServletRequest)
 */
public class CandidateViewEditSaveCommand implements Command {
    private final static Logger LOGGER = LogManager.getLogger(CandidateViewEditSaveCommand.class);

    private CandidateService service;

    /**
     *  Constructs and initialize commands type of 'candidate_view_edit_save'
     *
     * @param service - instance of the service type of "Candidate" to access the database table "candidate"
     */
    public CandidateViewEditSaveCommand(CandidateService service) {
        this.service = service;
    }

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router(PathConstant.PATH_PAGE_CANDIDATE, Router.RouteType.FORWARD);

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

        int candidateId = 0;
        try {
            candidateId = Integer.parseInt(stringCandidateId);
        } catch (NumberFormatException e) {
            LOGGER.log(Level.ERROR, "Error while parsing string value candidateId to integer! Detail: " +
                    e.getMessage());
        }
        Candidate candidate = service.findById(candidateId);
        if (candidate != null) {
            Object language = request.getSession(true).getAttribute("language");
            String message = MessageManager.getMessage(language.toString(), MessageConstant.INCORRECT_DATA);

            if (surname != null) {
                if(checkNames(surname)) {
                    candidate.setSurname(surname);
                } else {
                    request.setAttribute("candidate", candidate);
                    router.setPagePath(PathConstant.PATH_PAGE_CANDIDATE_VIEW_EDIT);
                    request.setAttribute("errorMessage", message);
                    return router;
                }
            }

            if (name != null) {
                if (checkNames(name)) {
                    candidate.setName(name);
                } else {
                    request.setAttribute("candidate", candidate);
                    router.setPagePath(PathConstant.PATH_PAGE_CANDIDATE_VIEW_EDIT);
                    request.setAttribute("errorMessage", message);
                    return router;
                }
            }


            if (lastname != null) {
                candidate.setLastname(lastname);
            }

            if (age != null) {
                if (checkAge(age)) {
                    candidate.setAge(Integer.parseInt(age));
                } else {
                    request.setAttribute("candidate", candidate);
                    router.setPagePath(PathConstant.PATH_PAGE_CANDIDATE_VIEW_EDIT);
                    request.setAttribute("errorMessage", message);
                    return router;
                }
            }

            if (email != null) {
                if (checkEmail(email)) {
                    candidate.setEmail(email);
                } else {
                    request.setAttribute("candidate", candidate);
                    router.setPagePath(PathConstant.PATH_PAGE_CANDIDATE_VIEW_EDIT);
                    request.setAttribute("errorMessage", message);
                    return router;
                }
            }

            if (address != null) {
                candidate.setAddress(address);
            }

            if (citizenship != null) {
                if (checkCitizenship(citizenship)) {
                    candidate.setCitizenship(citizenship);
                } else {
                    request.setAttribute("candidate", candidate);
                    router.setPagePath(PathConstant.PATH_PAGE_CANDIDATE_VIEW_EDIT);
                    request.setAttribute("errorMessage", message);
                    return router;
                }
            }

            if (phone != null) {
                if (checkPhone(phone)) {
                    candidate.setPhone(phone);
                } else {
                    request.setAttribute("candidate", candidate);
                    router.setPagePath(PathConstant.PATH_PAGE_CANDIDATE_VIEW_EDIT);
                    request.setAttribute("errorMessage", message);
                    return router;
                }
            }


            if (post != null) {
                candidate.setPost(post);
            }

            if (education != null) {
                candidate.setEducation(education);
            }

            if (experience != null) {
                if (checkExperience(experience)) {
                    candidate.setExperience(Integer.parseInt(experience));
                } else {
                    request.setAttribute("candidate", candidate);
                    router.setPagePath(PathConstant.PATH_PAGE_CANDIDATE_VIEW_EDIT);
                    request.setAttribute("errorMessage", message);
                    return router;
                }
            }

            if (english != null) {
                candidate.setEnglish(english);
            }

            if (skill != null) {
                candidate.setSkill(skill);
            }

            if (!service.update(candidate)) {
                language = request.getSession(true).getAttribute("language");
                message = MessageManager.getMessage(language.toString(), MessageConstant.ERROR_ON_WEBSITE);
                request.setAttribute("errorMessage", message);
            }
        } else {
            Object language = request.getSession(true).getAttribute("language");
            String message = MessageManager.getMessage(language.toString(), MessageConstant.ERROR_ON_WEBSITE);
            request.setAttribute("errorMessage", message);
        }

        return router;
    }
}
