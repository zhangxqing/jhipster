import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiAlertService, JhiDataUtils } from 'ng-jhipster';

import { IHospital } from 'app/shared/model/hospital.model';
import { HospitalService } from './hospital.service';
import { IChina } from 'app/shared/model/china.model';
import { ChinaService } from 'app/entities/china';
import { DomSanitizer } from '@angular/platform-browser';
import { FileUploader } from 'ng2-file-upload';

@Component({
    selector: 'jhi-hospital-update',
    templateUrl: './hospital-update.component.html',
    styleUrls: ['hospital-update.component.scss']
})
export class HospitalUpdateComponent implements OnInit {
    hospital: IHospital;
    isSaving: boolean;
    imgsrc = 'https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1574533109962&di=5252fb413653735de98102a5873b9877&imgtype' +
        '=0&src=http%3A%2F%2Fhbimg.b0.upaiyun.com%2F9817192fbf6914a38e77e0d289253e6d2f8d88b71635-VDp6dR_fw658';
    chinas: IChina[];

    public uploader: FileUploader = new FileUploader({
        url: '',
        method: 'Post',
        itemAlias: 'uploadedfile'
    });

    constructor(
        public _d: DomSanitizer,
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

    fileChange(e) {
        const file = e.srcElement.files[0]; // 获取图片这里只操作一张图片
        this.imgsrc = window.URL.createObjectURL(file); // 获取上传的图片临时路径
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
