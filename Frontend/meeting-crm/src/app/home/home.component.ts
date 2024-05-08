import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrl: './home.component.css'
})
export class HomeComponent {

  constructor(private router: Router) { }

  view() {
    this.router.navigate(['viewMeetings']);
  }

  add() {
    this.router.navigate(['addMeeting']);
  }

  loginAsAdmin() {
    this.router.navigate(['viewMeetings']);
  }

  loginAsUser() {
    this.router.navigate(['login']);
  }

  about() {
    this.router.navigate(['viewMeetings']);
  }
}
