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

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Base64;
import java.util.List;

public class SpotifySearch implements SearchStrategy {
    public JsonObject search(String trackName) {
        // Implement spotify search
        System.out.println("Searching using Spotify");

        String client_id = "955bd7aa550643ad92fb619df39cbde6";
        String client_secret = "66815b8214c443b7a445b61432d1180f";
        String access_token = null;

        try {
            // Obtain client credentials token
            URL url = new URL("https://accounts.spotify.com/api/token");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("Authorization",
                    "Basic " + Base64.getEncoder().encodeToString((client_id + ":" + client_secret).getBytes()));
            con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            con.setDoOutput(true);
            String body = "grant_type=client_credentials";
            con.getOutputStream().write(body.getBytes());

            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            // Parse access token from response
            String responseStr = response.toString();
            access_token = responseStr.substring(responseStr.indexOf("access_token") + 15,
                    responseStr.indexOf("token_type") - 3);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        // System.out.println(access_token);
        if (access_token != null) {
            try {
                // Make GET request to Spotify API

                // https://api.spotify.com/v1/recommendations?limit=1&market=ES&seed_artists=4NHQUGzhtTLFvgF5SZesLK&seed_genres=classical%2Ccountry&seed_tracks=0c6xIDDpzE81m2q797ordA"
                // -H "Accept: application/json" -H "Content-Type: application/json" -H
                // "Authorization: Bearer
                // BQCvSGd40LljEBD8zZrxJOIPLvgw-DWDKhViuYgLn2g-C--nADMl3IM-hziSTLlAp6e_aG-a51Qgorjqh9OTI1tskhqAgXci_5-P28-tf98_08cHy1Sm3D8QqrksHxH-qJJCbYRyDke-xYw13EA515TlLy6QgAL8ho3gxSqu0jFSJgr8PdtnJWvMJZN6NaCK

                URL url = new URL("https://api.spotify.com/v1/search?q=" + trackName + "&type=track&limit=10&market=IN");
                HttpURLConnection con = (HttpURLConnection) url.openConnection();
                con.setRequestMethod("GET");
                con.setRequestProperty("Authorization", "Bearer " + access_token);
                con.setRequestProperty("Content-Type", "application/json");

                BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
                String inputLine;
                StringBuffer response = new StringBuffer();
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                    System.out.println(inputLine);
                }
                in.close();

                // Print response
                System.out.println(response.toString());
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }

        return null;
    }
}