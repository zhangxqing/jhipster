import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IChina } from 'app/shared/model/china.model';
import { ChinaService } from './china.service';

@Component({
    selector: 'jhi-china-delete-dialog',
    templateUrl: './china-delete-dialog.component.html'
})
export class ChinaDeleteDialogComponent {
    china: IChina;

    constructor(private chinaService: ChinaService, public activeModal: NgbActiveModal, private eventManager: JhiEventManager) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.chinaService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'chinaListModification',
                content: 'Deleted an china'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-china-delete-popup',
    template: ''
})
export class ChinaDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ china }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(ChinaDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
                this.ngbModalRef.componentInstance.china = china;
                this.ngbModalRef.result.then(
                    result => {
                        this.router.navigate([{ outlets: { popup: null } }], { replaceUrl: true, queryParamsHandling: 'merge' });
                        this.ngbModalRef = null;
                    },
                    reason => {
                        this.router.navigate([{ outlets: { popup: null } }], { replaceUrl: true, queryParamsHandling: 'merge' });
                        this.ngbModalRef = null;
                    }
                );
            }, 0);
        });
    }

    ngOnDestroy() {
        this.ngbModalRef = null;
    }
}
