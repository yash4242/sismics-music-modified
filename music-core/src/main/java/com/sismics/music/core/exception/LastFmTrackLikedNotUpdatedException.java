package com.sismics.music.core.exception;

public class LastFmTrackLikedNotUpdatedException extends Exception {

    public LastFmTrackLikedNotUpdatedException()
	{
		super("The liked track was not updated in the LastFm profile");
	}   
}
