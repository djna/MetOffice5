package training.metofficeweather;

import training.metofficeweather.data.Root;

interface SiteListFetcher {
    public Root getSiteList() throws Exception;
}
