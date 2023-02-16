package com.sismics.music.core.exception;

public class PlayStartNotUpdatedException extends Exception {

    public PlayStartNotUpdatedException()
	{
		super("Now playing track was not updated");
	}   
}
