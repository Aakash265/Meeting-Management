import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Meeting } from '../model/meeting';
import { DataShareService } from '../services/data-share.service';
import { MeetingService } from '../services/meeting.service';

@Component({
  selector: 'app-meeting-by-user',
  templateUrl: './meeting-by-user.component.html',
  styleUrl: './meeting-by-user.component.css'
})
export class MeetingByUserComponent implements OnInit {
  id: number;
  originalMeetingList: Meeting[];
  filteredList: Meeting[];

  accept: string = 'Accept';
  deny: string = 'Deny';
  buttonVisibleAccept: boolean = true;
  buttonVisibleDeny: boolean = true;

  errorMessage: any;

  constructor(private meetingService: MeetingService, private activatedRoute: ActivatedRoute, private router: Router, private dataShare: DataShareService) { }

  ngOnInit() {
    this.id = this.activatedRoute.snapshot.params['id'];
    console.log(this.id);
    this.filteredList = this.dataShare.getFilteredMeetings();
  }

  update(id: number, meet: Meeting) {
    console.log("Meet: ", meet.userStatus);
    
    this.meetingService.updateMeeting(id, meet).subscribe(data => {
      console.log(data);
    })
  }

  disable(meet: Meeting) {
    if (meet.userStatus === "Accepted" || meet.userStatus === "Declined") {
      this.accept = "Accepted";
      this.buttonVisibleDeny = false;
      return true;
    }
    return false;
  }

  meetingStatus(status: string, meet: Meeting) {
    if (status === "Accept") {
      meet.userStatus = 'Accepted';
      this.update(meet.meetingId, meet);
      this.buttonVisibleDeny = false;
      alert(`Meeting ${meet.userStatus}`)
    } else {
      meet.userStatus = "Declined";
      this.update(meet.meetingId, meet);
      this.buttonVisibleAccept = false;
      alert(`Meeting ${meet.userStatus}`)
    }
  }


  goToView() {
    this.router.navigate(['byUser', this.id]);
  }

  logout() {
    this.router.navigate(['home']);
  }

}
