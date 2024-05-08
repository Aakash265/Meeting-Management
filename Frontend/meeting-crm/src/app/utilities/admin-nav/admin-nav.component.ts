import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-admin-nav',
  templateUrl: './admin-nav.component.html',
  styleUrl: './admin-nav.component.css'
})
export class AdminNavComponent {
  
  constructor(private router: Router) { }

  goToView() {
    this.router.navigate(['viewMeetings']);
  }

  goToAdd() {
    this.router.navigate(['addMeeting']);
  }

  logout() {
    this.router.navigate(['home']);
  }
}
