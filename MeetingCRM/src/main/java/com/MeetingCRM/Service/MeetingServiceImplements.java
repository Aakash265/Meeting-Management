package com.MeetingCRM.Service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.MeetingCRM.Exceptions.MeetingCreationFailedException;
import com.MeetingCRM.Exceptions.MeetingNotFoundException;
import com.MeetingCRM.Exceptions.MeetingStatusUpdateFailedException;
import com.MeetingCRM.Model.Meeting;
import com.MeetingCRM.Repository.MeetingManagementRepository;
import com.MeetingCRM.payload.UserDTO;

@Service
public class MeetingServiceImplements implements MeetingManagementService {
	@Autowired
	MeetingManagementRepository repo;
	
	Logger logger = LoggerFactory.getLogger(MeetingServiceImplements.class);
	
	@Autowired
	private RestTemplate restTemplate;

//	Find all meetings
	public List<Meeting> viewAllMeetings() {
		List<Meeting> allMeetings = repo.findAll();
		for (Meeting meet : allMeetings) {
			UserDTO user = restTemplate.getForObject("http://localhost:8080/api/users/getUser/" + meet.getUserId() , UserDTO.class);
			meet.setUser(user);
		}
		return allMeetings;
	}
	
	
//	Find meeting by meeting ID
	public Meeting viewMeetingById(long meetingId) throws MeetingNotFoundException {
		Meeting meet = repo.findById(meetingId).orElseThrow(
				() -> new MeetingNotFoundException("Meeting ID not valid. Please enter a valid meeting ID.")
				);
		
//		Fetching user details of the above meeting by USER SERVICE
		UserDTO user = restTemplate.getForObject("http://localhost:8080/api/users/getUser/" + meet.getUserId() , UserDTO.class);

		meet.setUser(user);
		
		return meet;
	}
	
	
	
//	Add a new meeting
	public Meeting addMeeting(Meeting meeting) throws MeetingCreationFailedException {
		
//		To check that meeting start date should come before meeting end date
		if (meeting.getMeetingStartDate().isAfter(meeting.getMeetingEndDate())) {
			throw new MeetingCreationFailedException("Meeting start date should come before meeting end date.");
		}
		
		meeting.setUserStatus("Pending");
		repo.save(meeting);
		return meeting;
	}
	
	
//	Update meeting details
	public Meeting updateDetails(long meetingId, Meeting meeting) throws MeetingStatusUpdateFailedException, MeetingNotFoundException {

//		To throw an exception is meeting is not found.
		Meeting meet = repo.findById(meetingId).orElseThrow(
				() -> new MeetingNotFoundException("Meeting ID not valid. Please enter a valid meeting ID.")
				);
		
//		To check whether the meeting is completed or pending
		if (meet.getStatus().equals("Completed"))
			throw new MeetingStatusUpdateFailedException("Meeting already completed.");
		
//		To check that meeting start date should come before meeting end date
		if (meeting.getMeetingStartDate().isAfter(meeting.getMeetingEndDate())) {
			throw new MeetingStatusUpdateFailedException("Meeting start date should come before meeting end date.");
		}
	
		meet.setMeetingStartDate(meeting.getMeetingStartDate());
		meet.setMeetingEndDate(meeting.getMeetingEndDate());
		meet.setStatus(meeting.getStatus());
		meet.setUserId(meeting.getUserId());
		meet.setUserStatus(meeting.getUserStatus());
		repo.save(meet);
		
		if (meet.getStatus().equalsIgnoreCase("Cancelled"))
			deleteMeeting(meetingId);
		
		logger.info("Meeting details updated");
		
		return meet;
	}
	
	
//	Delete meeting details
	public void deleteMeeting(long meetingId) throws MeetingNotFoundException {
		Optional<Meeting> meet = repo.findById(meetingId);
		if (!meet.isPresent())
			throw new MeetingNotFoundException("Meeting ID not valid. Please enter a valid meeting ID.");
		repo.deleteById(meetingId);
	}
} 