import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiAlertService, JhiDataUtils } from 'ng-jhipster';

import { IHospital } from 'app/shared/model/hospital.model';
import { HospitalService } from './hospital.service';
import { IChina } from 'app/shared/model/china.model';
import { ChinaService } from 'app/entities/china';

@Component({
    selector: 'jhi-hospital-update',
    templateUrl: './hospital-update.component.html'
})
export class HospitalUpdateComponent implements OnInit {
    hospital: IHospital;
    isSaving: boolean;

    chinas: IChina[];

    constructor(
        private dataUtils: JhiDataUtils,
        private jhiAlertService: JhiAlertService,
        private hospitalService: HospitalService,
        private chinaService: ChinaService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ hospital }) => {
            this.hospital = hospital;
        });
        this.chinaService.query().subscribe(
            (res: HttpResponse<IChina[]>) => {
                this.chinas = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    byteSize(field) {
        return this.dataUtils.byteSize(field);
    }

    openFile(contentType, field) {
        return this.dataUtils.openFile(contentType, field);
    }

    setFileData(event, entity, field, isImage) {
        this.dataUtils.setFileData(event, entity, field, isImage);
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.hospital.id !== undefined) {
            this.subscribeToSaveResponse(this.hospitalService.update(this.hospital));
        } else {
            this.subscribeToSaveResponse(this.hospitalService.create(this.hospital));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IHospital>>) {
        result.subscribe((res: HttpResponse<IHospital>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }

    trackChinaById(index: number, item: IChina) {
        return item.id;
    }
}
