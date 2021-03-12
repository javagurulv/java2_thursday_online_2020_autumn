package dental_clinic.core.requests.user;


import dental_clinic.core.domain.Role;

public class AddUserRequest {

    private String name;
    private String surname;
    private String login;
    private String password;
    private Long roleId;

    public AddUserRequest() { }

    public AddUserRequest(String name, String surname, String login, String password, Long roleId) {
        this.name = name;
        this.surname = surname;
        this.login = login;
        this.password = password;
        this.roleId = roleId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
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

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }
}
