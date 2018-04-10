package by.bsuir.abmyotkashevtsov.repository;

import by.bsuir.abmyotkashevtsov.constant.SQLConstant;
import by.bsuir.abmyotkashevtsov.domain.Employer;
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
 * Implementation of abstract methods AbstractDAO class for working with the 'employer' table in database.
 */
public class EmployerDAO extends AbstractDAO<Employer> {
    private final static Logger LOGGER = LogManager.getLogger(EmployerDAO.class);

    private static final String EMPLOYER_ID = "idEmployer";
    private static final String SURNAME = "surname";
    private static final String NAME = "name";
    private static final String LASTNAME = "lastname";
    private static final String ADDRESS = "address";
    private static final String PHONE = "phone";
    private static final String EMAIL = "email";
    private static final String COMPANY = "company";
    private static final String E_ACCOUNT_ID = "e_idAccount";

    public EmployerDAO(Connection connection) {
        super(connection);
    }

    @Override
    public List<Employer> findAll() {
        List<Employer> employers = new ArrayList<>();
        Statement statement = null;
        try {
            statement = this.getStatement();
            ResultSet resultSet = statement.executeQuery(SQLConstant.SQL_SELECT_ALL_EMPLOYER);
            while(resultSet.next()) {
                Employer employer = createEmployerByResultSet(resultSet);
                employers.add(employer);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.ERROR, "SQL exception (request or table failed)! Detail: " + e.getMessage());
        } finally {
            this.closeStatement(statement);
        }

        return employers;
    }

    public Employer findByAccountId(int accountId) {
        Employer employer = null;
        PreparedStatement statement = null;
        try {
            statement = this.getPreparedStatement(SQLConstant.SQL_SELECT_EMPLOYER_BY_ACCOUNT_ID);
            statement.setInt(1, accountId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                employer = createEmployerByResultSet(resultSet);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.ERROR, "SQL exception (request or table failed)! Detail: " + e.getMessage());
        } finally {
            this.closeStatement(statement);
        }

        return employer;
    }

    @Override
    public Employer findById(int employerId) {
        Employer employer = null;
        PreparedStatement statement = null;
        try {
            statement = this.getPreparedStatement(SQLConstant.SQL_SELECT_EMPLOYER_BY_EMPLOYER_ID);
            statement.setInt(1, employerId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                employer = createEmployerByResultSet(resultSet);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.ERROR, "Error while trying find employer by idEmployer in database! Detail: " +
                    e.getMessage());
        } finally {
            this.closeStatement(statement);
        }
        return employer;
    }

    private Employer createEmployerByResultSet(ResultSet resultSet) throws SQLException {
        Employer employer = new Employer();
        employer.setEmployerId(resultSet.getInt(EMPLOYER_ID));
        employer.setSurname(resultSet.getString(SURNAME));
        employer.setName(resultSet.getString(NAME));
        employer.setLastname(resultSet.getString(LASTNAME));
        employer.setAddress(resultSet.getString(ADDRESS));
        employer.setPhone(resultSet.getString(PHONE));
        employer.setEmail(resultSet.getString(EMAIL));
        employer.setCompany(resultSet.getString(COMPANY));
        employer.setAccountId(resultSet.getInt(E_ACCOUNT_ID));

        return employer;
    }

    @Override
    public int update(Employer employer) {
        int countRowsAffected = 0;
        PreparedStatement statement = null;
        try {
            statement = this.getPreparedStatement(SQLConstant.SQL_UPDATE_EMPLOYER);
            statement.setString(1, employer.getSurname());
            statement.setString(2, employer.getName());
            statement.setString(3, employer.getLastname());
            statement.setString(4,employer.getAddress());
            statement.setString(5, employer.getPhone());
            statement.setString(6, employer.getEmail());
            statement.setString(7, employer.getCompany());
            statement.setInt(8, employer.getEmployerId());
            countRowsAffected = statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.ERROR, "Error while trying update employer! Detail: " + e.getMessage());
        } finally {
            this.closeStatement(statement);
        }

        return countRowsAffected;
    }

    @Override
    public int delete(int employerId) {
        int countRowsAffected = 0;
        PreparedStatement statement = null;
        try {
            statement = this.getPreparedStatement(SQLConstant.SQL_DELETE_EMPLOYER_BY_EMPLOYER_ID);
            statement.setInt(1, employerId);
            countRowsAffected = statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.ERROR, "Error while trying delete employer from database! Detail: " + e.getMessage());
        } finally {
            this.closeStatement(statement);
        }
        return countRowsAffected;
    }

    @Override
    public int delete(Employer entity) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int add(Employer employer) {
        int countRowsAffected = 0;
        PreparedStatement statement = null;
        try {
            statement = this.getPreparedStatement(SQLConstant.SQL_INSERT_EMPLOYER);
            statement.setString(1, employer.getSurname());
            statement.setString(2, employer.getName());
            statement.setString(3, employer.getLastname());
            statement.setString(4,employer.getAddress());
            statement.setString(5, employer.getPhone());
            statement.setString(6, employer.getEmail());
            statement.setString(7, employer.getCompany());
            statement.setInt(8, employer.getAccountId());
            countRowsAffected = statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.ERROR, "Error while trying add employer in database! Detail: " + e.getMessage());
        } finally {
            this.closeStatement(statement);
        }
        return countRowsAffected;
    }
}
