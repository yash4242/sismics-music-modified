package com.sismics.music.core.exception;

public class DirectoryDeleteNotUpdatedException extends Exception
{
    public DirectoryDeleteNotUpdatedException()
	{
		super("Collection was not updated with the deleted Directory");
	}   
}

