import {Component, OnInit} from '@angular/core';
import {Login} from './login.model';
import {AuditService} from './audit.service';
import {DatePipe} from '@angular/common';

@Component({
    selector: 'app-audit',
    templateUrl: './audit.component.html',
    styleUrls: ['./audit.component.css']
})
export class AuditComponent implements OnInit {
    logins: Login[];
    sort: {column: string, desc: boolean} = {column: 'dateTimeLogin', desc: true};

    constructor(private auditService: AuditService,
                public datePipe: DatePipe) { }

    ngOnInit() {
        this.getLoginList();
    }

    private getLoginList() {
        this.auditService.getLogins().subscribe(data => {
            this.logins = data;
            this.sortData();
        });
    }

    sortData(column?: string) {
        if (column) {
            this.sort.desc = (this.sort.column === column) ? !this.sort.desc : false;
            this.sort.column = column;
        }

        this.logins.sort((a, b) => {
            if (a[this.sort.column] < b[this.sort.column]) { return this.sort.desc ? 1 : -1; }
            if (a[this.sort.column] > b[this.sort.column]) { return this.sort.desc ? -1 : 1; }
            return 0;
        });
    }
}
