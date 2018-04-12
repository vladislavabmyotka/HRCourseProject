package by.bsuir.abmyotkashevtsov.service;

import by.bsuir.abmyotkashevtsov.domain.Interview;
import by.bsuir.abmyotkashevtsov.repository.DBPool;
import by.bsuir.abmyotkashevtsov.repository.InterviewDAO;

import java.sql.Connection;
import java.util.List;

/**
 * Class-service for working with the table "interview" of the database.
 */
public class InterviewService {

    public List<Interview> takeAll() {
        DBPool pool = DBPool.getInstance();
        Connection connection = pool.getConnection();
        InterviewDAO interviewDAO = new InterviewDAO(connection);
        List<Interview> interviews = interviewDAO.findAll();
        pool.putConnection(connection);
        return interviews;
    }

    public Interview findById(int interviewId) {
        DBPool pool = DBPool.getInstance();
        Connection connection = pool.getConnection();
        InterviewDAO interviewDAO = new InterviewDAO(connection);
        Interview interview = interviewDAO.findById(interviewId);
        pool.putConnection(connection);
        return interview;
    }

    public List<Interview> findAllByEmployerId(int employerId) {
        DBPool pool = DBPool.getInstance();
        Connection connection = pool.getConnection();
        InterviewDAO interviewDAO = new InterviewDAO(connection);
        List<Interview> interviews = interviewDAO.findAllByEmployerId(employerId);
        pool.putConnection(connection);
        return interviews;
    }

    public boolean delete(int interviewId) {
        DBPool pool = DBPool.getInstance();
        Connection connection = pool.getConnection();
        InterviewDAO interviewDAO = new InterviewDAO(connection);
        int countRowsAffected = interviewDAO.delete(interviewId);
        pool.putConnection(connection);
        return countRowsAffected != 0;
    }

    public boolean update(Interview interview) {
        DBPool pool = DBPool.getInstance();
        Connection connection = pool.getConnection();
        InterviewDAO interviewDAO = new InterviewDAO(connection);
        int countRowsAffected = interviewDAO.update(interview);
        pool.putConnection(connection);
        return countRowsAffected != 0;
    }

    public boolean add(Interview interview) {
        DBPool pool = DBPool.getInstance();
        Connection connection = pool.getConnection();
        InterviewDAO interviewDAO = new InterviewDAO(connection);
        int countRowsAffected = interviewDAO.add(interview);
        pool.putConnection(connection);
        return countRowsAffected != 0;
    }

    public boolean checkForExist(Interview interview) {
        DBPool pool = DBPool.getInstance();
        Connection connection = pool.getConnection();
        InterviewDAO interviewDAO = new InterviewDAO(connection);
        boolean isExist = interviewDAO.checkForExist(interview);
        pool.putConnection(connection);
        return isExist;
    }
}
