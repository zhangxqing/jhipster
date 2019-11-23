import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IChina } from 'app/shared/model/china.model';

type EntityResponseType = HttpResponse<IChina>;
type EntityArrayResponseType = HttpResponse<IChina[]>;

@Injectable({ providedIn: 'root' })
export class ChinaService {
    public resourceUrl = SERVER_API_URL + 'api/chinas';

    constructor(private http: HttpClient) {}

    create(china: IChina): Observable<EntityResponseType> {
        return this.http.post<IChina>(this.resourceUrl, china, { observe: 'response' });
    }

    update(china: IChina): Observable<EntityResponseType> {
        return this.http.put<IChina>(this.resourceUrl, china, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IChina>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IChina[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
