package training.metofficeweather;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.glassfish.jersey.jackson.JacksonFeature;
import training.metofficeweather.data.Root;

import java.util.HashMap;

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
