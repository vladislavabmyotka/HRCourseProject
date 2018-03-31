package by.bsuir.abmyotkashevtsov.domain;

import java.math.BigDecimal;

public class Vacancy extends AbstractDomain {
    private int vacancyId;
    private String post;
    private String company;
    private BigDecimal salary;
    private String location;
    private int experience;
    private String english;
    private String text;
    private String conditionVacancy;
    private int employerId;
    private String employerInfo;

    public Vacancy() {
    }

    public Vacancy(String post, String company, BigDecimal salary, String location, int experience, String english,
                   String text, String conditionVacancy, int employerId) {
        this.post = post;
        this.company = company;
        this.salary = salary;
        this.location = location;
        this.experience = experience;
        this.english = english;
        this.text = text;
        this.conditionVacancy = conditionVacancy;
        this.employerId = employerId;
    }

    public Vacancy(int vacancyId, String post, String company, BigDecimal salary, String location, int experience,
                   String english, String text, String conditionVacancy) {
        this.vacancyId = vacancyId;
        this.post = post;
        this.company = company;
        this.salary = salary;
        this.location = location;
        this.experience = experience;
        this.english = english;
        this.text = text;
        this.conditionVacancy = conditionVacancy;
    }

    public int getVacancyId() {
        return vacancyId;
    }

    public void setVacancyId(int vacancyId) {
        this.vacancyId = vacancyId;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public String getEnglish() {
        return english;
    }

    public void setEnglish(String english) {
        this.english = english;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getConditionVacancy() {
        return conditionVacancy;
    }

    public void setConditionVacancy(String conditionVacancy) {
        this.conditionVacancy = conditionVacancy;
    }

    public int getEmployerId() {
        return employerId;
    }

    public void setEmployerId(int employerId) {
        this.employerId = employerId;
    }

    public String getEmployerInfo() {
        return employerInfo;
    }

    public void setEmployerInfo(String employerInfo) {
        this.employerInfo = employerInfo;
    }

    public String getMainInformation() {
        return post + ", " + company + ", " + salary;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Vacancy vacancy = (Vacancy) o;

        return vacancyId == vacancy.vacancyId && experience == vacancy.experience && employerId == vacancy.employerId
                && (post != null ? post.equals(vacancy.post) : vacancy.post == null)
                && (company != null ? company.equals(vacancy.company) : vacancy.company == null)
                && (salary != null ? salary.equals(vacancy.salary) : vacancy.salary == null)
                && (location != null ? location.equals(vacancy.location) : vacancy.location == null)
                && (english != null ? english.equals(vacancy.english) : vacancy.english == null)
                && (text != null ? text.equals(vacancy.text) : vacancy.text == null)
                && (conditionVacancy != null ? conditionVacancy.equals(vacancy.conditionVacancy) :
                        vacancy.conditionVacancy == null)
                && (employerInfo != null ? employerInfo.equals(vacancy.employerInfo) : vacancy.employerInfo == null);
    }

    @Override
    public int hashCode() {
        int result = vacancyId;
        result = 31 * result + (post != null ? post.hashCode() : 0);
        result = 31 * result + (company != null ? company.hashCode() : 0);
        result = 31 * result + (salary != null ? salary.hashCode() : 0);
        result = 31 * result + (location != null ? location.hashCode() : 0);
        result = 31 * result + experience;
        result = 31 * result + (english != null ? english.hashCode() : 0);
        result = 31 * result + (text != null ? text.hashCode() : 0);
        result = 31 * result + (conditionVacancy != null ? conditionVacancy.hashCode() : 0);
        result = 31 * result + employerId;
        result = 31 * result + (employerInfo != null ? employerInfo.hashCode() : 0);
        return result;
    }
}
