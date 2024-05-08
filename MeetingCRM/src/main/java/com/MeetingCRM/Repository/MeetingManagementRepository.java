package com.MeetingCRM.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.MeetingCRM.Model.Meeting;

@Repository
public interface MeetingManagementRepository extends JpaRepository<Meeting, Long> {
	
}
