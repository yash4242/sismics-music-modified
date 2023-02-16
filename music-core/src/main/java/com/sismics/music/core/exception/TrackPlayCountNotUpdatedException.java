package com.sismics.music.core.exception;

public class TrackPlayCountNotUpdatedException extends Exception {

    public TrackPlayCountNotUpdatedException()
	{
		super("Track play count was not updated");
	}   
}
