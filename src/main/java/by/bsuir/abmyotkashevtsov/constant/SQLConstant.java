package by.bsuir.abmyotkashevtsov.constant;

/**
 * Class constants for all SQL queries used in the corresponding DAO classes.
 */
public class SQLConstant {
    public static final String SQL_SELECT_ALL_ACCOUNT = "SELECT * FROM account";
    public static final String SQL_SELECT_SINGLE_LOGIN = "SELECT login FROM account WHERE login = ?";
    public static final String SQL_SELECT_FIND_ACCOUNT_ID_BY_PASSWORD =
            "SELECT idAccount FROM account WHERE password = ?";
    public static final String SQL_SELECT_FIND_ACCOUNT_ID_BY_LOGIN_PASSWORD_ATTACHMENT = "SELECT idAccount " +
            "FROM account WHERE login = ? and password = ? and attachment = ?";
    public static final String SQL_SELECT_FIND_PASSWORD_BY_LOGIN = "SELECT password FROM account WHERE login = ?";
    public static final String SQL_INSERT_ADD_ACCOUNT =
            "INSERT INTO account (login, password, attachment) VALUES (?, ?, ?)";
    public static final String SQL_UPDATE_ACCOUNT =
            "UPDATE account SET login = ?, password = ? WHERE idAccount = ?";
    public static final String SQL_DELETE_ACCOUNT = "DELETE FROM account WHERE login = ? and password = ?";

    public static final String SQL_SELECT_ALL_CANDIDATE = "SELECT * FROM candidate";
    public static final String SQL_SELECT_CANDIDATE_BY_ACCOUNT_ID = "SELECT * FROM candidate WHERE c_idAccount = ?";
    public static final String SQL_SELECT_CANDIDATE_BY_CANDIDATE_ID = "SELECT * FROM candidate WHERE idCandidate = ?";
    public static final String SQL_DELETE_CANDIDATE_BY_ACCOUNT_ID = "DELETE FROM candidate WHERE c_idAccount = ?";
    public static final String SQL_DELETE_CANDIDATE_BY_CANDIDATE_ID = "DELETE FROM candidate WHERE idCandidate = ?";
    public static final String SQL_UPDATE_CANDIDATE = "UPDATE candidate SET surname = ?, name = ?, lastname = ?, " +
            "age = ?, email = ?, address = ?, citizenship = ?, phone = ?, post = ?, education = ?, experience = ?, " +
            "english = ?, skill = ? WHERE idCandidate = ?";
    public static final String SQL_INSERT_CANDIDATE = "INSERT INTO candidate (surname, name, lastname, age, email, " +
            "address, citizenship, phone, post, education, experience, english, skill, c_idAccount) VALUES (?, ?, ?, " +
            "?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

    public static final String SQL_SELECT_ALL_EMPLOYER = "SELECT * FROM employer";
    public static final String SQL_SELECT_EMPLOYER_BY_ACCOUNT_ID = "SELECT * FROM employer WHERE e_idAccount = ?";
    public static final String SQL_SELECT_EMPLOYER_BY_EMPLOYER_ID = "SELECT * FROM employer WHERE idEmployer = ?";
    public static final String SQL_DELETE_EMPLOYER_BY_ACCOUNT_ID = "DELETE FROM employer WHERE e_idAccount = ?";
    public static final String SQL_DELETE_EMPLOYER_BY_EMPLOYER_ID = "DELETE FROM employer WHERE idEmployer = ?";
    public static final String SQL_UPDATE_EMPLOYER = "UPDATE employer SET surname = ?, name = ?, lastname = ?, " +
            "address = ?, phone = ?, email = ?, company = ? WHERE idEmployer = ?";
    public static final String SQL_INSERT_EMPLOYER = "INSERT INTO employer (surname, name, lastname, address, phone, " +
            "email, company, e_idAccount) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

    public static final String SQL_SELECT_ALL_VACANCY = "SELECT * FROM vacancy";
    public static final String SQL_SELECT_VACANCY_BY_VACANCY_ID = "SELECT * FROM vacancy WHERE idvacancy = ?";
    public static final String SQL_SELECT_VACANCY_BY_EMPLOYER_ID = "SELECT * FROM vacancy WHERE v_idEmployer = ?";
    public static final String SQL_SELECT_VACANCY_BY_KEYWORD = "SELECT * FROM vacancy " +
            "WHERE post LIKE ? or company LIKE ? or salary = ? or location LIKE ?;";
    public static final String SQL_DELETE_VACANCY_BY_VACANCY_ID = "DELETE FROM vacancy WHERE idvacancy = ?";
    public static final String SQL_UPDATE_VACANCY = "UPDATE vacancy SET post = ?, company = ?, salary = ?, " +
            "location = ?, experience = ?, english = ?, text = ?, conditionVacancy = ? WHERE idvacancy = ?";
    public static final String SQL_INSERT_VACANCY = "INSERT INTO vacancy (post, company, salary, location, " +
            "experience, english, text, conditionVacancy, v_idEmployer) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

    public static final String SQL_SELECT_ALL_INTERVIEW = "SELECT * FROM interview";
    public static final String SQL_SELECT_INTERVIEW_BY_INTERVIEW_ID = "SELECT * FROM interview WHERE idInterview = ?";
    public static final String SQL_SELECT_INTERVIEW_BY_CANDIDATE_ID_VACANCY_ID = "SELECT * FROM interview " +
            "WHERE i_idCandidate = ? and i_idvacancy = ?";
    public static final String SQL_SELECT_INTERVIEW_BY_EMPLOYER_ID =
            "SELECT idInterview, i_idCandidate, i_idvacancy, preResult, finalResult, " +
            "GROUP_CONCAT(candidate.surname, ' ', candidate.name, ' ', " +
            "case when candidate.lastname  is NOT null then candidate.lastname else '' end, '\n', candidate.email) " +
            "AS 'candidateInfo', " +
            "GROUP_CONCAT(vacancy.post, ', ', vacancy.company, ', ', vacancy.salary) AS 'vacancyInfo' " +
            "FROM interview " +
            "INNER JOIN candidate on interview.i_idCandidate = candidate.idCandidate " +
            "INNER JOIN vacancy on interview.i_idvacancy = vacancy.idvacancy " +
            "WHERE i_idvacancy IN (SELECT idvacancy FROM vacancy WHERE v_idEmployer = ?) " +
            "GROUP BY idInterview;";
    public static final String SQL_DELETE_INTERVIEW_BY_INTERVIEW_ID = "DELETE FROM interview WHERE idInterview = ?";
    public static final String SQL_UPDATE_INTERVIEW = "UPDATE interview SET preResult = ?, finalResult = ? " +
            "WHERE idInterview = ?";
    public static final String SQL_INSERT_INTERVIEW = "INSERT INTO interview (i_idCandidate, i_idvacancy) " +
            "VALUES (?, ?)";
}
