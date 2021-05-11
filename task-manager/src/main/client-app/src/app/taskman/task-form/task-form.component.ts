import { Component, OnInit } from '@angular/core';
import { Task } from '../models/task.model';
import { TaskService } from '../services/task.service';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
	selector: 'app-task-form',
	templateUrl: './task-form.component.html',
	styleUrls: ['./task-form.component.css']
})
export class TaskFormComponent implements OnInit {

	task: Task;

	constructor(
		private taskService: TaskService,
		private route: ActivatedRoute,
		private router: Router) 
		{ }

	ngOnInit(): void {
		var id = "";
		this.route.queryParams
			.subscribe(params => {
				console.log(params);
				id = params.id;
				console.log("Task id is: " + id);
				this.taskService.findById(id).subscribe(data => {
					this.task = data;
				})
			});
	}
	
  onSubmit() {
	this.taskService.update(this.task).subscribe(result => this.navToTaskList());
  }
  
  navToTaskList() {
    this.router.navigate(['/tasks']);
  }  	
	
}
