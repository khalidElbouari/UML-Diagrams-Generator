package resources.org.mql.model.location;




public class Address {

    private String street;
    private String zipCode;
    private City city;  

    public Address(String street, String zipCode, City city) {
        this.street = street;
        this.zipCode = zipCode;
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public String getZipCode() {
        return zipCode;
    }

    public City getCity() {
        return city;
    }
}
