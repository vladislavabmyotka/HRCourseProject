package by.bsuir.abmyotkashevtsov.repository;

import by.bsuir.abmyotkashevtsov.domain.AbstractDomain;
import by.bsuir.abmyotkashevtsov.repository.exception.SQLTechnicalException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/**
 * The root abstract class in the DAO hierarchy.
 * @param <T> - It is assumed that T is the class of the hierarchy that is in the Entity package.
 */
public abstract class AbstractDAO<T extends AbstractDomain> {
    private final static Logger LOGGER = LogManager.getLogger(AbstractDAO.class);

    private Connection connection;

    AbstractDAO(Connection connection) {
        this.connection = connection;
    }

    public abstract List<T> findAll();
    public abstract T findById(int id);
    public abstract int delete(int id);
    public abstract int delete(T entity);
    public abstract int add(T entity);
    public abstract int update(T entity);

    Statement getStatement() throws SQLException {
        if (connection != null) {
            Statement statement = connection.createStatement();
            if (statement != null) {
                return statement;
            }
        }
        throw new SQLTechnicalException("Connection or Statement is null");
    }

    PreparedStatement getPreparedStatement(String query) throws SQLException {
        if (connection != null) {
            PreparedStatement statement = connection.prepareStatement(query);
            if (statement != null) {
                return statement;
            }
        }
        throw new SQLTechnicalException("Connection or PreparedStatement is null");
    }

    void closeStatement(Statement statement) {
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                LOGGER.log(Level.ERROR, "It's impossible to close 'Statement' object! Detail: " + e.getMessage());
            }
        }
    }
}
