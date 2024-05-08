import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MeetingByUserComponent } from './meeting-by-user.component';

describe('MeetingByUserComponent', () => {
  let component: MeetingByUserComponent;
  let fixture: ComponentFixture<MeetingByUserComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [MeetingByUserComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(MeetingByUserComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
