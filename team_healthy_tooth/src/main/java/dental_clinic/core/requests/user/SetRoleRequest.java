package dental_clinic.core.requests.user;

public class SetRoleRequest {

    private Long userId;
    private Long roleId;

    public SetRoleRequest() { }

    public SetRoleRequest(Long userId, Long roleId) {
        this.userId = userId;
        this.roleId = roleId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getRoleId() {
        return roleId;
    }
}
