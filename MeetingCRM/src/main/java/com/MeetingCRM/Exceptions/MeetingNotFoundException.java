package com.MeetingCRM.Exceptions;

@SuppressWarnings("serial")
public class MeetingNotFoundException extends Exception {
	public MeetingNotFoundException(String message) {
		super(message);
	}
}
