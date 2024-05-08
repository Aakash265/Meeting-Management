package com.MeetingCRM.Exceptions;

@SuppressWarnings("serial")
public class MeetingStatusUpdateFailedException extends Exception {
	public MeetingStatusUpdateFailedException(String message) {
		super(message);
	}
}
