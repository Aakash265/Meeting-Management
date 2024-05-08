import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Meeting } from '../model/meeting';
import { DataShareService } from '../services/data-share.service';
import { MeetingService } from '../services/meeting.service';

@Component({
  selector: 'app-login-form',
  templateUrl: './login-form.component.html',
  styleUrl: './login-form.component.css'
})
export class LoginFormComponent implements OnInit {

  errorMessage: string;
  meetings: Meeting[];
  filteredMeetings: Meeting[];
  userId: number;
  password: string;

  constructor(private router: Router, private meetingService: MeetingService, private dataShare: DataShareService) { }

  ngOnInit() { 
    this.getAllMeetings();
  }

  private getAllMeetings() {
    // this.meetingService.getMeetings().subscribe(data => {
    //   this.meetings = data
    // });
    this.meetingService.getMeetings().subscribe({
      next: value => this.meetings = value,
      error: error => this.errorMessage = error.message,
      complete: () => console.log("Task completed.")
    });
  }

  onClick() {
    this.errorMessage = '';
  }

  checkIfUserExists() {
    if (this.filteredMeetings.length == 0) {
      alert(`User Id ${this.userId} invalid. Please enter a valid User Id.`);
      this.router.navigate(['login']);
      return false;
    }
    return true;
  }

  goToUser() {
    if (this.userId != null && this.password != null) {
      console.log("Submit the form.");
      this.filteredMeetings = this.meetings.filter(e => e.userId == this.userId);
      if (this.checkIfUserExists()) {
        this.dataShare.setFilteredMeetings(this.filteredMeetings);
        this.router.navigate(['byUser', this.userId]);
      }
    } else {
      this.errorMessage = "Please enter all the details.";
    }
  }
}