import {Component, OnInit} from '@angular/core';
import {UserService} from '../user/user.service';

@Component({
  selector: 'app-main',
  templateUrl: './main.component.html',
  styleUrls: ['./main.component.css']
})
export class MainComponent implements OnInit {

  title = 'Student App';

  constructor(public userService: UserService) { }

  ngOnInit(): void {
  }

}
