package java2.application_target_list.core.junittests.matchers;


import java2.application_target_list.core.domain.User;
import org.mockito.ArgumentMatcher;

public class UserMatcher implements ArgumentMatcher<User> {

    private String firstName;
    private String lastName;

    public UserMatcher(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Override
    public boolean matches(User user) {
        return user.getFirstName().equals(firstName)
                && user.getLastName().equals(lastName);
    }
}
