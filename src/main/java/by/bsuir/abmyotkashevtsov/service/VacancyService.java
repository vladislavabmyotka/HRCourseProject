package by.bsuir.abmyotkashevtsov.service;

import by.bsuir.abmyotkashevtsov.domain.Vacancy;
import by.bsuir.abmyotkashevtsov.repository.DBPool;
import by.bsuir.abmyotkashevtsov.repository.VacancyDAO;

import java.sql.Connection;
import java.util.List;

/**
 * Class-service for working with the table "vacancy" of the database.
 */
public class VacancyService {

    public List<Vacancy> takeAll() {
        DBPool pool = DBPool.getInstance();
        Connection connection = pool.getConnection();
        VacancyDAO vacancyDAO = new VacancyDAO(connection);
        List<Vacancy> vacancies = vacancyDAO.findAll();
        pool.putConnection(connection);
        return vacancies;
    }

    public Vacancy findById(int vacancyId) {
        DBPool pool = DBPool.getInstance();
        Connection connection = pool.getConnection();
        VacancyDAO vacancyDAO = new VacancyDAO(connection);
        Vacancy candidate = vacancyDAO.findById(vacancyId);
        pool.putConnection(connection);
        return candidate;
    }

    public List<Vacancy> findAllByEmployerId(int employerId) {
        DBPool pool = DBPool.getInstance();
        Connection connection = pool.getConnection();
        VacancyDAO vacancyDAO = new VacancyDAO(connection);
        List<Vacancy> vacancies = vacancyDAO.findAllByEmployerId(employerId);
        pool.putConnection(connection);
        return vacancies;
    }

    public List<Vacancy> findAllByKeyword(String keyword) {
        DBPool pool = DBPool.getInstance();
        Connection connection = pool.getConnection();
        VacancyDAO vacancyDAO = new VacancyDAO(connection);
        List<Vacancy> vacancies = vacancyDAO.findAllByKeyword(keyword);
        pool.putConnection(connection);
        return vacancies;
    }

    public boolean delete(int vacancyId) {
        DBPool pool = DBPool.getInstance();
        Connection connection = pool.getConnection();
        VacancyDAO vacancyDAO = new VacancyDAO(connection);
        int countRowsAffected = vacancyDAO.delete(vacancyId);
        pool.putConnection(connection);
        return countRowsAffected != 0;
    }

    public boolean update(Vacancy vacancy) {
        DBPool pool = DBPool.getInstance();
        Connection connection = pool.getConnection();
        VacancyDAO vacancyDAO = new VacancyDAO(connection);
        int countRowsAffected = vacancyDAO.update(vacancy);
        pool.putConnection(connection);
        return countRowsAffected != 0;
    }

    public boolean add(Vacancy vacancy) {
        DBPool pool = DBPool.getInstance();
        Connection connection = pool.getConnection();
        VacancyDAO vacancyDAO = new VacancyDAO(connection);
        int countRowsAffected = vacancyDAO.add(vacancy);
        pool.putConnection(connection);
        return countRowsAffected != 0;
    }
}
