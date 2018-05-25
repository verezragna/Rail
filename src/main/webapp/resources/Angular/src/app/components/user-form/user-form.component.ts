import { Component, OnInit } from '@angular/core';
import { UserService } from '../../shared-service/user.service';
import { HttpModule } from '@angular/http';

@Component({
  selector: 'app-user-form',
  templateUrl: './user-form.component.html',
  styleUrls: ['./user-form.component.css']
})
export class UserFormComponent implements OnInit {
  constructor(private userService:UserService, private http:HttpModule) { }

  ngOnInit() {
    this.userService.login().subscribe((users)=>{
      console.log(users);
    },(error)=>{
      console.log(error);
    });
  }

}
