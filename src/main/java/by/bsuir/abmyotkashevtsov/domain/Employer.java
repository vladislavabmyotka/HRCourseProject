package by.bsuir.abmyotkashevtsov.domain;

public class Employer extends AbstractDomain {
    private int employerId;
    private String surname;
    private String name;
    private String lastname;
    private String address;
    private String phone;
    private String email;
    private String company;
    private int accountId;

    public Employer() {
    }

    public Employer(String surname, String name, String lastname, String address, String phone, String email,
                    String company, int accountId) {
        this.surname = surname;
        this.name = name;
        this.lastname = lastname;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.company = company;
        this.accountId = accountId;
    }

    public Employer(int employerId, String surname, String name, String lastname, String address, String phone,
                    String email, String company) {
        this.employerId = employerId;
        this.surname = surname;
        this.name = name;
        this.lastname = lastname;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.company = company;
    }

    public int getEmployerId() {
        return employerId;
    }

    public void setEmployerId(int employerId) {
        this.employerId = employerId;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
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

        Employer employer = (Employer) o;

        return employerId == employer.employerId && accountId == employer.accountId
                && (surname != null ? surname.equals(employer.surname) : employer.surname == null)
                && (name != null ? name.equals(employer.name) : employer.name == null)
                && (lastname != null ? lastname.equals(employer.lastname) : employer.lastname == null)
                && (address != null ? address.equals(employer.address) : employer.address == null)
                && (phone != null ? phone.equals(employer.phone) : employer.phone == null)
                && (email != null ? email.equals(employer.email) : employer.email == null)
                && (company != null ? company.equals(employer.company) : employer.company == null);
    }

    @Override
    public int hashCode() {
        int result = employerId;
        result = 31 * result + (surname != null ? surname.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (lastname != null ? lastname.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (phone != null ? phone.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (company != null ? company.hashCode() : 0);
        result = 31 * result + accountId;
        return result;
    }
}
