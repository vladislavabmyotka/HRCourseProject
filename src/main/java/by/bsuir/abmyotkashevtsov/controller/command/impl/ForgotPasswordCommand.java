package by.bsuir.abmyotkashevtsov.controller.command.impl;

import by.bsuir.abmyotkashevtsov.constant.CompanyEmailConstant;
import by.bsuir.abmyotkashevtsov.constant.MessageConstant;
import by.bsuir.abmyotkashevtsov.constant.ParameterConstant;
import by.bsuir.abmyotkashevtsov.constant.PathConstant;
import by.bsuir.abmyotkashevtsov.controller.Router;
import by.bsuir.abmyotkashevtsov.controller.command.Command;
import by.bsuir.abmyotkashevtsov.controller.mail.MailThread;
import by.bsuir.abmyotkashevtsov.controller.manager.MessageManager;
import by.bsuir.abmyotkashevtsov.service.AccountService;
import by.bsuir.abmyotkashevtsov.validator.AccountValidator;
import by.bsuir.abmyotkashevtsov.validator.CandidateEmployerVacancyValidator;

import javax.servlet.http.HttpServletRequest;
import java.util.Properties;

/**
 * Implementation of the "forgot_password" command
 *
 * <p>
 *     Takes a login and user e-mail. If the data is correct a confirmation e-mail will be sent the user's
 *     password, otherwise you will receive a message about incorrect input data.
 * </p>
 *
 * @see Command#execute(HttpServletRequest)
 */
public class ForgotPasswordCommand implements Command {
    private AccountService service;

    /**
     *  Constructs and initialize commands type of 'forgot_password'
     *
     * @param service - instance of the service type of "Account" to access the database table "account"
     */
    public ForgotPasswordCommand(AccountService service) {
        this.service = service;
    }

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router(PathConstant.PATH_PAGE_MAIN, Router.RouteType.FORWARD);

        String login = request.getParameter(ParameterConstant.PARAM_LOGIN);
        String email = request.getParameter(ParameterConstant.PARAM_EMAIL);
        if (AccountValidator.checkLogin(login) && CandidateEmployerVacancyValidator.checkEmail(email)) {
            String password = service.findPasswordByLogin(login);
            if (password != null) {
                Properties properties = new Properties();
                properties.setProperty("mail.smtp.host", "smtp.gmail.com");
                properties.setProperty("mail.smtp.port", "465");
                properties.setProperty("mail.user.name", CompanyEmailConstant.SEND_FROM_EMAIL);
                properties.setProperty("mail.user.password", CompanyEmailConstant.SEND_FROM_EMAIL_PASSWORD);

                Object language = request.getSession(true).getAttribute("language");
                String theme = MessageManager.getMessage(language.toString(),
                        MessageConstant.FORGOT_PASSWORD_THEME_MESSAGE);
                String emailMessage = MessageManager.getMessage(language.toString(),
                        MessageConstant.FORGOT_PASSWORD_MESSAGE_PART1);
                emailMessage += "\n\n" + MessageManager.getMessage(language.toString(),
                        MessageConstant.FORGOT_PASSWORD_MESSAGE_PART2) + " " + password + ".\n\n" +
                        MessageManager.getMessage(language.toString(), MessageConstant.FORGOT_PASSWORD_MESSAGE_PART3) +
                        "\n" + MessageManager.getMessage(language.toString(),
                        MessageConstant.FORGOT_PASSWORD_MESSAGE_PART4);

                MailThread mailOperator = new MailThread(email, CompanyEmailConstant.SEND_FROM_EMAIL, theme,
                        emailMessage, properties);
                mailOperator.start();

                String successfullySent = MessageManager.getMessage(language.toString(),
                        MessageConstant.SUCCESSFULLY_FORGOT_PASSWORD_MESSAGE);
                request.setAttribute("notificationMessage", successfullySent);
            } else {
                Object language = request.getSession(true).getAttribute("language");
                String message = MessageManager.getMessage(language.toString(), MessageConstant.NON_REGISTERED_MESSAGE);
                request.setAttribute("errorMessage", message);
            }
        } else {
            Object language = request.getSession(true).getAttribute("language");
            String message = MessageManager.getMessage(language.toString(), MessageConstant.INCORRECT_LOGIN_MESSAGE);
            request.setAttribute("errorMessage", message);
            router.setPagePath(PathConstant.PATH_PAGE_FORGOT_PASSWORD);
        }

        return router;
    }
}
