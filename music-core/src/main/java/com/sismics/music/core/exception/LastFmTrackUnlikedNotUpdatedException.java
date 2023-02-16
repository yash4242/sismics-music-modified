package com.sismics.music.core.exception;

public class LastFmTrackUnlikedNotUpdatedException extends Exception {

    public LastFmTrackUnlikedNotUpdatedException()
	{
		super("The unliked track was not updated in the LastFm profile");
	}   
}
