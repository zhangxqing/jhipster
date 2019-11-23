import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { of } from 'rxjs';
import { map } from 'rxjs/operators';
import { Hospital } from 'app/shared/model/hospital.model';
import { HospitalService } from './hospital.service';
import { HospitalComponent } from './hospital.component';
import { HospitalDetailComponent } from './hospital-detail.component';
import { HospitalUpdateComponent } from './hospital-update.component';
import { HospitalDeletePopupComponent } from './hospital-delete-dialog.component';
import { IHospital } from 'app/shared/model/hospital.model';

@Injectable({ providedIn: 'root' })
export class HospitalResolve implements Resolve<IHospital> {
    constructor(private service: HospitalService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(map((hospital: HttpResponse<Hospital>) => hospital.body));
        }
        return of(new Hospital());
    }
}

export const hospitalRoute: Routes = [
    {
        path: 'hospital',
        component: HospitalComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'jhipsterApp.hospital.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'hospital/:id/view',
        component: HospitalDetailComponent,
        resolve: {
            hospital: HospitalResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jhipsterApp.hospital.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'hospital/new',
        component: HospitalUpdateComponent,
        resolve: {
            hospital: HospitalResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jhipsterApp.hospital.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'hospital/:id/edit',
        component: HospitalUpdateComponent,
        resolve: {
            hospital: HospitalResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jhipsterApp.hospital.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const hospitalPopupRoute: Routes = [
    {
        path: 'hospital/:id/delete',
        component: HospitalDeletePopupComponent,
        resolve: {
            hospital: HospitalResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jhipsterApp.hospital.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
