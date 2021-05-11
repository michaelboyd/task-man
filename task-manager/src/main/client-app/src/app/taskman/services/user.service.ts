import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { User } from '../models/user.model';
import { Observable } from 'rxjs/internal/Observable';

@Injectable({
	providedIn: 'root'
})
export class UserService {

	private usersUrl: string;

	constructor(private http: HttpClient) {
		this.usersUrl = 'http://localhost:8080/api/users';
	}

	public findAll(): Observable<User[]> {
		return this.http.get<User[]>(this.usersUrl);
	}

	public save(user: User) {
		return this.http.post<User>(this.usersUrl, user);
	}
}
