package pojos;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GMICountryPostPojo {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public GMICountryPostPojo() {
    }

    public GMICountryPostPojo(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "CountryPost{" +
                "name='" + name + '\'' +
                '}';
    }
}
