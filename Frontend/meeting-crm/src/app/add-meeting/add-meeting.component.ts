import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Meeting } from '../model/meeting';
import { MeetingService } from '../services/meeting.service';

@Component({
  selector: 'app-add-meeting',
  templateUrl: './add-meeting.component.html',
  styleUrl: './add-meeting.component.css'
})
export class AddMeetingComponent implements OnInit {

  meeting: Meeting = new Meeting();
  errorMessage: any;

  constructor(
    private meetingService: MeetingService, 
    private router: Router
  ) { }

  ngOnInit(): void {
    this.meeting.status = "Pending";
  }

  saveMeeting(): void {
    this.meetingService.addMeeting(this.meeting).subscribe({
      next: data => console.log(data),
      error: error => this.errorMessage = error.message,
      complete: () => {
        this.goToMeetingList();
        alert("Meeting scheduled successfully");
      }
    });
  }

  goToMeetingList(): void {
    this.router.navigate(['viewMeetings']);
  }

  onClick() {
    this.errorMessage = '';
  }

  onSubmit(): void {
    console.log(this.meeting);
    this.saveMeeting();
  }

}
