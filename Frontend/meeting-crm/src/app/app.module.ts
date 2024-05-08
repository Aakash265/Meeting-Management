import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { AddMeetingComponent } from './add-meeting/add-meeting.component';
import { ViewMeetingComponent } from './view-meeting/view-meeting.component';
import { UpdateMeetingComponent } from './update-meeting/update-meeting.component';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { HomeComponent } from './home/home.component';
import { MeetingByUserComponent } from './meeting-by-user/meeting-by-user.component';
import { LoginFormComponent } from './login-form/login-form.component';
import { HomeNavComponent } from './utilities/home-nav/home-nav.component';
import { FooterComponent } from './utilities/footer/footer.component';
import { AdminNavComponent } from './utilities/admin-nav/admin-nav.component';

@NgModule({
  declarations: [
    AppComponent,
    AddMeetingComponent,
    ViewMeetingComponent,
    UpdateMeetingComponent,
    HomeComponent,
    MeetingByUserComponent,
    LoginFormComponent,
    HomeNavComponent,
    FooterComponent,
    AdminNavComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
