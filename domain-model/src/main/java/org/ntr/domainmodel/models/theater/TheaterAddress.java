package org.ntr.domainmodel.models.theater;

import org.ntr.domainmodel.models.Model;

import java.util.Objects;

public class TheaterAddress extends Model {

    private String city;
    private String pinCode;
    private String state;
    private String streetNo;
    private String landmark;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPinCode() {
        return pinCode;
    }

    public void setPinCode(String pinCode) {
        this.pinCode = pinCode;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getStreetNo() {
        return streetNo;
    }

    public void setStreetNo(String streetNo) {
        this.streetNo = streetNo;
    }

    public String getLandmark() {
        return landmark;
    }

    public void setLandmark(String landmark) {
        this.landmark = landmark;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TheaterAddress that = (TheaterAddress) o;
        return Objects.equals(city, that.city) &&
                Objects.equals(pinCode, that.pinCode) &&
                Objects.equals(state, that.state) &&
                Objects.equals(streetNo, that.streetNo) &&
                Objects.equals(landmark, that.landmark) &&
                getExternalId().equals(that.getExternalId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(city, pinCode, state, streetNo, landmark, getExternalId());
    }
}
