package com.sismics.music.rest.searchfeature;

class SearchContext {
    private SearchStrategy searchStrategy;

    public SearchContext(SearchStrategy searchStrategy) {
        this.searchStrategy = searchStrategy;
    }

    public void search(String trackName) {
        searchStrategy.search(trackName);
    }
}
