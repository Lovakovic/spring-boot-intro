import {Injectable} from '@angular/core';
import {UserCredentials} from './user-credentials.model';
import {HttpClient} from '@angular/common/http';
import {SERVER_API_URL} from '../constants/app.constants';
import {Observable} from 'rxjs';
import {JwtToken} from './jwt-token.model';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  constructor(
    private http: HttpClient
  ) { }

  authenticate(userCredentials: UserCredentials): Observable<JwtToken> {
    return this.http.post<JwtToken>(`${SERVER_API_URL}/api/auth/authenticate`, userCredentials);
  }

    logoutServer(): Observable<void> {
        return this.http.post<void>(`${SERVER_API_URL}/api/auth/logout`, {});
    }

  logout(): void {
    localStorage.removeItem('token');
  }
}
