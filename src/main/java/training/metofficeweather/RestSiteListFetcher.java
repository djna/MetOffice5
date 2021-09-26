package training.metofficeweather;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.glassfish.jersey.jackson.JacksonFeature;
import training.metofficeweather.data.Root;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.nio.file.Paths;

public class RestSiteListFetcher implements SiteListFetcher {
    static final String siteListUrl =  "http://datapoint.metoffice.gov.uk/public/data/val/wxfcs/all/json/sitelist?key=7581460d-7f90-4bd0-bacb-e72e36e3b557";

    @Override
    public Root getSiteList() throws IOException {
        Client client = ClientBuilder.newBuilder().register(JacksonFeature.class).build();
        Root root = client.target(siteListUrl)
                .request(MediaType.APPLICATION_JSON)
                .get(Root.class);
        return root;
    }

    @Override
    public String toString() {
        return "RestSiteListFetcher";
    }
}
