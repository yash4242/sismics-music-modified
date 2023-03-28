package com.sismics.music.rest.recommendationsfeature;

import java.util.*;

class RecommendationsContext {
    private RecommendationsStrategy recommendationsStrategy;

    public RecommendationsContext(RecommendationsStrategy recommendationsStrategy) {
        this.recommendationsStrategy = recommendationsStrategy;
    }

    public List<String> getRecommendations(String playlistID) {
        List<String> recommendationsResult = recommendationsStrategy.getRecommendations(playlistID);
        return recommendationsResult;
    }
}
