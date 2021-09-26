package training.metofficeweather;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.glassfish.jersey.jackson.JacksonFeature;
import training.metofficeweather.data.Root;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.nio.file.Paths;

public class FileSiteListFetcher implements SiteListFetcher {
    @Override
    public Root getSiteList() throws IOException {
        ObjectMapper mapper = new ObjectMapper();

        // convert JSON file to map
        Root metData = mapper.readValue(Paths.get("sampleMet.json").toFile(), Root.class);
        return metData;
    }

    @Override
    public String toString() {
        return "FileSiteListFetcher ";
    }
}
