import { Injectable } from '@angular/core';
import { CommonService } from './common.service';
import { Product } from '../models/product';
import { BASE_ENDPOINT } from '../config/app';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ProductService extends CommonService<Product> {

  protected baseEndpoint = BASE_ENDPOINT + '/product';

  constructor(http: HttpClient) {
    super(http);
  }

  public increase(e: Product, count: number) : Observable<Product>{
    return this.http.post<Product>(`${this.baseEndpoint}/increase-by-id/${e.id}/count/${count}`, null);
  }

  public decrease(e: Product, count: number) : Observable<Product>{
    return this.http.post<Product>(`${this.baseEndpoint}/decrease-by-id/${e.id}/count/${count}`, null);
  }
}
