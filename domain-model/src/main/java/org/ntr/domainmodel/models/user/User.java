package org.ntr.domainmodel.models.user;

import org.ntr.domainmodel.models.Model;
import org.ntr.domainmodel.models.role.Role;
import org.ntr.domainmodel.exceptions.user.EmptyEmailException;
import org.ntr.domainmodel.exceptions.user.EmptyPasswordException;
import org.ntr.domainmodel.exceptions.user.EmptyUsernameException;
import org.ntr.domainmodel.models.reservation.Reservation;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class User extends Model implements Serializable {

    // data which should not be null when persisting to DB
    private String email;
    private String username;
    private String password;


    private Role role; // one user can have only one role in the app
    private List<Reservation> reservations; // one user can make many reservations for different screenings

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public Role getRole() {
        return role;
    }

    public List<Reservation> getReservations() {
        return reservations;
    }

    public void setUsername(String username) {
        if (Objects.isNull(username) || username.isEmpty()) throw new EmptyUsernameException();
        this.username = username;
    }

    public void setPassword(String password) {
        if (Objects.isNull(password) || username.isEmpty()) throw new EmptyPasswordException();
        this.password = password;
    }

    public void setEmail(String email) {
        if (Objects.isNull(email) || username.isEmpty()) throw new EmptyEmailException();
        this.email = email;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public void setReservations(List<Reservation> reservations) {
        this.reservations = reservations;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(username, user.username) &&
                Objects.equals(password, user.password) &&
                Objects.equals(email, user.email) &&
                Objects.equals(getExternalId(), user.getExternalId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, password, email, getExternalId());
    }
}
