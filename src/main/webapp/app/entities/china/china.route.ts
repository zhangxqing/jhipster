import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { of } from 'rxjs';
import { map } from 'rxjs/operators';
import { China } from 'app/shared/model/china.model';
import { ChinaService } from './china.service';
import { ChinaComponent } from './china.component';
import { ChinaDetailComponent } from './china-detail.component';
import { ChinaUpdateComponent } from './china-update.component';
import { ChinaDeletePopupComponent } from './china-delete-dialog.component';
import { IChina } from 'app/shared/model/china.model';

@Injectable({ providedIn: 'root' })
export class ChinaResolve implements Resolve<IChina> {
    constructor(private service: ChinaService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(map((china: HttpResponse<China>) => china.body));
        }
        return of(new China());
    }
}

export const chinaRoute: Routes = [
    {
        path: 'china',
        component: ChinaComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'jhipsterApp.china.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'china/:id/view',
        component: ChinaDetailComponent,
        resolve: {
            china: ChinaResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jhipsterApp.china.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'china/new',
        component: ChinaUpdateComponent,
        resolve: {
            china: ChinaResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jhipsterApp.china.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'china/:id/edit',
        component: ChinaUpdateComponent,
        resolve: {
            china: ChinaResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jhipsterApp.china.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const chinaPopupRoute: Routes = [
    {
        path: 'china/:id/delete',
        component: ChinaDeletePopupComponent,
        resolve: {
            china: ChinaResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jhipsterApp.china.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
