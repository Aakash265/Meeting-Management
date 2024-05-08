package com.MeetingCRM.Model; 

import java.time.LocalDate;

import com.MeetingCRM.payload.UserDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
 
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="meeting")
public class Meeting {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long meetingId;
	
	@Column(nullable = false)
	private int userId;

	@ManyToOne
	@JoinColumn(name="userid", referencedColumnName = "userid")
	@Transient
	private UserDTO user;
	@Column(nullable = false)
	private LocalDate meetingStartDate;
	@Column(nullable = false)
	private LocalDate meetingEndDate;
	@Column(nullable = false)
	private String status;
	
	@Column
	private String userStatus;
	
}
