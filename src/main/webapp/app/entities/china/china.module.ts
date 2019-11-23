import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { JhipsterSharedModule } from 'app/shared';
import {
    ChinaComponent,
    ChinaDetailComponent,
    ChinaUpdateComponent,
    ChinaDeletePopupComponent,
    ChinaDeleteDialogComponent,
    chinaRoute,
    chinaPopupRoute
} from './';

const ENTITY_STATES = [...chinaRoute, ...chinaPopupRoute];

@NgModule({
    imports: [JhipsterSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [ChinaComponent, ChinaDetailComponent, ChinaUpdateComponent, ChinaDeleteDialogComponent, ChinaDeletePopupComponent],
    entryComponents: [ChinaComponent, ChinaUpdateComponent, ChinaDeleteDialogComponent, ChinaDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class JhipsterChinaModule {}
