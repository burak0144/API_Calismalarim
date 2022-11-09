package pojos;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GMICountryDataPostPojo {
    private String name;

    public GMICountryDataPostPojo() {
    }

    public GMICountryDataPostPojo(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "GMICountryDataPostPojo{" +
                "name='" + name + '\'' +
                '}';
    }
}
