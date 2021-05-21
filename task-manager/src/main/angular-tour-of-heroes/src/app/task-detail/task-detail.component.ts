import { Component, OnInit, Input } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Location } from '@angular/common';
import { TaskService } from '../service/task.service';
import { Task } from '../model/task';
import { UserService } from '../service/user.service';
import { User } from '../model/user';

@Component({
  selector: 'app-task-detail',
  templateUrl: './task-detail.component.html',
  styleUrls: ['./task-detail.component.css']
})
export class TaskDetailComponent implements OnInit {

  @Input() task?: Task;

  statuses = ['NEW', 'IN_PROGRESS', 'COMPLETE'];

  users: User[] = [];

  constructor(
    private route: ActivatedRoute,
    private location: Location,
    private taskService: TaskService,
    private userService: UserService
  ) { }

  ngOnInit(): void {
    this.getTask();
    this.getUsers();
  }

  getUsers(): void {
    this.userService.getUsers()
      .subscribe(users => this.users = users);
  }  

  getTask(): void {
    const id = Number(this.route.snapshot.paramMap.get('id'));
    this.taskService.getTask(id)
      .subscribe(task => this.task = task);
  }

  goBack(): void {
    this.location.back();
  }

  save(): void {
    if (this.task) {
      this.taskService.updateTask(this.task)
        .subscribe(() => this.goBack());
    }
  }

}
