package training.metofficeweather.data;

import com.fasterxml.jackson.annotation.JsonProperty;


public class Locations {
    @JsonProperty(value="Location")
    Location[] locations;

    @Override
    public String toString() {
        return "Locations{" +
                "locations=" + locations.length +
                '}';
    }
}
