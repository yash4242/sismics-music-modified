package com.sismics.music.rest.resource;

import com.google.common.base.Strings;
import com.sismics.music.core.model.context.AppContext;
import com.sismics.music.core.service.lastfm.LastFmService;
import com.sismics.rest.exception.ForbiddenClientException;
// import de.umass.lastfm.Album;
// import de.umass.lastfm.ImageSize;
import de.umass.lastfm.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import java.util.Collection;
import javax.json.JsonObject;

import com.sismics.music.rest.searchfeature.*;

/**
 * Album art REST resources.
 * 
 * @author jtremeaux
 */
@Path("/external")
public class ExternalSearchResource extends BaseResource {
    /**
     * Logger.
     */
    private static final Logger log = LoggerFactory.getLogger(ExternalSearchResource.class);

    /**
     * Search Spotify tracks
     *
     * @param query The query
     * @return Response
     */
    @GET
    @Path("spotify-search")
    public Response spotifySearch(
            @QueryParam("query") String query) {
        if (!authenticate()) {
            throw new ForbiddenClientException();
        }

        // Get the album arts
        JsonObjectBuilder response = Json.createObjectBuilder();
        JsonArray items = Json.createArrayBuilder().add("track1")
        .add("track2")
        .add("track3")
        .build();

        response.add("albumArts", items);

        return renderJson(response);
    }



    /**
     * Search LastFm tracks
     *
     * @param query The query
     * @return Response
     */
    @GET
    @Path("lastfm-search")
    public JsonObject lastfmSearch(
            @QueryParam("query") String query) {
        if (!authenticate()) {
            throw new ForbiddenClientException();
        }

        // Get the album arts
        // JsonObjectBuilder response = Json.createObjectBuilder();
        // JsonArray items = Json.createArrayBuilder().add("track1")
        // .add("track2")
        // .add("track3")
        // .add("track4")
        // .build();
        // return renderJson(response);
        SearchCall call= new SearchCall();
        JsonObject response = call.searchMain(1,query); 
        // System.out.println("hello from the other sidee");
        return response;
    }
}
