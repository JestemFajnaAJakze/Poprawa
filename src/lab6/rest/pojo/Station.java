package lab6.rest.pojo;

import java.util.ArrayList;
import java.util.List;

public class Station {

    private String stationId;
    private Address address;

    public Station(String stationId, Address address) {
        this.stationId = stationId;
        this.address = address;
        this.airqualities = new ArrayList<>();
    }

    private List<AirqualityPOJO> airqualities;

    public String getStationId() {
        return stationId;
    }

    public void setStationId(String stationId) {
        this.stationId = stationId;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public List<AirqualityPOJO> getAirqualities() {
        return airqualities;
    }

    public void setAirqualities(List<AirqualityPOJO> airqualities) {
        this.airqualities = airqualities;
    }

}
