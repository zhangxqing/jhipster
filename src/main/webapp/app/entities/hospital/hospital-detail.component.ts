import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { JhiDataUtils } from 'ng-jhipster';

import { IHospital } from 'app/shared/model/hospital.model';

@Component({
    selector: 'jhi-hospital-detail',
    templateUrl: './hospital-detail.component.html'
})
export class HospitalDetailComponent implements OnInit {
    hospital: IHospital;

    constructor(private dataUtils: JhiDataUtils, private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ hospital }) => {
            this.hospital = hospital;
        });
    }

    byteSize(field) {
        return this.dataUtils.byteSize(field);
    }

    openFile(contentType, field) {
        return this.dataUtils.openFile(contentType, field);
    }
    previousState() {
        window.history.back();
    }
}
