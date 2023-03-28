package com.sismics.music.rest.recommendationsfeature;

import java.util.*;

public interface RecommendationsStrategy {
    public List<String> getRecommendations(String playlistId);
}
