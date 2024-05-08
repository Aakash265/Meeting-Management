import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Meeting } from '../model/meeting';
import { DataShareService } from '../services/data-share.service';
import { MeetingService } from '../services/meeting.service';

@Component({
  selector: 'app-update-meeting',
  templateUrl: './update-meeting.component.html',
  styleUrl: './update-meeting.component.css'
})
export class UpdateMeetingComponent implements OnInit {

  meeting: Meeting = new Meeting();
  errorMessage: any;

  constructor(
    private meetingService: MeetingService,
    private router: Router,
    private dataShare: DataShareService
  ) { };

  ngOnInit(): void {
    this.meeting = this.dataShare.getData();
  }
  
  goToMeetingList(): void {
    this.router.navigate(["/viewMeetings"]);
  }
  
  onSubmit() {
    this.meetingService.updateMeeting(this.meeting.meetingId, this.meeting).subscribe({
      next: value => console.log(value),
      error: error => this.errorMessage = error.message,
      complete: () => {
          this.goToMeetingList();
      },
    })
  }

}
