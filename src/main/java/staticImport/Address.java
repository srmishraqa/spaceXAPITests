package staticImport;

public class Address {
    private int houseNo;
    private String streetName;
    private String city;
    private String state;
    private String country;
    public void setHouseNo(int houseNo) {
        this.houseNo = houseNo;
    }
    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }
    public void setCity(String city) {
        this.city = city;
    }
    public void setState(String state) {
        this.state = state;
    }
    public void setCountry(String country) {
        this.country = country;
    }
    public int getHouseNo() {
        return houseNo;
    }
    public String getStreetName() {
        return streetName;
    }
    public String getCity() {
        return city;
    }
    public String getState() {
        return state;
    }
    public String getCountry() {
        return country;
    }
}
