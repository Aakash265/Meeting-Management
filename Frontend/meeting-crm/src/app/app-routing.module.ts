import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AddMeetingComponent } from './add-meeting/add-meeting.component';
import { UpdateMeetingComponent } from './update-meeting/update-meeting.component';
import { ViewMeetingComponent } from './view-meeting/view-meeting.component';
import { HomeComponent } from './home/home.component';
import { MeetingByUserComponent } from './meeting-by-user/meeting-by-user.component';
import { LoginFormComponent } from './login-form/login-form.component';

const routes: Routes = [
  {
    path: 'viewMeetings',
    component: ViewMeetingComponent
  },
  {
    path: '',
    redirectTo: 'home',
    pathMatch: 'full'
  },
  {
    path: 'addMeeting',
    component: AddMeetingComponent
  },
  {
    path: 'updateMeeting',
    component: UpdateMeetingComponent
  },
  {
    path: 'home',
    component: HomeComponent
  },
  {
    path: 'byUser/:id',
    component: MeetingByUserComponent
  },
  {
    path: 'login',
    component: LoginFormComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
