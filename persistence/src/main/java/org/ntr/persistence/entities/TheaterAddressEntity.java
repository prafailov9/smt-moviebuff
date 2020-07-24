package org.ntr.persistence.entities;

import org.ntr.persistence.entities.relations.Entity;

public class TheaterAddressEntity extends Entity {

    private Long theaterAddressEntityId;
    private String city;
    private String pinCode;
    private String state;
    private Integer streetNo;
    private String landmark;
    private String externalId;

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

    public Integer getStreetNo() {
        return streetNo;
    }

    public void setStreetNo(Integer streetNo) {
        this.streetNo = streetNo;
    }

    public String getLandmark() {
        return landmark;
    }

    public void setLandmark(String landmark) {
        this.landmark = landmark;
    }

    public String getExternalId() {
        return externalId;
    }

    public void setExternalId(String externalId) {
        this.externalId = externalId;
    }

    @Override
    public Long getId() {
        return theaterAddressEntityId;
    }

    public void setTheaterAddressEntityId(Long theaterAddressEntityId) {
        this.theaterAddressEntityId = theaterAddressEntityId;
    }
}
