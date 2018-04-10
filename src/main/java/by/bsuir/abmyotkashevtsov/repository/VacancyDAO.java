package by.bsuir.abmyotkashevtsov.repository;

import by.bsuir.abmyotkashevtsov.constant.SQLConstant;
import by.bsuir.abmyotkashevtsov.domain.Vacancy;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of abstract methods AbstractDAO class for working with the 'vacancy' table in database.
 */
public class VacancyDAO extends AbstractDAO<Vacancy> {
    private final static Logger LOGGER = LogManager.getLogger(VacancyDAO.class);

    private static final String VACANCY_ID = "idvacancy";
    private static final String POST = "post";
    private static final String COMPANY = "company";
    private static final String SALARY = "salary";
    private static final String LOCATION = "location";
    private static final String EXPERIENCE = "experience";
    private static final String ENGLISH = "english";
    private static final String TEXT = "text";
    private static final String CONDITION_VACANCY = "conditionVacancy";
    private static final String V_EMPLOYER_ID = "v_idEmployer";
    private static final String PROCENT_SYMBOL = "%";

    public VacancyDAO(Connection connection) {
        super(connection);
    }

    @Override
    public List<Vacancy> findAll() {
        List<Vacancy> vacancies = new ArrayList<>();
        Statement statement = null;
        try {
            statement = this.getStatement();
            ResultSet resultSet = statement.executeQuery(SQLConstant.SQL_SELECT_ALL_VACANCY);
            while(resultSet.next()) {
                Vacancy vacancy = createVacancyByResultSet(resultSet);
                vacancies.add(vacancy);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.ERROR, "SQL exception (request or table failed)! Detail: " + e.getMessage());
        } finally {
            this.closeStatement(statement);
        }

        return vacancies;
    }

    @Override
    public Vacancy findById(int vacancyId) {
        Vacancy vacancy = null;
        PreparedStatement statement = null;
        try {
            statement = this.getPreparedStatement(SQLConstant.SQL_SELECT_VACANCY_BY_VACANCY_ID);
            statement.setInt(1, vacancyId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                vacancy = createVacancyByResultSet(resultSet);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.ERROR, "Error while trying find vacancy by idvacancy in database! Detail: " +
                    e.getMessage());
        } finally {
            this.closeStatement(statement);
        }
        return vacancy;
    }

    public List<Vacancy> findAllByEmployerId(int employerId) {
        List<Vacancy> vacancies = new ArrayList<>();
        PreparedStatement statement = null;
        try {
            statement = this.getPreparedStatement(SQLConstant.SQL_SELECT_VACANCY_BY_EMPLOYER_ID);
            statement.setInt(1, employerId);
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()) {
                Vacancy vacancy = createVacancyByResultSet(resultSet);
                vacancies.add(vacancy);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.ERROR, "Error while trying find vacancy by v_idEmployer in database! Detail: " +
                    e.getMessage());
        } finally {
            this.closeStatement(statement);
        }
        return vacancies;
    }

    public List<Vacancy> findAllByKeyword(String keyword) {
        List<Vacancy> vacancies = new ArrayList<>();
        PreparedStatement statement = null;
        try {
            statement = this.getPreparedStatement(SQLConstant.SQL_SELECT_VACANCY_BY_KEYWORD);
            statement.setString(1, PROCENT_SYMBOL + keyword + PROCENT_SYMBOL);
            statement.setString(2, PROCENT_SYMBOL + keyword + PROCENT_SYMBOL);
            statement.setString(3, keyword);
            statement.setString(4, PROCENT_SYMBOL + keyword + PROCENT_SYMBOL);
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()) {
                Vacancy vacancy = createVacancyByResultSet(resultSet);
                vacancies.add(vacancy);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.ERROR, "Error while trying find vacancy by keyword in database! Detail: " +
                    e.getMessage());
        } finally {
            this.closeStatement(statement);
        }
        return vacancies;
    }

    private Vacancy createVacancyByResultSet(ResultSet resultSet) throws SQLException {
        Vacancy vacancy = new Vacancy();
        vacancy.setVacancyId(resultSet.getInt(VACANCY_ID));
        vacancy.setPost(resultSet.getString(POST));
        vacancy.setCompany(resultSet.getString(COMPANY));
        vacancy.setSalary(resultSet.getBigDecimal(SALARY));
        vacancy.setLocation(resultSet.getString(LOCATION));
        vacancy.setExperience(resultSet.getInt(EXPERIENCE));
        vacancy.setEnglish(resultSet.getString(ENGLISH));
        vacancy.setText(resultSet.getString(TEXT));
        vacancy.setConditionVacancy(resultSet.getString(CONDITION_VACANCY));
        vacancy.setEmployerId(resultSet.getInt(V_EMPLOYER_ID));
        return vacancy;
    }

    @Override
    public int delete(int vacancyId) {
        int countRowsAffected = 0;
        PreparedStatement statement = null;
        try {
            statement = this.getPreparedStatement(SQLConstant.SQL_DELETE_VACANCY_BY_VACANCY_ID);
            statement.setInt(1, vacancyId);
            countRowsAffected = statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.ERROR, "Error while trying delete vacancy from database! Detail: " + e.getMessage());
        } finally {
            this.closeStatement(statement);
        }
        return countRowsAffected;
    }

    @Override
    public int delete(Vacancy entity) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int update(Vacancy vacancy) {
        int countRowsAffected = 0;
        PreparedStatement statement = null;
        try {
            statement = this.getPreparedStatement(SQLConstant.SQL_UPDATE_VACANCY);
            statement.setString(1, vacancy.getPost());
            statement.setString(2, vacancy.getCompany());
            statement.setBigDecimal(3, vacancy.getSalary());
            statement.setString(4, vacancy.getLocation());
            statement.setInt(5, vacancy.getExperience());
            statement.setString(6, vacancy.getEnglish());
            statement.setString(7, vacancy.getText());
            statement.setString(8, vacancy.getConditionVacancy());
            statement.setInt(9, vacancy.getVacancyId());
            countRowsAffected = statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.ERROR, "Error while trying update vacancy! Detail: " + e.getMessage());
        } finally {
            this.closeStatement(statement);
        }

        return countRowsAffected;
    }

    @Override
    public int add(Vacancy vacancy) {
        int countRowsAffected = 0;
        PreparedStatement statement = null;
        try {
            statement = this.getPreparedStatement(SQLConstant.SQL_INSERT_VACANCY);
            statement.setString(1, vacancy.getPost());
            statement.setString(2, vacancy.getCompany());
            statement.setBigDecimal(3, vacancy.getSalary());
            statement.setString(4,vacancy.getLocation());
            statement.setInt(5, vacancy.getExperience());
            statement.setString(6, vacancy.getEnglish());
            statement.setString(7, vacancy.getText());
            statement.setString(8, vacancy.getConditionVacancy());
            statement.setInt(9, vacancy.getEmployerId());
            countRowsAffected = statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.ERROR, "Error while trying add vacancy in database! Detail: " + e.getMessage());
        } finally {
            this.closeStatement(statement);
        }
        return countRowsAffected;
    }
}
