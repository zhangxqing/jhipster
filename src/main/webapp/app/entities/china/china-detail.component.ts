import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IChina } from 'app/shared/model/china.model';

@Component({
    selector: 'jhi-china-detail',
    templateUrl: './china-detail.component.html'
})
export class ChinaDetailComponent implements OnInit {
    china: IChina;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ china }) => {
            this.china = china;
        });
    }

    previousState() {
        window.history.back();
    }
}
