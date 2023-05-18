import { Injectable } from '@angular/core';
import {Observable} from 'rxjs';
import {HttpClient} from '@angular/common/http';
import {Login} from './login.model';

@Injectable({
  providedIn: 'root'
})
export class AuditService {

  private apiUrl = 'http://localhost:8080/api/login';

  constructor(private http: HttpClient) { }

  getLogins(): Observable<Login[]> {
    return this.http.get<Login[]>(this.apiUrl);
  }

  getLastLoginByUser(userId: number): Observable<Login> {
    return this.http.get<Login>(`${this.apiUrl}/lastLogin/${userId}`);
  }
}
