package org.ntr.domainmodel.models;

import org.ntr.domainmodel.models.user.User;
import org.ntr.domainmodel.exceptions.user.EmptyPasswordException;
import org.ntr.domainmodel.exceptions.user.EmptyUsernameException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class UserTest {

    private User user;

    @BeforeEach
    public void setUp() {
        user = new User();
    }

    // uname, pass and e-mail should all not be null for the test to pass
    @Test
    public void tryToCreateUserNullUsername() {
        Assertions.assertThrows(EmptyUsernameException.class, () -> user.setUsername(null));
    }

    @Test
    public void tryToCreateUserNullPassword() {
        Assertions.assertThrows(EmptyPasswordException.class, () -> user.setPassword(null));
    }
}