package by.bsuir.abmyotkashevtsov.service;

import by.bsuir.abmyotkashevtsov.domain.Employer;
import by.bsuir.abmyotkashevtsov.repository.DBPool;
import by.bsuir.abmyotkashevtsov.repository.EmployerDAO;

import java.sql.Connection;
import java.util.List;

/**
 * Class-service for working with the table "employer" of the database.
 */
public class EmployerService {

    public List<Employer> takeAll() {
        DBPool pool = DBPool.getInstance();
        Connection connection = pool.getConnection();
        EmployerDAO employerDAO = new EmployerDAO(connection);
        List<Employer> employers = employerDAO.findAll();
        pool.putConnection(connection);
        return employers;
    }

    public Employer findById(int employerId) {
        DBPool pool = DBPool.getInstance();
        Connection connection = pool.getConnection();
        EmployerDAO employerDAO = new EmployerDAO(connection);
        Employer employer = employerDAO.findById(employerId);
        pool.putConnection(connection);
        return employer;
    }

    public Employer findByAccountId(int accountId) {
        DBPool pool = DBPool.getInstance();
        Connection connection = pool.getConnection();
        EmployerDAO employerDAO = new EmployerDAO(connection);
        Employer employer = employerDAO.findByAccountId(accountId);
        pool.putConnection(connection);
        return employer;
    }

    public boolean delete(int employerId) {
        DBPool pool = DBPool.getInstance();
        Connection connection = pool.getConnection();
        EmployerDAO employerDAO = new EmployerDAO(connection);
        int countRowsAffected = employerDAO.delete(employerId);
        pool.putConnection(connection);
        return countRowsAffected != 0;
    }

    public boolean update(Employer employer) {
        DBPool pool = DBPool.getInstance();
        Connection connection = pool.getConnection();
        EmployerDAO employerDAO = new EmployerDAO(connection);
        int countRowsAffected = employerDAO.update(employer);
        pool.putConnection(connection);
        return countRowsAffected != 0;
    }

    public boolean add(Employer employer) {
        DBPool pool = DBPool.getInstance();
        Connection connection = pool.getConnection();
        EmployerDAO employerDAO = new EmployerDAO(connection);
        int countRowsAffected = employerDAO.add(employer);
        pool.putConnection(connection);
        return countRowsAffected != 0;
    }

    public boolean isExistEmployerByAccountId(int accountId) {
        DBPool pool = DBPool.getInstance();
        Connection connection = pool.getConnection();
        EmployerDAO employerDAO = new EmployerDAO(connection);
        Employer employer = employerDAO.findByAccountId(accountId);
        pool.putConnection(connection);
        return employer != null;
    }
}
