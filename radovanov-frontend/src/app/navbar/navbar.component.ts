import { Component, OnInit } from '@angular/core';
import { UserService } from '../user/user.service';
import { LoginService } from '../login/login.service';
import { User } from '../user/user.model';
import { Router } from '@angular/router';
import {AuditService} from '../audit/audit.service';
import {DatePipe} from '@angular/common';
import {TranslateService} from '@ngx-translate/core';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {

    isNavbarCollapsed: boolean;
    lastLoginDateTime: string | Date;
    selectedLanguage = 'hr';
    flag = 'https://flagcdn.com/w40/hr.png';

  constructor(
    private loginService: LoginService,
    private auditService: AuditService,
    public userService: UserService,
    private router: Router,
    private datePipe: DatePipe,
    private translate: TranslateService) {
  }

  ngOnInit(): void {
    this.userService.getCurrentUser().subscribe((currentUser: User) => {
      this.userService.currentUser = currentUser;
      this.isNavbarCollapsed = true;

      if (currentUser) {
        this.auditService.getLastLoginByUser(currentUser.id).subscribe(login => {
            this.lastLoginDateTime = this.datePipe.transform(login.dateTimeLogin, 'dd.MM.yyyy. HH:mm');
        });
      }
    });
  }

    switchLang(lang: string) {
        this.translate.use(lang);
        this.selectedLanguage = lang;

        switch (lang) {
            case 'hr':
                this.flag = 'https://flagcdn.com/w40/hr.png';
                break;
            case 'en':
                this.flag = 'https://flagcdn.com/w40/us.png';
                break;
        }
    }

  toggleNavbar() {
    this.isNavbarCollapsed = !this.isNavbarCollapsed;
  }

  logout() {
    this.loginService.logoutServer().subscribe(() => {
        this.loginService.logout();
        this.userService.currentUser = null;
        this.router.navigate(['/login']);
      });
  }

  isUserLoggedIn(): boolean {
    return !!this.userService.currentUser;
  }
}
