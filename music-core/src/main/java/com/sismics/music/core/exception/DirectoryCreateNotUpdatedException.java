package com.sismics.music.core.exception;

public class DirectoryCreateNotUpdatedException extends Exception
{
    public DirectoryCreateNotUpdatedException()
	{
		super("Collection was not updated with the new Directory");
	}   
}
