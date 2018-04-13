package by.bsuir.abmyotkashevtsov;

import by.bsuir.abmyotkashevtsov.validator.CandidateEmployerVacancyValidator;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CandidateEmployerVacancyValidatorTest {

    @Test
    public void correctIdTest() {
        String id = "4";
        Assert.assertTrue(CandidateEmployerVacancyValidator.checkID(id));
    }

    @Test
    public void incorrectIdTest() {
        String id = "-1";
        Assert.assertFalse(CandidateEmployerVacancyValidator.checkID(id));
    }

    @Test
    public void nullIdTest() {
        String id = null;
        Assert.assertFalse(CandidateEmployerVacancyValidator.checkID(id));
    }

    @Test
    public void correctNamesTest() {
        String name = "Duke";
        Assert.assertTrue(CandidateEmployerVacancyValidator.checkNames(name));
    }

    @Test
    public void incorrectNamesTest() {
        String name = "D1Uke21";
        Assert.assertFalse(CandidateEmployerVacancyValidator.checkNames(name));
    }

    @Test
    public void nullNamesTest() {
        String name = null;
        Assert.assertFalse(CandidateEmployerVacancyValidator.checkNames(name));
    }

    @Test
    public void correctLastnameTest() {
        String lastname = "Duke";
        Assert.assertTrue(CandidateEmployerVacancyValidator.checkLastname(lastname));
    }

    @Test
    public void incorrectLastnameTest() {
        String lastname = "D1Uke21";
        Assert.assertFalse(CandidateEmployerVacancyValidator.checkLastname(lastname));
    }

    @Test
    public void nullLastnameTest() {
        String lastname = null;
        Assert.assertTrue(CandidateEmployerVacancyValidator.checkLastname(lastname));
    }

    @Test
    public void correctAgeTest() {
        String age = "18";
        Assert.assertTrue(CandidateEmployerVacancyValidator.checkAge(age));
    }

    @Test
    public void incorrectAgeTest() {
        String age = "225";
        Assert.assertFalse(CandidateEmployerVacancyValidator.checkAge(age));
    }

    @Test
    public void nullAgeTest() {
        String age = null;
        Assert.assertFalse(CandidateEmployerVacancyValidator.checkAge(age));
    }

    @Test
    public void correctEmailTest() {
        String email = "qwertq23@gmail.com";
        Assert.assertTrue(CandidateEmployerVacancyValidator.checkEmail(email));
    }

    @Test
    public void incorrectEmailTest() {
        String email = "agsfdgds";
        Assert.assertFalse(CandidateEmployerVacancyValidator.checkEmail(email));
    }

    @Test
    public void nullEmailTest() {
        String email = null;
        Assert.assertFalse(CandidateEmployerVacancyValidator.checkEmail(email));
    }

    @Test
    public void correctCitizenshipTest() {
        String citizenship = "Республика Беларусь";
        Assert.assertTrue(CandidateEmployerVacancyValidator.checkCitizenship(citizenship));
    }

    @Test
    public void incorrectCitizenshipTest() {
        String citizenship = "523235";
        Assert.assertFalse(CandidateEmployerVacancyValidator.checkCitizenship(citizenship));
    }

    @Test
    public void nullCitizenshipTest() {
        String citizenship = null;
        Assert.assertFalse(CandidateEmployerVacancyValidator.checkCitizenship(citizenship));
    }

    @Test
    public void correctPhoneTest() {
        String phone = "8 (029) 123-45-67";
        Assert.assertTrue(CandidateEmployerVacancyValidator.checkPhone(phone));
    }

    @Test
    public void incorrectPhoneTest() {
        String phone = "bsdbdss";
        Assert.assertFalse(CandidateEmployerVacancyValidator.checkPhone(phone));
    }

    @Test
    public void nullPhoneTest() {
        String phone = null;
        Assert.assertFalse(CandidateEmployerVacancyValidator.checkPhone(phone));
    }

    @Test
    public void correctExperienceTest() {
        String experience = "8";
        Assert.assertTrue(CandidateEmployerVacancyValidator.checkExperience(experience));
    }

    @Test
    public void incorrectExperienceTest() {
        String experience = "bsdbdss";
        Assert.assertFalse(CandidateEmployerVacancyValidator.checkExperience(experience));
    }

    @Test
    public void nullExperienceTest() {
        String experience = null;
        Assert.assertFalse(CandidateEmployerVacancyValidator.checkExperience(experience));
    }
}
