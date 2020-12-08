import { Injectable } from '@angular/core';
import { Movement } from '../models/movement';
import { CommonService } from './common.service';
import { BASE_ENDPOINT } from '../config/app';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';


@Injectable({
  providedIn: 'root'
})
export class MovementService extends CommonService<Movement> {

  protected baseEndpoint = BASE_ENDPOINT + '/movement';

  constructor(http: HttpClient) {
    super(http);
  }

  public listPage(page: string, size: string): Observable<Movement>{
    const params = new HttpParams()
    .set('page', page)
    .set('size', size)
    .set('sort', 'id,desc');
    return this.http.get<any>(`${this.baseEndpoint}/page`, {params: params});
  }
}
