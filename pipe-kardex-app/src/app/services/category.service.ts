import { Injectable } from '@angular/core';
import { Category } from '../models/category';
import { CommonService } from './common.service';
import { BASE_ENDPOINT } from '../config/app';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class CategoryService extends CommonService<Category> {

  protected baseEndpoint = BASE_ENDPOINT + '/category';

  constructor(http: HttpClient) {
    super(http);
  }
}
