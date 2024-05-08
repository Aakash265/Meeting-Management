package com.MeetingCRM.Controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.MeetingCRM.Exceptions.MeetingCreationFailedException;
import com.MeetingCRM.Exceptions.MeetingNotFoundException;
import com.MeetingCRM.Exceptions.MeetingStatusUpdateFailedException;
import com.MeetingCRM.Model.Meeting;
import com.MeetingCRM.Service.MeetingManagementService;

@RestController
@RequestMapping("/api/meetings")
public class MeetingManagementController {
	
	@Autowired
	MeetingManagementService meetingService;
	
	Logger logger = LoggerFactory.getLogger(MeetingManagementController.class);
	
//	To schedule/add a new meeting
	@CrossOrigin("http://localhost:4200")
	@PostMapping("/schedule")
	public ResponseEntity<?> scheduleANewMeeting(@RequestBody Meeting meeting) {
		try {
			logger.info("Scheduling a new meeting...");
			return new ResponseEntity<>(meetingService.addMeeting(meeting), HttpStatus.CREATED);
		} catch (MeetingCreationFailedException e) {
			logger.warn(e.getMessage());
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	} 
	
	
//	To view all the available meetings
	@CrossOrigin("http://localhost:4200")
	@GetMapping("/view")
	public ResponseEntity<List<Meeting>> viewAllMeetings() {
		try {
			logger.info("Get My Meetings...");
			List<Meeting> list = meetingService.viewAllMeetings();
			if (!list.isEmpty()) {
				logger.info("Fetching meeting details...");
				return new ResponseEntity<>(list, HttpStatus.OK);
			} else
				return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
//	To view an available meeting
	@CrossOrigin("http://localhost:4200")
	@GetMapping("/view/{meetingId}")
	public ResponseEntity<?> viewMeetingById(@PathVariable long meetingId) {
		try {
			logger.info("Fetching details of meeting with ID: {}", meetingId);
			return new ResponseEntity<>(meetingService.viewMeetingById(meetingId), HttpStatus.OK);			
		} catch (MeetingNotFoundException e) {
			logger.warn(e.getMessage());
//			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			logger.error(e.getMessage() , meetingId);
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
//	To update meeting details
	@CrossOrigin("http://localhost:4200")
	@PutMapping("/update/{meetingId}")
	public ResponseEntity<?> updateMeeting(@PathVariable long meetingId, @RequestBody Meeting meeting) {
		try {
			logger.info("Updating meeting details...");
			return new ResponseEntity<>(meetingService.updateDetails(meetingId, meeting), HttpStatus.OK);
		} catch (MeetingStatusUpdateFailedException e) {
			logger.warn(e.getMessage());
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
//	To delete meeting
	@CrossOrigin("http://localhost:4200")
	@DeleteMapping("/delete/{meetingId}")
	public ResponseEntity<String> deleteMeeting(@PathVariable long meetingId) {
		try {
			logger.info("Deleting meeting details with ID: {}", meetingId);
			meetingService.deleteMeeting(meetingId);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (MeetingNotFoundException e) {
			logger.warn(e.getMessage());
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}



