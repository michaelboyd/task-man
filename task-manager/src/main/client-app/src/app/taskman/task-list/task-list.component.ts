import { Component, OnInit } from '@angular/core';
import { Task } from '../models/task.model';
import { TaskService } from '../services/task.service';
import { ActivatedRoute } from '@angular/router';

@Component({
	selector: 'app-task-list',
	templateUrl: './task-list.component.html',
	styleUrls: ['./task-list.component.css']
})
export class TaskListComponent implements OnInit {

	tasks: Task[];

	constructor(
		private taskService: TaskService,
		private route: ActivatedRoute,
	) { }

	ngOnInit(): void {
		
		var status = "";

		this.route.queryParams
			.subscribe(params => {
				console.log(params);
				status = params.status;
				console.log(status);
			});

		if (status && status.length > 0) {
			this.taskService.findByStatus(status).subscribe(data => {
				this.tasks = data;
			});
		}
		else {
			this.taskService.findAll().subscribe(data => {
				this.tasks = data;
			});
		}

	}


}
