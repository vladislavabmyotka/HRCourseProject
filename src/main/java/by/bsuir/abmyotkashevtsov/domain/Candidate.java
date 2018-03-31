package by.bsuir.abmyotkashevtsov.domain;

public class Candidate extends AbstractDomain {
    private int candidateId;
    private String surname;
    private String name;
    private String lastname;
    private int age;
    private String email;
    private String address;
    private String citizenship;
    private String phone;
    private String post;
    private String education;
    private int experience;
    private String english;
    private String skill;
    private int accountId;

    public Candidate() {
    }

    public Candidate(int candidateId, String surname, String name, String lastname, int age, String email,
                     String address, String citizenship, String phone, String post, String education, int experience,
                     String english, String skill) {
        this.candidateId = candidateId;
        this.surname = surname;
        this.name = name;
        this.lastname = lastname;
        this.age = age;
        this.email = email;
        this.address = address;
        this.citizenship = citizenship;
        this.phone = phone;
        this.post = post;
        this.education = education;
        this.experience = experience;
        this.english = english;
        this.skill = skill;
    }

    public Candidate(String surname, String name, String lastname, int age, String email, String address,
                     String citizenship, String phone, String post, String education, int experience, String english,
                     String skill, int accountId) {
        this.surname = surname;
        this.name = name;
        this.lastname = lastname;
        this.age = age;
        this.email = email;
        this.address = address;
        this.citizenship = citizenship;
        this.phone = phone;
        this.post = post;
        this.education = education;
        this.experience = experience;
        this.english = english;
        this.skill = skill;
        this.accountId = accountId;
    }

    public int getCandidateId() {
        return candidateId;
    }

    public void setCandidateId(int candidateId) {
        this.candidateId = candidateId;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCitizenship() {
        return citizenship;
    }

    public void setCitizenship(String citizenship) {
        this.citizenship = citizenship;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public String getSkill() {
        return skill;
    }

    public void setSkill(String skill) {
        this.skill = skill;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public String getEnglish() {
        return english;
    }

    public void setEnglish(String english) {
        this.english = english;
    }

    public String getMainInformation() {
        return surname + " " + name + " " + (lastname != null ? lastname : "")  + "\n" + email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Candidate candidate = (Candidate) o;

        return candidateId == candidate.candidateId && age == candidate.age && experience == candidate.experience
                && accountId == candidate.accountId
                && (surname != null ? surname.equals(candidate.surname) : candidate.surname == null)
                && (name != null ? name.equals(candidate.name) : candidate.name == null)
                && (lastname != null ? lastname.equals(candidate.lastname) : candidate.lastname == null)
                && (email != null ? email.equals(candidate.email) : candidate.email == null)
                && (address != null ? address.equals(candidate.address) : candidate.address == null)
                && (citizenship != null ? citizenship.equals(candidate.citizenship) : candidate.citizenship == null)
                && (phone != null ? phone.equals(candidate.phone) : candidate.phone == null)
                && (post != null ? post.equals(candidate.post) : candidate.post == null)
                && (education != null ? education.equals(candidate.education) : candidate.education == null)
                && (skill != null ? skill.equals(candidate.skill) : candidate.skill == null)
                && (english != null ? english.equals(candidate.english) : candidate.english == null);
    }

    @Override
    public int hashCode() {
        int result = candidateId;
        result = 31 * result + (surname != null ? surname.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (lastname != null ? lastname.hashCode() : 0);
        result = 31 * result + age;
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (citizenship != null ? citizenship.hashCode() : 0);
        result = 31 * result + (phone != null ? phone.hashCode() : 0);
        result = 31 * result + (post != null ? post.hashCode() : 0);
        result = 31 * result + (education != null ? education.hashCode() : 0);
        result = 31 * result + experience;
        result = 31 * result + (skill != null ? skill.hashCode() : 0);
        result = 31 * result + accountId;
        result = 31 * result + (english != null ? english.hashCode() : 0);
        return result;
    }
}
