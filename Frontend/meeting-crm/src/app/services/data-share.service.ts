import { Injectable } from '@angular/core';
import { Meeting } from '../model/meeting';

@Injectable({
  providedIn: 'root'
})
export class DataShareService {

  constructor() { }

  private shareData: Meeting;
  private meetingList: Meeting[];


  setData(data: Meeting) {
    this.shareData = data;
  }
  getData() {
    return this.shareData;
  }

  setFilteredMeetings(meet: Meeting[]) {
    this.meetingList = meet;
  }
  getFilteredMeetings() {
    return this.meetingList;
  }

  setMeetingList(meet: Meeting[]) {
    this.meetingList = meet;
  }
  getMeetingList() {
    return this.meetingList;
  }
}
