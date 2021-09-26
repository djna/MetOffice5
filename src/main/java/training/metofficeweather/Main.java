package training.metofficeweather;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import training.metofficeweather.data.Root;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class Main {
    static final Logger theLogger = LogManager.getLogger("Weather");
    static final String defaultSiteListUrl =  "http://datapoint.metoffice.gov.uk/public/data/val/wxfcs/all/json/sitelist?key=7581460d-7f90-4bd0-bacb-e72e36e3b557";

    public static void main(String[] args) {
        theLogger.info("Application Started");
        Map<String, SiteListFetcher> fetcherMap = new HashMap<>();

        String siteListUrl = System.getProperty("org.djna.siteRest", defaultSiteListUrl);
        fetcherMap.put("REST",  new RestSiteListFetcher(siteListUrl));

        String siteFileName = System.getProperty("org.djna.siteFile", "sampleMet.json");
        fetcherMap.put("FILE", new FileSiteListFetcher(siteFileName));

        String dataFrom = (args.length > 0) ? args[0] : "FILE";
        SiteListFetcher fetcher = fetcherMap.get(dataFrom);

        if ( fetcher == null){
            theLogger.error("No fetcher available for {}", dataFrom);
            System.exit(1);
        }

        try {
            Root rootFromRest = fetcher.getSiteList();
            theLogger.info("result from {} {}", fetcher, rootFromRest);
        } catch (Exception ex) {
            theLogger.error("Failed to get Site List {}", ex.getMessage());
        }
    }




}
