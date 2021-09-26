package training.metofficeweather;

import com.fasterxml.jackson.databind.ObjectMapper;
import training.metofficeweather.data.Root;

import java.nio.file.Paths;

public class FileSiteListFetcher implements SiteListFetcher {
    private String siteListFileName;
    public FileSiteListFetcher(String initSiteListFileName){
        siteListFileName = initSiteListFileName;
    }
    @Override
    public Root getSiteList() throws Exception {
        // create object mapper instance
        ObjectMapper mapper = new ObjectMapper();

        // convert JSON file to map
        Root metData = mapper.readValue(Paths.get(siteListFileName).toFile(), Root.class);
        return metData;
    }

    @Override
    public String toString() {
        return "FileSiteListFetcher";
    }
}
