import play.api.mvc.EssentialFilter;
import play.filters.cors.CORSFilter;
import play.filters.gzip.GzipFilter;
import play.http.HttpFilters;

import javax.inject.Inject;

public class Filters implements HttpFilters {

    @Inject
    GzipFilter gzipFilter;

    @Inject
    CORSFilter corsFilter;

    public EssentialFilter[] filters() {
        return new EssentialFilter[] { gzipFilter, corsFilter };
    }
}