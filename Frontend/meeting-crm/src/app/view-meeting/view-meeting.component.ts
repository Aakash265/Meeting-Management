import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Meeting } from '../model/meeting';
import { DataShareService } from '../services/data-share.service';
import { MeetingService } from '../services/meeting.service';

@Component({
  selector: 'app-view-meeting',
  templateUrl: './view-meeting.component.html',
  styleUrl: './view-meeting.component.css'
  // providers: [MeetingService]      // Hierarchical dependeny injection
})
export class ViewMeetingComponent implements OnInit {
  meetings: Meeting[];
  disable: boolean;
  errorMessage: string;

  constructor(
    private meetingService: MeetingService, 
    private router: Router, 
    private dataShare: DataShareService
  ) { }

  ngOnInit(): void {
    this.getAllMeetings();
    this.dataShare.setMeetingList(this.meetings);
  }

  private getAllMeetings() {
    this.meetingService.getMeetings().subscribe({
      next: value => this.meetings = value,
      error: error => this.errorMessage = error.message,
      complete: () => console.log("Data collected.")
    });
  }

  updateMeeting(meet: Meeting) {
    this.dataShare.setData(meet);
    this.router.navigate(['updateMeeting']);
  }

  deleteMeeting(id: number) {
    this.meetingService.deleteMeeting(id).subscribe({
      next: value => console.log(value),
      error: error => this.errorMessage = error.message,
      complete: () => {
        this.getAllMeetings();
        alert("Meeting deleted successfully.");
      }
    });
  }

  disableIfCompleted(status: string) {
    this.disable = false;
    if (status === "Completed") {
      this.disable = true;
    }
    return this.disable;
  }
}