import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BookMeComponent } from './book-me.component';

describe('BookMeComponent', () => {
  let component: BookMeComponent;
  let fixture: ComponentFixture<BookMeComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ BookMeComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(BookMeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
