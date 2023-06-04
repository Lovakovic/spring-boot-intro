import { Injectable } from '@angular/core';
import { SERVER_API_URL } from '../constants/app.constants';
import { HttpClient } from '@angular/common/http';
import { User } from './user.model';
import {Observable, throwError} from 'rxjs';
import { Authority } from '../constants/authority.constants';
import {catchError} from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  currentUser: User;

  private usersUrl = `${SERVER_API_URL}/api/user`;

  constructor(private http: HttpClient) { }

    getCurrentUser(): Observable<User> {
        return this.http.get<User>(`${this.usersUrl}/current-user`).pipe(
            catchError(err => {
                if (err.status === 403) {
                    localStorage.removeItem('token'); // remove token from localstorage
                }

                return throwError(err);
            })
        );
    }

    isRoleAdmin(): boolean {
      if (this.currentUser) {
          return this.currentUser.role === Authority.ADMIN;
      } else {
          return false;
      }
    }

    isRoleUser(): boolean {
        if (this.currentUser) {
            return this.currentUser.role === Authority.USER;
        } else {
            return false;
        }
    }
}
