/* tslint:disable max-line-length */
import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { of } from 'rxjs';
import { take, map } from 'rxjs/operators';
import { HospitalService } from 'app/entities/hospital/hospital.service';
import { IHospital, Hospital, HospitalType, HospitalLevel } from 'app/shared/model/hospital.model';

describe('Service Tests', () => {
    describe('Hospital Service', () => {
        let injector: TestBed;
        let service: HospitalService;
        let httpMock: HttpTestingController;
        let elemDefault: IHospital;
        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [HttpClientTestingModule]
            });
            injector = getTestBed();
            service = injector.get(HospitalService);
            httpMock = injector.get(HttpTestingController);

            elemDefault = new Hospital(
                0,
                'AAAAAAA',
                HospitalType.COMPOSITE,
                HospitalLevel.FIRST_RATE_ONE,
                'AAAAAAA',
                'AAAAAAA',
                'AAAAAAA',
                'AAAAAAA'
            );
        });

        describe('Service methods', async () => {
            it('should find an element', async () => {
                const returnedFromService = Object.assign({}, elemDefault);
                service
                    .find(123)
                    .pipe(take(1))
                    .subscribe(resp => expect(resp).toMatchObject({ body: elemDefault }));

                const req = httpMock.expectOne({ method: 'GET' });
                req.flush(JSON.stringify(returnedFromService));
            });

            it('should create a Hospital', async () => {
                const returnedFromService = Object.assign(
                    {
                        id: 0
                    },
                    elemDefault
                );
                const expected = Object.assign({}, returnedFromService);
                service
                    .create(new Hospital(null))
                    .pipe(take(1))
                    .subscribe(resp => expect(resp).toMatchObject({ body: expected }));
                const req = httpMock.expectOne({ method: 'POST' });
                req.flush(JSON.stringify(returnedFromService));
            });

            it('should update a Hospital', async () => {
                const returnedFromService = Object.assign(
                    {
                        name: 'BBBBBB',
                        hospitalType: 'BBBBBB',
                        hospitalLevel: 'BBBBBB',
                        address: 'BBBBBB',
                        phone: 'BBBBBB',
                        imageUrl: 'BBBBBB',
                        intro: 'BBBBBB'
                    },
                    elemDefault
                );

                const expected = Object.assign({}, returnedFromService);
                service
                    .update(expected)
                    .pipe(take(1))
                    .subscribe(resp => expect(resp).toMatchObject({ body: expected }));
                const req = httpMock.expectOne({ method: 'PUT' });
                req.flush(JSON.stringify(returnedFromService));
            });

            it('should return a list of Hospital', async () => {
                const returnedFromService = Object.assign(
                    {
                        name: 'BBBBBB',
                        hospitalType: 'BBBBBB',
                        hospitalLevel: 'BBBBBB',
                        address: 'BBBBBB',
                        phone: 'BBBBBB',
                        imageUrl: 'BBBBBB',
                        intro: 'BBBBBB'
                    },
                    elemDefault
                );
                const expected = Object.assign({}, returnedFromService);
                service
                    .query(expected)
                    .pipe(take(1), map(resp => resp.body))
                    .subscribe(body => expect(body).toContainEqual(expected));
                const req = httpMock.expectOne({ method: 'GET' });
                req.flush(JSON.stringify([returnedFromService]));
                httpMock.verify();
            });

            it('should delete a Hospital', async () => {
                const rxPromise = service.delete(123).subscribe(resp => expect(resp.ok));

                const req = httpMock.expectOne({ method: 'DELETE' });
                req.flush({ status: 200 });
            });
        });

        afterEach(() => {
            httpMock.verify();
        });
    });
});
