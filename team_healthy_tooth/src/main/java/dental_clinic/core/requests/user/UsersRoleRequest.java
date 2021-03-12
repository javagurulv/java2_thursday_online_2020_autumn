package dental_clinic.core.requests.user;

import dental_clinic.core.domain.Role;

public class UsersRoleRequest {

    private Long userId;

    public UsersRoleRequest() { }

    public UsersRoleRequest(Long userId) {
        this.userId = userId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
