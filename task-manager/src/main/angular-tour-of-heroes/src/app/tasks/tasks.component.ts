import { Component, OnInit } from '@angular/core';
import { TaskService } from '../service/task.service';
import { Task } from '../model/task';
import { Router } from '@angular/router';

@Component({
  selector: 'app-tasks',
  templateUrl: './tasks.component.html',
  styleUrls: ['./tasks.component.css']
})
export class TasksComponent implements OnInit {

  tasks: Task[] = [];

  constructor(private taskService: TaskService,
    private router: Router
    ) { }

  getTasks(): void {
    this.taskService.getTasks()
      .subscribe(tasks => this.tasks = tasks);
  }

  ngOnInit(): void {
    this.getTasks();
  }

  getTasksByStatus(status: string): void {
    this.taskService.getTasksByStatus(status)
      .subscribe(tasks => this.tasks = tasks);
  }

  createTask(): void {
    this.router.navigateByUrl("tasks/create");
  }

}
