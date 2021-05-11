import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Task } from '../models/task.model';
import { Observable } from 'rxjs/internal/Observable';

@Injectable({
  providedIn: 'root'
})
export class TaskService {
	
	private tasksUrl: string;
	private taskUrl: string;

	constructor(private http: HttpClient) {
		this.tasksUrl = 'http://localhost:8080/api/tasks';
		this.taskUrl = "http://localhost:8080/api/task";
	}

	public findAll(): Observable<Task[]> {
		return this.http.get<Task[]>(this.tasksUrl);
	}
	
	public findByStatus(status: String): Observable<Task[]> {
		return this.http.get<Task[]>(this.tasksUrl + "/" + status);
	}
	
	public findById(id: String): Observable<Task> {
		return this.http.get<Task>(this.taskUrl + id);
	}		

	public update(task: Task) {
		return this.http.put<Task>(this.taskUrl, task);
	}
}
