import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { catchError, tap } from 'rxjs/operators';
import { UtilityService } from '../service/utility.service';

import { Task } from '../model/task';

@Injectable({
  providedIn: 'root'
})
export class TaskService {

  private resourceUrl = 'api/tasks';

  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  };

  constructor(
    private http: HttpClient,
    private utilityService: UtilityService
  ) { }

  getTasks(): Observable<Task[]> {
    return this.http.get<Task[]>(this.resourceUrl)
      .pipe(
        tap(_ => this.utilityService.log('fetched tasks')),
        catchError(this.utilityService.handleError<Task[]>('getTasks', []))
      );
  }

  getTask(id: number): Observable<Task> {
    const url = `${this.resourceUrl}/${id}`;
    return this.http.get<Task>(url).pipe(
      tap(_ => this.utilityService.log(`fetched task id=${id}`)),
      catchError(this.utilityService.handleError<Task>(`getTask id=${id}`))
    );
  }

  updateTask(task: Task): Observable<any> {
    const url = `${this.resourceUrl}/${task.id}`;
    return this.http.put(url, task, this.httpOptions).pipe(
      tap(_ => this.utilityService.log(`updated task id=${task.id}`)),
      catchError(this.utilityService.handleError<any>('updateTask'))
    );
  }

  getTasksByStatus(status: string): Observable<Task[]> {
    if (!status.trim()) {
      return of([]);
    }
    return this.http.get<Task[]>(`${this.resourceUrl}/?status=${status}`).pipe(
      tap(x => x.length ?
        this.utilityService.log(`found tasks matching "${status}"`) :
        this.utilityService.log(`no tasks matching "${status}"`)),
      catchError(this.utilityService.handleError<Task[]>('getTasksByStatus', []))
    );
  }
}
