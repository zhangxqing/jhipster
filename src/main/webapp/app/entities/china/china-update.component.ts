import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { IChina } from 'app/shared/model/china.model';
import { ChinaService } from './china.service';

@Component({
    selector: 'jhi-china-update',
    templateUrl: './china-update.component.html'
})
export class ChinaUpdateComponent implements OnInit {
    china: IChina;
    isSaving: boolean;

    constructor(private chinaService: ChinaService, private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ china }) => {
            this.china = china;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.china.id !== undefined) {
            this.subscribeToSaveResponse(this.chinaService.update(this.china));
        } else {
            this.subscribeToSaveResponse(this.chinaService.create(this.china));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IChina>>) {
        result.subscribe((res: HttpResponse<IChina>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }
}
