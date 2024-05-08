package com.MeetingCRM;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.RestTemplate;

import com.MeetingCRM.Exceptions.MeetingCreationFailedException;
import com.MeetingCRM.Exceptions.MeetingNotFoundException;
import com.MeetingCRM.Exceptions.MeetingStatusUpdateFailedException;
import com.MeetingCRM.Model.Meeting;
import com.MeetingCRM.Repository.MeetingManagementRepository;
import com.MeetingCRM.Service.MeetingServiceImplements;
import com.MeetingCRM.payload.UserDTO;

@ExtendWith(MockitoExtension.class)
public class MeetingCRMTests {
	@Mock
	MeetingManagementRepository meetingRepo;
	
	@Mock
	RestTemplate restTemplate;
	
	@InjectMocks
	MeetingServiceImplements meetingService;
	
	Meeting testMeeting;
	
	@BeforeEach
	void setUp() {
		testMeeting = new Meeting();
		testMeeting.setUserId(10);
        testMeeting.setMeetingStartDate(LocalDate.of(2024, 2, 7));
        testMeeting.setMeetingEndDate(LocalDate.of(2024, 2, 7));
        testMeeting.setStatus("Pending");
        testMeeting.setUserStatus("Pending");
    }
	
	
//	For scheduleANewMeeting()
	@Test
	public void testScheduleMeeting() throws MeetingCreationFailedException {
		when(meetingRepo.save(any(Meeting.class))).thenReturn(testMeeting);
		
		assertNotNull(meetingService.addMeeting(testMeeting));
		verify(meetingRepo, times(1)).save(testMeeting);
	}
	
	
//	For findAllMeetings()
	@Test
	public void testFindAllMeetings() {
		List<Meeting> mockList = Arrays.asList(new Meeting(), new Meeting());
		when(meetingRepo.findAll()).thenReturn(mockList);
		
		List<Meeting> actualList = meetingService.viewAllMeetings();
		
		assertEquals(mockList, actualList);
		assertThat(actualList).hasSizeGreaterThan(0);
	}
	
	
//	For findMeetingById()
	@Test
	public void testFindMeetingById() throws MeetingNotFoundException {
	    long meetingId = 123;
        UserDTO expectedUser = new UserDTO();
        expectedUser.setUserId(10);
        when(meetingRepo.findById(meetingId)).thenReturn(Optional.of(testMeeting));
        when(restTemplate.getForObject(eq("http://localhost:8080/api/users/getUser/" + testMeeting.getUserId()), eq(UserDTO.class)))
                .thenReturn(expectedUser);

        Meeting actualMeeting = meetingService.viewMeetingById(meetingId);

        assertEquals(testMeeting, actualMeeting);
        assertEquals(expectedUser, actualMeeting.getUser());
	}
	
	
	@Test
	public void testUpdateMeeting() throws MeetingStatusUpdateFailedException, MeetingNotFoundException {
        int UserId = 123;
        long meetingId = 123L;
        
        Meeting updatedMeeting = new Meeting();
        updatedMeeting.setMeetingId(meetingId);
	    updatedMeeting.setMeetingStartDate(LocalDate.of(2024, 2, 20));
	    updatedMeeting.setMeetingEndDate(LocalDate.of(2024, 2, 20));
	    updatedMeeting.setUserId(UserId);
	    updatedMeeting.setStatus("Completed");
	    updatedMeeting.setUserStatus("Pending");

        when(meetingRepo.findById(meetingId)).thenReturn(Optional.of(testMeeting));

        meetingService.updateDetails(meetingId, updatedMeeting);

        
      assertEquals(updatedMeeting.getMeetingStartDate(), testMeeting.getMeetingStartDate());
      assertEquals(updatedMeeting.getMeetingEndDate(), testMeeting.getMeetingEndDate());
      assertEquals(updatedMeeting.getStatus(), testMeeting.getStatus());
      assertEquals(updatedMeeting.getUserId(), testMeeting.getUserId());
      assertEquals(updatedMeeting.getUserStatus(), testMeeting.getUserStatus());
//        verify(testMeeting).setMeetingStartDate(updatedMeeting.getMeetingStartDate());
//        verify(testMeeting).setMeetingEndDate(updatedMeeting.getMeetingEndDate());
//        verify(testMeeting).setStatus(updatedMeeting.getStatus());
//        verify(testMeeting).setUserId(updatedMeeting.getUserId());
//        verify(testMeeting).setUserStatus(updatedMeeting.getUserStatus());

        verify(meetingRepo).save(testMeeting);
	}

	
//	For deleteMeeting()
	@Test
	public void testDeleteMeeting() throws MeetingNotFoundException {
		when(meetingRepo.findById(testMeeting.getMeetingId())).thenReturn(Optional.of(testMeeting));

        meetingService.deleteMeeting(testMeeting.getMeetingId());

        verify(meetingRepo, times(1)).deleteById(testMeeting.getMeetingId());
	}
	
}