package by.bsuir.abmyotkashevtsov.constant;

/**
 * Class constants for all regular expressions used in validators classes.
 */
public class ValidationRegExConstant {
    public static final String LOGIN_REGEX = "[a-zA-Z][\\s\\w]{4,25}";
    public static final String PASSWORD_REGEX = "^(?=.*\\d)(?=.*[a-zA-Z])[0-9a-zA-Z]{8,25}$";
    public static final String NAMES_REGEX = "[A-ZА-Я][a-zа-яёA-ZА-ЯЁ-]{1,40}";
    public static final String AGE_EXPERIENCE_REGEX = "[\\d]{1,3}";
    public static final String EMAIL_REGEX = "^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$";
    public static final String CITIZENSHIP_REGEX = "[A-ZА-ЯЁa-zа-яё\\s]+";
    public static final String PHONE_REGEX = "^[+]?((8|\\+7)[\\- ]?)?(\\(?\\d{3}\\)?[\\- ]?)?[\\d\\- ]{7,12}$";
    public static final String ID_REGEX = "[\\d]+";
    public static final String SALARY_REGEX = "\\d+\\.\\d{2}";
}
