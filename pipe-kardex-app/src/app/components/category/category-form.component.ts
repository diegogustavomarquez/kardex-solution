import { Component, OnInit } from '@angular/core';
import { Category } from 'src/app/models/category';
import { CategoryService } from 'src/app/services/category.service';
import { CommonFormComponent } from '../commons-form.component';
import { Router, ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-category-form',
  templateUrl: './category-form.component.html',
  styleUrls: ['./category-form.component.css']
})
export class CategoryFormComponent extends CommonFormComponent<Category, CategoryService> implements OnInit {

  constructor(service: CategoryService, 
    router: Router,
    route: ActivatedRoute) { 
      super(service, router, route);
      this.titulo = 'Crear Categoría';
      this.model = new Category();
      this.redirect = '/category';
      this.nombreModel = 'Categoría';
    }
}
