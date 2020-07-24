package org.ntr.domainmodel.models.theater;

import org.ntr.domainmodel.models.Model;
import org.ntr.domainmodel.models.screening.Screening;
import org.ntr.domainmodel.models.seat.Seat;

import java.util.List;
import java.util.Objects;

public class Theater extends Model {

    private String theaterName;
    private Integer seatCapacity;

    // --------- relations
    private List<Seat> seats; // one hall has many seats
    private List<Screening> screenings; // one hall screens only one film at a given time
    private TheaterAddress address; // one-to-one

    public String getTheaterName() {
        return theaterName;
    }

    public void setTheaterName(String theaterName) {
        this.theaterName = theaterName;
    }

    public List<Seat> getSeats() {
        return seats;
    }

    public void setSeats(List<Seat> seats) {
        this.seats = seats;
    }

    public List<Screening> getScreenings() {
        return screenings;
    }

    public void setScreenings(List<Screening> screenings) {
        this.screenings = screenings;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Theater theater = (Theater) o;
        return theaterName.equals(theater.theaterName) &&
                getExternalId().equals(theater.getExternalId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(theaterName, getExternalId());
    }

    public Integer getSeatCapacity() {
        return seatCapacity;
    }

    public void setSeatCapacity(Integer seatCapacity) {
        this.seatCapacity = seatCapacity;
    }

    public TheaterAddress getAddress() {
        return address;
    }

    public void setAddress(TheaterAddress address) {
        this.address = address;
    }
}
