import { Component, OnInit } from '@angular/core';
import { CommonListarComponent } from '../common-listar.component';
import { Category } from 'src/app/models/category';
import { CategoryService } from 'src/app/services/category.service';

@Component({
  selector: 'app-category',
  templateUrl: './category.component.html',
  styleUrls: ['./category.component.css']
})
export class CategoryComponent extends CommonListarComponent<Category, CategoryService> implements OnInit {

  constructor(service: CategoryService) {
    super(service);
    this.titulo = 'Listado de Categorías';
    this.nombreModel = 'Categoría';
   }


}
