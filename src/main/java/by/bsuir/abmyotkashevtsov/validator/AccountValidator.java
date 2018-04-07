package by.bsuir.abmyotkashevtsov.validator;

import by.bsuir.abmyotkashevtsov.constant.AccountAttachmentConstant;
import by.bsuir.abmyotkashevtsov.constant.ValidationRegExConstant;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AccountValidator {
    public static boolean checkLogin(String login) {
        if (login != null) {
            Pattern pattern = Pattern.compile(ValidationRegExConstant.LOGIN_REGEX);
            Matcher matcher = pattern.matcher(login);
            return matcher.matches();
        } else {
            return false;
        }
    }

    public static boolean checkPassword(String password) {
        if (password != null) {
            Pattern pattern = Pattern.compile(ValidationRegExConstant.PASSWORD_REGEX);
            Matcher matcher = pattern.matcher(password);
            return matcher.matches();
        } else {
            return false;
        }
    }

    public static boolean checkAttachment(String attachment) {
        return attachment != null && (attachment.equals(AccountAttachmentConstant.CANDIDATE_ATTACHMENT) ||
                attachment.equals(AccountAttachmentConstant.EMPLOYER_ATTACHMENT));
    }
}
