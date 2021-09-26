package training.metofficeweather;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.glassfish.jersey.jackson.JacksonFeature;
import training.metofficeweather.data.Root;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import java.nio.file.Paths;

public class RestSiteListFetcher implements SiteListFetcher {
    private String siteListUrl;
    public RestSiteListFetcher(String initSiteListUrl){
        siteListUrl = initSiteListUrl;
    }
        @Override
    public Root getSiteList() throws Exception {
        try {
            Client client = ClientBuilder.newBuilder().register(JacksonFeature.class).build();
            Root root = client.target(siteListUrl)
                    .request(MediaType.APPLICATION_JSON)
                    .get(Root.class);
            return root;
        } catch ( Exception e){
            e.printStackTrace();
            throw new Exception("Failed to read Site data", e);
        }
    }

    @Override
    public String toString() {
        return "RestSiteListFetcher";
    }
}
