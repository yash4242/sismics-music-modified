package com.sismics.music.rest.searchfeature.strategies;

import com.sismics.music.rest.searchfeature.SearchStrategy;
import com.sismics.music.core.dao.dbi.AlbumDao;
import com.sismics.music.core.dao.dbi.ArtistDao;
import com.sismics.music.core.dao.dbi.TrackDao;
import com.sismics.music.core.dao.dbi.criteria.AlbumCriteria;
import com.sismics.music.core.dao.dbi.criteria.ArtistCriteria;
import com.sismics.music.core.dao.dbi.criteria.TrackCriteria;
import com.sismics.music.core.dao.dbi.dto.AlbumDto;
import com.sismics.music.core.dao.dbi.dto.ArtistDto;
import com.sismics.music.core.dao.dbi.dto.TrackDto;
import com.sismics.music.core.util.dbi.PaginatedList;
import com.sismics.music.core.util.dbi.PaginatedLists;
import com.sismics.music.rest.util.JsonUtil;
import com.sismics.rest.exception.ForbiddenClientException;
import com.sismics.rest.util.Validation;
import com.sismics.music.core.model.context.AppContext;
import com.sismics.music.core.service.lastfm.LastFmService;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Collection;
import java.util.Collections;
import de.umass.lastfm.*;

public class LastfmSearch implements SearchStrategy {
    Track t;
    public JsonObject search(String trackName) {
        // Implement lastfm search
        // System.out.println("Searching using LastFm");
        final LastFmService lastFmService = AppContext.getInstance().getLastFmService();
        Collection<Track> result = lastFmService.searchTrack(trackName, 10);
        JsonArrayBuilder trackArray = Json.createArrayBuilder();
        // System.out.println("result: " + result);
        for (Track track : result) {
            trackArray.add(Json.createObjectBuilder()
                    .add("name", track.getName())
                    .add("artist", track.getArtist()));
        }

        JsonObject response = Json.createObjectBuilder()
                .add("status", "ok")
                .add("tracks", trackArray)
                .build();
        System.out.println("response: " + response);
        // return the response
        return response;
        // return Response.ok().entity(response).build();
    }
}