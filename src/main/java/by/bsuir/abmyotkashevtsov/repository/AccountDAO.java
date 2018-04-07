package by.bsuir.abmyotkashevtsov.repository;

import by.bsuir.abmyotkashevtsov.constant.SQLConstant;
import by.bsuir.abmyotkashevtsov.domain.Account;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of abstract methods AbstractDAO class for working with the 'account' table in database.
 */
public class AccountDAO extends AbstractDAO<Account> {
    private final static Logger LOGGER = LogManager.getLogger(AccountDAO.class);

    private static final String ID_ACCOUNT = "idAccount";
    private static final String LOGIN = "login";
    private static final String PASSWORD = "password";
    private static final String ATTACHMENT = "attachment";

    public AccountDAO(Connection connection) {
        super(connection);
    }

    @Override
    public List<Account> findAll() {
        List<Account> accounts = new ArrayList<>();
        Statement statement = null;
        try {
            statement = this.getStatement();
            ResultSet resultSet = statement.executeQuery(SQLConstant.SQL_SELECT_ALL_ACCOUNT);
            while(resultSet.next()) {
                Account account = new Account();
                account.setAccountId(resultSet.getInt(ID_ACCOUNT));
                account.setLogin(resultSet.getString(LOGIN));
                account.setPassword(resultSet.getString(PASSWORD));
                account.setAttachment(resultSet.getString(ATTACHMENT));
                accounts.add(account);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.ERROR, "SQL exception (request or table failed)! Detail: " + e.getMessage());
        } finally {
            this.closeStatement(statement);
        }

        return accounts;
    }

    public int findAccountIdByPassword(String password) {
        int accountId = 0;
        PreparedStatement statement = null;
        try {
            statement = this.getPreparedStatement(SQLConstant.SQL_SELECT_FIND_ACCOUNT_ID_BY_PASSWORD);
            statement.setString(1, password);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                accountId = resultSet.getInt(ID_ACCOUNT);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.ERROR, "Error while trying find idAccount by password into database! Detail: " +
                    e.getMessage());
        } finally {
            this.closeStatement(statement);
        }
        return accountId;
    }

    public int findAccountIdByLoginPasswordAttachment(Account account) {
        int accountId = 0;
        PreparedStatement statement = null;
        try {
            statement = this.getPreparedStatement(SQLConstant.SQL_SELECT_FIND_ACCOUNT_ID_BY_LOGIN_PASSWORD_ATTACHMENT);
            statement.setString(1, account.getLogin());
            statement.setString(2, account.getPassword());
            statement.setString(3, account.getAttachment());
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                accountId = resultSet.getInt(ID_ACCOUNT);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.ERROR, "Error while trying find idAccount by login, password and attachment into " +
                    "database! Detail: " + e.getMessage());
        } finally {
            this.closeStatement(statement);
        }
        return accountId;
    }

    public String findPasswordByLogin(String login) {
        String password = null;
        PreparedStatement statement = null;
        try {
            statement = this.getPreparedStatement(SQLConstant.SQL_SELECT_FIND_PASSWORD_BY_LOGIN);
            statement.setString(1, login);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                password = resultSet.getString(PASSWORD);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.ERROR, "Error while trying find password by password into database! Detail: " +
                    e.getMessage());
        } finally {
            this.closeStatement(statement);
        }
        return password;
    }

    @Override
    public Account findById(int id) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int delete(int id) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int delete(Account account) {
        int countRowsAffected = 0;
        PreparedStatement statement = null;
        try {
            statement = this.getPreparedStatement(SQLConstant.SQL_DELETE_ACCOUNT);
            statement.setString(1, account.getLogin());
            statement.setString(2, account.getPassword());
            countRowsAffected = statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.ERROR, "Error while trying delete account from database! Detail: " + e.getMessage());
        } finally {
            this.closeStatement(statement);
        }
        return countRowsAffected;
    }

    @Override
    public int add(Account account) {
        int countRowsAffected = 0;
        PreparedStatement statement = null;
        try {
            statement = this.getPreparedStatement(SQLConstant.SQL_INSERT_ADD_ACCOUNT);
            statement.setString(1, account.getLogin());
            statement.setString(2, account.getPassword());
            statement.setString(3, account.getAttachment());
            countRowsAffected = statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.ERROR, "Error while trying add account into database! Detail: " + e.getMessage());
        } finally {
            this.closeStatement(statement);
        }

        return countRowsAffected;
    }

    @Override
    public int update(Account user) {
        int countRowsAffected = 0;
        PreparedStatement statement = null;
        try {
            statement = this.getPreparedStatement(SQLConstant.SQL_UPDATE_ACCOUNT);
            statement.setString(1, user.getLogin());
            statement.setString(2, user.getPassword());
            statement.setInt(3, user.getAccountId());
            countRowsAffected = statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.ERROR, "Error while trying update account! Detail: " + e.getMessage());
        } finally {
            this.closeStatement(statement);
        }

        return countRowsAffected;
    }

    public boolean checkCoincidenceByLogin(String login) {
        boolean isCoincidence = true;
        PreparedStatement statement = null;
        try {
            statement = this.getPreparedStatement(SQLConstant.SQL_SELECT_SINGLE_LOGIN);
            statement.setString(1, login);
            ResultSet resultSet = statement.executeQuery();
            isCoincidence = resultSet.next();
        } catch (SQLException e) {
            LOGGER.log(Level.ERROR, "Error while trying find coincidence login! Detail: " + e.getMessage());
        } finally {
            this.closeStatement(statement);
        }
        return isCoincidence;
    }
}
