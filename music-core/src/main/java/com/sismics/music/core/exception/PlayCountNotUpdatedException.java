package com.sismics.music.core.exception;

public class PlayCountNotUpdatedException extends Exception {

    public PlayCountNotUpdatedException()
	{
		super("Play count was not updated/incremented");
	}   
}
