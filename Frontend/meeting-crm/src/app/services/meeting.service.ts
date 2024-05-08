import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { catchError, Observable, throwError } from 'rxjs';
import { Meeting } from '../model/meeting';

@Injectable({
  providedIn: 'root'
})
export class MeetingService {

  private baseUrl = "http://localhost:8090/api/meetings";

  constructor(private httpClient: HttpClient) { }

  getMeetings(): Observable<Meeting[]> {
    return this.httpClient.get<Meeting[]>(`${this.baseUrl}/view`).pipe(
      catchError(this.handleError)
    );
  }

  addMeeting(meeting: Meeting): Observable<Object> {
    return this.httpClient.post(`${this.baseUrl}/schedule`, meeting).pipe(
      catchError(this.handleError)
    );
  }

  getMeetingById(id: number): Observable<Meeting> {
    return this.httpClient.get<Meeting>(`${this.baseUrl}/view/${id}`).pipe(
      catchError(this.handleError)
    );
  }

  updateMeeting(id: number, meeting: Meeting): Observable<Object> {
    console.log("Inside updateMeeting", meeting);
    
    return this.httpClient.put(`${this.baseUrl}/update/${id}`, meeting);
  }

  deleteMeeting(id: number): Observable<Object> {
    return this.httpClient.delete(`${this.baseUrl}/delete/${id}`).pipe(
      catchError(this.handleError)
    );
  }


  // Error Handelling
  private handleError(error: HttpErrorResponse) {
    let errorMessage = '';
    if (error.status === 0) {
      console.log("An error occured: ", error.error);
    } else {
      console.error(`Backend returned CODE: ${error.status}, MESSAGE: ${error.error}`);
      errorMessage = `Backend returned CODE: ${error.status}, MESSAGE: ${error.error}`;
    }

    return throwError(() => new Error(errorMessage));
  }

}
