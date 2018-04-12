package by.bsuir.abmyotkashevtsov.validator;

import by.bsuir.abmyotkashevtsov.constant.ValidationRegExConstant;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CandidateEmployerVacancyValidator {
    private static final String EMPTY_STRING = "";

    public static boolean checkID(String stringId) {
        if (stringId != null) {
            Pattern pattern = Pattern.compile(ValidationRegExConstant.ID_REGEX);
            Matcher matcher = pattern.matcher(stringId);
            if (matcher.matches()) {
                int id = Integer.parseInt(stringId);
                return id >= 0;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    public static boolean checkNames(String name) {
        if (name != null) {
            Pattern pattern = Pattern.compile(ValidationRegExConstant.NAMES_REGEX);
            Matcher matcher = pattern.matcher(name);
            return matcher.matches();
        } else {
            return false;
        }
    }

    public static boolean checkLastname(String name) {
        if (name != null && !name.equals(EMPTY_STRING)) {
            Pattern pattern = Pattern.compile(ValidationRegExConstant.NAMES_REGEX);
            Matcher matcher = pattern.matcher(name);
            return matcher.matches();
        } else {
            return true;
        }
    }

    public static boolean checkAge(String stringAge) {
        if (stringAge != null) {
            Pattern pattern = Pattern.compile(ValidationRegExConstant.AGE_EXPERIENCE_REGEX);
            Matcher matcher = pattern.matcher(stringAge);
            if (matcher.matches()) {
                int age = Integer.parseInt(stringAge);
                return age >= 1 && age <= 120;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    public static boolean checkEmail(String email) {
        if (email != null) {
            Pattern pattern = Pattern.compile(ValidationRegExConstant.EMAIL_REGEX);
            Matcher matcher = pattern.matcher(email);
            return matcher.matches();
        } else {
            return false;
        }
    }

    public static boolean checkCitizenship(String citizenship) {
        if (citizenship != null) {
            Pattern pattern = Pattern.compile(ValidationRegExConstant.CITIZENSHIP_REGEX);
            Matcher matcher = pattern.matcher(citizenship);
            return matcher.matches();
        } else {
            return false;
        }
    }

    public static boolean checkPhone(String phone) {
        if (phone != null) {
            Pattern pattern = Pattern.compile(ValidationRegExConstant.PHONE_REGEX);
            Matcher matcher = pattern.matcher(phone);
            return matcher.matches();
        } else {
            return false;
        }
    }

    public static boolean checkExperience(String stringExperience) {
        if (stringExperience != null) {
            Pattern pattern = Pattern.compile(ValidationRegExConstant.AGE_EXPERIENCE_REGEX);
            Matcher matcher = pattern.matcher(stringExperience);
            if (matcher.matches()) {
                int experience = Integer.parseInt(stringExperience);
                return experience >= 0 && experience <= 100;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    public static boolean checkSalary(String salary) {
        if (salary != null) {
            Pattern pattern = Pattern.compile(ValidationRegExConstant.SALARY_REGEX);
            Matcher matcher = pattern.matcher(salary);
            return matcher.matches();
        } else {
            return false;
        }
    }
}
