import { Component, OnInit } from '@angular/core';
import { User } from '../model/user';
import { UserService } from '../service/user.service';
import { Router } from '@angular/router';
import { Location } from '@angular/common';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-user-form',
  templateUrl: './user-form.component.html',
  styleUrls: ['./user-form.component.css']
})
export class UserFormComponent implements OnInit {

  model = new User();

  submitted = false;

  title = "User";

  isEdit = false;

  constructor(
    private userService: UserService,
    private router: Router,
    private location: Location,
    private route: ActivatedRoute
  ) { }

  ngOnInit(): void {
    this.newUser();
  }

  onSubmit() { 
    this.submitted = true;
    if(this.isEdit) {
      this.save();
    }
    else {
      this.add(this.model);
    }
    // this.router.navigateByUrl('users');
    this.router.navigate(['users']);
  }

  add(user: User): void {
    this.userService.addUser(user)
      .subscribe();
  }  
  
  save(): void {
      this.userService.updateUser(this.model)
        .subscribe();
  }

  newUser() {
    const id = Number(this.route.snapshot.paramMap.get('id'));
    if(id > 0) {
      this.title = "Edit User";
      this.isEdit = true;
      this.userService.getUser(id)
      .subscribe(model => this.model = model);
    }
    else {
      this.isEdit = false;
      this.title = "Create User";
      this.model = new User();
    }
  }
  
  

}
