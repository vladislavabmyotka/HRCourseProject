package by.bsuir.abmyotkashevtsov.controller.command;

import by.bsuir.abmyotkashevtsov.controller.command.impl.*;
import by.bsuir.abmyotkashevtsov.controller.command.impl.accountImpl.*;
import by.bsuir.abmyotkashevtsov.controller.command.impl.adminImpl.*;
import by.bsuir.abmyotkashevtsov.service.*;

/**
 *  In this enumeration lists the values in uppercase are located in the hidden field of the command type in all jsp.
 */
public enum CommandType {
    INDEX(new IndexCommand()),
    REGISTER_RELOAD(new RegisterReloadCommand()),
    AUTHORIZATION(new AuthorizationCommand(new AccountService())),
    FORGOT_PASSWORD(new ForgotPasswordCommand(new AccountService())),
    REGISTER(new RegisterCommand(new AccountService())),
    EDIT_ACCOUNT_DATA(new AccountEditCommand(new AccountService())),
    DELETE_ACCOUNT(new AccountDeleteCommand(new AccountService())),
    ADMIN_CANDIDATE_VIEW(new AdminCandidateViewCommand(new CandidateService())),
    ADMIN_CANDIDATE_DELETE(new AdminCandidateDeleteCommand(new CandidateService())),
    ADMIN_CANDIDATE_EDIT(new AdminCandidateEditCommand(new CandidateService())),
    ADMIN_CANDIDATE_EDIT_SAVE(new AdminCandidateEditSaveCommand(new CandidateService())),
    ADMIN_EMPLOYER_VIEW(new AdminEmployerViewCommand(new EmployerService())),
    ADMIN_EMPLOYER_DELETE(new AdminEmployerDeleteCommand(new EmployerService())),
    ADMIN_EMPLOYER_EDIT(new AdminEmployerEditCommand(new EmployerService())),
    ADMIN_EMPLOYER_EDIT_SAVE(new AdminEmployerEditSaveCommand(new EmployerService())),
    ADMIN_VACANCY_VIEW(new AdminVacancyViewCommand(new VacancyService(), new EmployerService())),
    ADMIN_VACANCY_DELETE(new AdminVacancyDeleteCommand(new VacancyService(), new EmployerService())),
    ADMIN_VACANCY_EDIT(new AdminVacancyEditCommand(new VacancyService())),
    ADMIN_VACANCY_EDIT_SAVE(new AdminVacancyEditSaveCommand(new VacancyService(), new EmployerService())),
    ADMIN_INTERVIEW_VIEW(new AdminInterviewViewCommand(new InterviewService(), new CandidateService(),
            new VacancyService(), new EmployerService())),
    ADMIN_INTERVIEW_DELETE(new AdminInterviewDeleteCommand(new InterviewService(), new CandidateService(),
            new VacancyService(), new EmployerService())),
    ADMIN_INTERVIEW_EDIT(new AdminInterviewEditCommand(new InterviewService())),
    ADMIN_INTERVIEW_EDIT_SAVE(new AdminInterviewEditSaveCommand(new InterviewService(), new CandidateService(),
            new VacancyService(), new EmployerService())),
    ;
    private Command command;

    /**
     * Constructs and initializes a Command.
     * @param command - command that came with jsp.
     */
    CommandType(Command command) {
        this.command = command;
    }

    public Command getCommand() {
        return command;
    }
}
