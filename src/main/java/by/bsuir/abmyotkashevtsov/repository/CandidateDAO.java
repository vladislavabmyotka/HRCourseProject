package by.bsuir.abmyotkashevtsov.repository;

import by.bsuir.abmyotkashevtsov.constant.SQLConstant;
import by.bsuir.abmyotkashevtsov.domain.Candidate;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of abstract methods AbstractDAO class for working with the 'candidate' table in database.
 */
public class CandidateDAO extends AbstractDAO<Candidate> {
    private final static Logger LOGGER = LogManager.getLogger(CandidateDAO.class);

    private static final String CANDIDATE_ID = "idCandidate";
    private static final String SURNAME = "surname";
    private static final String NAME = "name";
    private static final String LASTNAME = "lastname";
    private static final String AGE = "age";
    private static final String EMAIL = "email";
    private static final String ADDRESS = "address";
    private static final String CITIZENSHIP = "citizenship";
    private static final String PHONE = "phone";
    private static final String POST = "post";
    private static final String EDUCATION = "education";
    private static final String EXPERIENCE = "experience";
    private static final String ENGLISH = "english";
    private static final String SKILL = "skill";
    private static final String C_ID_ACCOUNT = "c_idAccount";

    public CandidateDAO(Connection connection) {
        super(connection);
    }

    @Override
    public List<Candidate> findAll() {
        List<Candidate> candidates = new ArrayList<>();
        Statement statement = null;
        try {
            statement = this.getStatement();
            ResultSet resultSet = statement.executeQuery(SQLConstant.SQL_SELECT_ALL_CANDIDATE);
            while(resultSet.next()) {
                Candidate candidate = createCandidateByResultSet(resultSet);
                candidates.add(candidate);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.ERROR, "SQL exception (request or table failed)! Detail: " + e.getMessage());
        } finally {
            this.closeStatement(statement);
        }

        return candidates;
    }

    public Candidate findByAccountId(int accountId) {
        Candidate candidate = null;
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = this.getPreparedStatement(SQLConstant.SQL_SELECT_CANDIDATE_BY_ACCOUNT_ID);
            preparedStatement.setInt(1, accountId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                candidate = createCandidateByResultSet(resultSet);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.ERROR, "SQL exception (request or table failed)! Detail: " + e.getMessage());
        } finally {
            this.closeStatement(preparedStatement);
        }
        return candidate;
    }

    @Override
    public Candidate findById(int candidateId) {
        Candidate candidate = null;
        PreparedStatement statement = null;
        try {
            statement = this.getPreparedStatement(SQLConstant.SQL_SELECT_CANDIDATE_BY_CANDIDATE_ID);
            statement.setInt(1, candidateId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                candidate = createCandidateByResultSet(resultSet);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.ERROR, "Error while trying find candidate by idCandidate in database! Detail: " +
                    e.getMessage());
        } finally {
            this.closeStatement(statement);
        }
        return candidate;
    }

    private Candidate createCandidateByResultSet(ResultSet resultSet) throws SQLException {
        Candidate candidate = new Candidate();
        candidate.setCandidateId(resultSet.getInt(CANDIDATE_ID));
        candidate.setSurname(resultSet.getString(SURNAME));
        candidate.setName(resultSet.getString(NAME));
        candidate.setLastname(resultSet.getString(LASTNAME));
        candidate.setAge(resultSet.getInt(AGE));
        candidate.setEmail(resultSet.getString(EMAIL));
        candidate.setAddress(resultSet.getString(ADDRESS));
        candidate.setCitizenship(resultSet.getString(CITIZENSHIP));
        candidate.setPhone(resultSet.getString(PHONE));
        candidate.setPost(resultSet.getString(POST));
        candidate.setEducation(resultSet.getString(EDUCATION));
        candidate.setExperience(resultSet.getInt(EXPERIENCE));
        candidate.setEnglish(resultSet.getString(ENGLISH));
        candidate.setSkill(resultSet.getString(SKILL));
        candidate.setAccountId(resultSet.getInt(C_ID_ACCOUNT));
        return candidate;
    }

    @Override
    public int delete(int candidateId) {
        int countRowsAffected = 0;
        PreparedStatement statement = null;
        try {
            statement = this.getPreparedStatement(SQLConstant.SQL_DELETE_CANDIDATE_BY_CANDIDATE_ID);
            statement.setInt(1, candidateId);
            countRowsAffected = statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.ERROR, "Error while trying delete candidate from database! Detail: " + e.getMessage());
        } finally {
            this.closeStatement(statement);
        }
        return countRowsAffected;
    }

    @Override
    public int delete(Candidate entity) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int update(Candidate candidate) {
        int countRowsAffected = 0;
        PreparedStatement statement = null;
        try {
            statement = this.getPreparedStatement(SQLConstant.SQL_UPDATE_CANDIDATE);
            statement.setString(1, candidate.getSurname());
            statement.setString(2, candidate.getName());
            statement.setString(3, candidate.getLastname());
            statement.setInt(4, candidate.getAge());
            statement.setString(5, candidate.getEmail());
            statement.setString(6,candidate.getAddress());
            statement.setString(7, candidate.getCitizenship());
            statement.setString(8, candidate.getPhone());
            statement.setString(9, candidate.getPost());
            statement.setString(10, candidate.getEducation());
            statement.setInt(11, candidate.getExperience());
            statement.setString(12, candidate.getEnglish());
            statement.setString(13, candidate.getSkill());
            statement.setInt(14, candidate.getCandidateId());
            countRowsAffected = statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.ERROR, "Error while trying update candidate! Detail: " + e.getMessage());
        } finally {
            this.closeStatement(statement);
        }

        return countRowsAffected;
    }

    @Override
    public int add(Candidate candidate) {
        int countRowsAffected = 0;
        PreparedStatement statement = null;
        try {
            statement = this.getPreparedStatement(SQLConstant.SQL_INSERT_CANDIDATE);
            statement.setString(1, candidate.getSurname());
            statement.setString(2, candidate.getName());
            statement.setString(3, candidate.getLastname());
            statement.setInt(4, candidate.getAge());
            statement.setString(5, candidate.getEmail());
            statement.setString(6,candidate.getAddress());
            statement.setString(7, candidate.getCitizenship());
            statement.setString(8, candidate.getPhone());
            statement.setString(9, candidate.getPost());
            statement.setString(10, candidate.getEducation());
            statement.setInt(11, candidate.getExperience());
            statement.setString(12, candidate.getEnglish());
            statement.setString(13, candidate.getSkill());
            statement.setInt(14, candidate.getAccountId());
            countRowsAffected = statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.ERROR, "Error while trying add candidate in database! Detail: " + e.getMessage());
        } finally {
            this.closeStatement(statement);
        }
        return countRowsAffected;
    }
}
