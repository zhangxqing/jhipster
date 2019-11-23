/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { JhipsterTestModule } from '../../../test.module';
import { ChinaUpdateComponent } from 'app/entities/china/china-update.component';
import { ChinaService } from 'app/entities/china/china.service';
import { China } from 'app/shared/model/china.model';

describe('Component Tests', () => {
    describe('China Management Update Component', () => {
        let comp: ChinaUpdateComponent;
        let fixture: ComponentFixture<ChinaUpdateComponent>;
        let service: ChinaService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [JhipsterTestModule],
                declarations: [ChinaUpdateComponent]
            })
                .overrideTemplate(ChinaUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(ChinaUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ChinaService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new China(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.china = entity;
                    // WHEN
                    comp.save();
                    tick(); // simulate async

                    // THEN
                    expect(service.update).toHaveBeenCalledWith(entity);
                    expect(comp.isSaving).toEqual(false);
                })
            );

            it(
                'Should call create service on save for new entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new China();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.china = entity;
                    // WHEN
                    comp.save();
                    tick(); // simulate async

                    // THEN
                    expect(service.create).toHaveBeenCalledWith(entity);
                    expect(comp.isSaving).toEqual(false);
                })
            );
        });
    });
});
