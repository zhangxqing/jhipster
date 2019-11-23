/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { JhipsterTestModule } from '../../../test.module';
import { ChinaDetailComponent } from 'app/entities/china/china-detail.component';
import { China } from 'app/shared/model/china.model';

describe('Component Tests', () => {
    describe('China Management Detail Component', () => {
        let comp: ChinaDetailComponent;
        let fixture: ComponentFixture<ChinaDetailComponent>;
        const route = ({ data: of({ china: new China(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [JhipsterTestModule],
                declarations: [ChinaDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(ChinaDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(ChinaDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.china).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
