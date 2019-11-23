import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { JhipsterSharedModule } from 'app/shared';
import {
    HospitalComponent,
    HospitalDetailComponent,
    HospitalUpdateComponent,
    HospitalDeletePopupComponent,
    HospitalDeleteDialogComponent,
    hospitalRoute,
    hospitalPopupRoute
} from './';

const ENTITY_STATES = [...hospitalRoute, ...hospitalPopupRoute];

@NgModule({
    imports: [JhipsterSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        HospitalComponent,
        HospitalDetailComponent,
        HospitalUpdateComponent,
        HospitalDeleteDialogComponent,
        HospitalDeletePopupComponent
    ],
    entryComponents: [HospitalComponent, HospitalUpdateComponent, HospitalDeleteDialogComponent, HospitalDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class JhipsterHospitalModule {}
