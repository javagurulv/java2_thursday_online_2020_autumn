package internet_store.core.domain;

public class CustomerAccount {

    private String login;
    private String password;

    public CustomerAccount(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

}
