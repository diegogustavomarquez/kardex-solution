import { Component, OnInit } from '@angular/core';
import { CommonFormComponent } from '../commons-form.component';
import { Product } from 'src/app/models/product';
import { ProductService } from 'src/app/services/product.service';
import { Router, ActivatedRoute } from '@angular/router';
import { CategoryService } from 'src/app/services/category.service';
import { Category } from 'src/app/models/category';
import Swal from 'sweetalert2'
import { logging } from 'protractor';

@Component({
  selector: 'app-product-form',
  templateUrl: './product-form.component.html',
  styleUrls: ['./product-form.component.css']
})
export class ProductFormComponent extends CommonFormComponent<Product, ProductService> implements OnInit {

  public listOfCategory: Category[];
  public modelCategory : number;
  public count: number;

  constructor(service: ProductService, private serviceCategory: CategoryService,
    router: Router,
    route: ActivatedRoute) { 
      super(service, router, route);
      this.titulo = 'Crear Producto';
      this.model = new Product();
      this.redirect = '/product';
      this.nombreModel = 'Producto';
      
    }

    ngOnInit() {

      this.serviceCategory.list().subscribe(p => 
        {
          this.listOfCategory = p as Category[];
        });

      this.route.paramMap.subscribe(params => {
        const id: number = +params.get('id');
        if(id){
          this.service.view(id).subscribe(m => {
            this.model = m;
            this.modelCategory = m.category.id;
            this.titulo = 'Editar ' + this.nombreModel;
          });
        }
      });

    }

    public create(): void {
      if(this.count != null && !Number.isInteger(this.count)){
        Swal.fire('Error:', 'Solo se permite números enteros', 'error');
        return;
      }
      if(this.count != null && this.count < 0){
        Swal.fire('Error:', 'Solo se permite números enteros mayores o iguales a cero', 'error');
        return;
      }      
      this.model.category = this.listOfCategory.filter(p => p.id == this.modelCategory)[0];
      this.service.create(this.model).subscribe(m => {
        console.log(m);
        Swal.fire('Nuevo:', `${this.nombreModel} ${m.name} creado con éxito`, 'success');
        this.router.navigate([this.redirect]);
      },  err => {
        if(err.status === 400){
          this.error = err.error;
          console.log(this.error);
        }
      });
    }
  
    public update(): void {
      this.model.category = this.listOfCategory.filter(p => p.id == this.modelCategory)[0];
      this.service.update(this.model).subscribe(m => {
        console.log(m);
        Swal.fire('Modificado:', `${this.nombreModel} ${m.name} actualizado con éxito`, 'success');
        this.router.navigate([this.redirect]);
      }, err => {
        if(err.status === 400){
          this.error = err.error;
          console.log(this.error);
        }
      });
    }

}
