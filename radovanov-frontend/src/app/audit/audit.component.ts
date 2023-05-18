import {Component, OnInit} from '@angular/core';
import {Login} from './login.model';
import {AuditService} from './audit.service';

@Component({
  selector: 'app-audit',
  templateUrl: './audit.component.html',
  styleUrls: ['./audit.component.css']
})
export class AuditComponent implements OnInit {
  logins: Login[];

  constructor(private auditService: AuditService) { }

  ngOnInit() {
    this.getLoginList();
  }

  private getLoginList() {
    this.auditService.getLogins().subscribe(data => {
      this.logins = data;
    });
  }
}
