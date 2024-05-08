package com.MeetingCRM.Service;

import java.util.List;

import com.MeetingCRM.Exceptions.MeetingCreationFailedException;
import com.MeetingCRM.Exceptions.MeetingNotFoundException;
import com.MeetingCRM.Exceptions.MeetingStatusUpdateFailedException;
import com.MeetingCRM.Model.Meeting;

public interface MeetingManagementService {

//	For viewing all meetings
	public List<Meeting> viewAllMeetings();

//	For viewing meeting by ID
	public Meeting viewMeetingById(long meetingId) throws MeetingNotFoundException;

//	For adding new meeting details
	public Meeting addMeeting(Meeting meeting) throws MeetingCreationFailedException;

//	For updating meeting details
	public Meeting updateDetails(long meetingId, Meeting meeting) throws MeetingStatusUpdateFailedException, MeetingNotFoundException;

//	For deleting meeting
	public void deleteMeeting(long meetingId) throws MeetingNotFoundException;

	
}
