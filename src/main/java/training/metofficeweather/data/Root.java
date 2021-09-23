package training.metofficeweather.data;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Root {
    @JsonProperty(value="Locations") // For ObjectMapper
    private Locations locations;

    @Override
    public String toString() {
        return "Root={" +
                "locations=" + locations +
                '}';
    }
}
