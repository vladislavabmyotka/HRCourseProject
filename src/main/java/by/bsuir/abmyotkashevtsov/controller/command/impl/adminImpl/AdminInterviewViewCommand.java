package by.bsuir.abmyotkashevtsov.controller.command.impl.adminImpl;

import by.bsuir.abmyotkashevtsov.constant.PathConstant;
import by.bsuir.abmyotkashevtsov.controller.Router;
import by.bsuir.abmyotkashevtsov.controller.command.Command;
import by.bsuir.abmyotkashevtsov.domain.Candidate;
import by.bsuir.abmyotkashevtsov.domain.Employer;
import by.bsuir.abmyotkashevtsov.domain.Interview;
import by.bsuir.abmyotkashevtsov.domain.Vacancy;
import by.bsuir.abmyotkashevtsov.service.CandidateService;
import by.bsuir.abmyotkashevtsov.service.EmployerService;
import by.bsuir.abmyotkashevtsov.service.InterviewService;
import by.bsuir.abmyotkashevtsov.service.VacancyService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static by.bsuir.abmyotkashevtsov.constant.MessageConstant.*;

/**
 * Implementation of the "admin_interview_view" command.
 *
 * <p>
 *     Retrieves from the database information about all the interviews, and brief information about the candidate and
 *     the HR for each interview and puts in a request attribute to display on the page to view information about
 *     the interviews.
 * </p>
 *
 * @see Command#execute(HttpServletRequest)
 */
public class AdminInterviewViewCommand implements Command {
    private InterviewService interviewService;
    private CandidateService candidateService;
    private VacancyService vacancyService;
    private EmployerService employerService;

    /**
     *  Constructs and initialize commands type of 'admin_interview_view'
     *
     * @param interviewService - instance of the service type of "Interview" to access the database table "interview"
     * @param candidateService - instance of the service type of "Candidate" to access the database table "candidate"
     * @param vacancyService - instance of the service type of "Vacancy" to access the database table "vacancy"
     * @param employerService - instance of the service type of "Employer" to access the database table "employer"
     */
    public AdminInterviewViewCommand(InterviewService interviewService, CandidateService candidateService,
                                     VacancyService vacancyService, EmployerService employerService) {
        this.interviewService = interviewService;
        this.candidateService = candidateService;
        this.vacancyService = vacancyService;
        this.employerService = employerService;
    }

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router(PathConstant.PATH_PAGE_ADMIN_INTERVIEW, Router.RouteType.FORWARD);

        List<Interview> interviews = interviewService.takeAll();

        for(Interview interview : interviews) {
            int candidateID = interview.getCandidateId();
            if (candidateID != 0) {
                Candidate candidate = candidateService.findById(candidateID);
                String mainCandidateInformation = candidate.getMainInformation();
                interview.setCandidateInfo(mainCandidateInformation);
            } else {
                interview.setCandidateInfo(CANDIDATE_HAS_BEEN_REMOVED);
            }

            int vacancyId = interview.getVacancyId();
            if (vacancyId != 0) {
                Vacancy vacancy = vacancyService.findById(vacancyId);
                String mainVacancyInformation = vacancy.getMainInformation();
                interview.setVacancyInfo(mainVacancyInformation);

                int employerId = vacancy.getEmployerId();
                if (employerId != 0) {
                    Employer employer = employerService.findById(employerId);
                    String mainEmployerInformation = employer.getMainInformation();
                    interview.setEmployerInfo(mainEmployerInformation);
                } else {
                    interview.setEmployerInfo(EMPLOYER_NOT_ASSIGNED);
                }
            } else {
                interview.setVacancyInfo(VACANCY_HAS_BEEN_REMOVED);
                interview.setEmployerInfo(EMPLOYER_NOT_ASSIGNED);
            }
        }

        request.setAttribute("interviewList", interviews);

        return router;
    }
}
