package training.metofficeweather;

import training.metofficeweather.data.Root;

import java.io.IOException;

public interface SiteListFetcher {
    Root getSiteList() throws IOException;
}
