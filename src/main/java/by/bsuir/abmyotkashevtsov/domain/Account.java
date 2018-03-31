package by.bsuir.abmyotkashevtsov.domain;

public class Account extends AbstractDomain {
    private int accountId;
    private String login;
    private String password;
    private String attachment;

    public Account() {
    }

    public Account(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public Account(String login, String password, String attachment) {
        this.login = login;
        this.password = password;
        this.attachment = attachment;
    }

    public Account(int accountId, String login, String password, String attachment) {
        this.accountId = accountId;
        this.login = login;
        this.password = password;
        this.attachment = attachment;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAttachment() {
        return attachment;
    }

    public void setAttachment(String attachment) {
        this.attachment = attachment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Account account = (Account) o;

        return accountId == account.accountId && (login != null ? login.equals(account.login) : account.login == null)
                && (password != null ? password.equals(account.password) : account.password == null)
                && (attachment != null ? attachment.equals(account.attachment) : account.attachment == null);
    }

    @Override
    public int hashCode() {
        int result = accountId;
        result = 31 * result + (login != null ? login.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (attachment != null ? attachment.hashCode() : 0);
        return result;
    }
}
