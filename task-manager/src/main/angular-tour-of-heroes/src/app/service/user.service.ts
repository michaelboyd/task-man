import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { catchError, tap } from 'rxjs/operators';
import { UtilityService } from '../service/utility.service';

import { User } from '../model/user';


@Injectable({
  providedIn: 'root'
})
export class UserService {

  private resourceUrl = 'api/users';

  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  };

  constructor(
    private http: HttpClient,
    private utilityService: UtilityService
  ) { }

  getUsers(): Observable<User[]> {
    return this.http.get<User[]>(this.resourceUrl)
      .pipe(
        tap(_ => this.utilityService.log('fetched users')),
        catchError(this.utilityService.handleError<User[]>('getUsers', []))
      );
  }

  getUser(id: number): Observable<User> {
    const url = `${this.resourceUrl}/${id}`;
    return this.http.get<User>(url).pipe(
      tap(_ => this.utilityService.log(`fetched user id=${id}`)),
      catchError(this.utilityService.handleError<User>(`getUser id=${id}`))
    );
  }

  updateUser(user: User): Observable<any> {
    const url = `${this.resourceUrl}/${user.id}`;
    return this.http.put(url, user, this.httpOptions).pipe(
      tap(_ => this.utilityService.log(`updated user id=${user.id}`)),
      catchError(this.utilityService.handleError<any>('updateUser'))
    );
  } 
  
  addUser(user: User): Observable<User> {
    return this.http.post<User>(this.resourceUrl, user, this.httpOptions).pipe(
      tap((newUser: User) => this.utilityService.log(`added user w/ id=${newUser.id}`)),
      catchError(this.utilityService.handleError<User>('addUser'))
    );
  }

}
