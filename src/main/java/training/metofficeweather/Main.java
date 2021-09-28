package training.metofficeweather;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.glassfish.jersey.jackson.JacksonFeature;
import training.metofficeweather.data.Root;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class Main {
    static final Logger theLogger = LogManager.getLogger("Weather");


    public static void main(String[] args) {

        theLogger.info("Application Started");

        String dataSource = (args.length == 0 ) ? "FILE" : args[0];
        SiteListFetcher siteListFetcher = getSiteListFetcher(dataSource);

        try {
            Root metData = siteListFetcher.getSiteList();
            theLogger.info("Met data from {}: {} ", siteListFetcher, metData);
        } catch ( Exception e){
            e.printStackTrace();
        }
        ApplicationProperties applicationProperties = new ApplicationProperties();
        String weatherKey = "org.djna.weather.99999" ;
        String weatherText = applicationProperties.getProperty(weatherKey, "Not Understood");
        theLogger.info("Weather: {}", weatherText);
    }

    private static SiteListFetcher getSiteListFetcher(String dataSource) {
        SiteListFetcher fileSiteListFetcher = new FileSiteListFetcher();
        SiteListFetcher restSiteListFetcher = new RestSiteListFetcher();

        HashMap<String, SiteListFetcher> fetchers = new HashMap<>();

        fetchers.put("FILE", fileSiteListFetcher );
        fetchers.put("REST", restSiteListFetcher );


        SiteListFetcher siteListFetcher = fetchers.getOrDefault(dataSource, fileSiteListFetcher);
        return siteListFetcher;
    }


}
