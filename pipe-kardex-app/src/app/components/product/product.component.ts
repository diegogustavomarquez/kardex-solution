import { Component, OnInit } from '@angular/core';
import { Product } from 'src/app/models/product';
import { ProductService } from 'src/app/services/product.service';
import { CommonListarComponent } from '../common-listar.component';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-product',
  templateUrl: './product.component.html',
  styleUrls: ['./product.component.css']
})
export class ProductComponent extends CommonListarComponent<Product, ProductService> implements OnInit {

  constructor(service: ProductService) {
    super(service);
    this.titulo = 'Listado de Productos';
    this.nombreModel = Product.name;
   }

   public increase(e: Product) {
      Swal.fire({
        title: 'Agregar Productos',
        text: `¿Cuantos productos desea agregar a ${e.name} ?`,
        icon: 'question',
        input: 'number',
        showCancelButton: true,
        confirmButtonColor: '#3085d6',
        cancelButtonColor: '#d33',
        confirmButtonText: 'Si, agregar!'
      }).then((result) => {
        let num: number = Number(result.value);
        if(!Number.isInteger(num)){
          Swal.fire('Error:', 'Solo se permite números enteros mayores o iguales a 1', 'error');
          return;
        }
        if(num < 1){
          Swal.fire('Error:', 'Solo se permite números enteros mayores o iguales a 1', 'error');
          return;
        } 

        this.service.increase(e, num).subscribe(
          p => {
          this.lista = this.lista.map((e) => {
              return e.id == p.id ? p : e 
            } 
          )
          Swal.fire('Agregado:', `${this.nombreModel} ${e.name} agregado con éxito`, 'success');
        });
      
      });
  }

  public decrease(e : Product) {
    Swal.fire({
      title: 'Retirar Productos',
      text: `¿Cuantos productos desea retirar a ${e.name} ?`,
      icon: 'question',
      input: 'number',
      showCancelButton: true,
      confirmButtonColor: '#3085d6',
      cancelButtonColor: '#d33',
      confirmButtonText: 'Si, retirar!'
    }).then((result) => {

      let num: number = Number(result.value);
      if(!Number.isInteger(num)){
        Swal.fire('Error:', 'Solo se permite números enteros mayores o iguales a 1', 'error');
        return;
      }
      if(num < 1){
        Swal.fire('Error:', 'Solo se permite números enteros mayores o iguales a 1', 'error');
        return;
      }

      if(e.count - num < 0){
        Swal.fire('Error:', `${this.nombreModel} ${e.name} no se puede retirar más de ${e.count}`, 'error');
        return;
      }

      this.service.decrease(e, Number(result.value)).subscribe(
        p => {
        this.lista = this.lista.map((e) => {
            return e.id == p.id ? p : e 
          } 
        )
        Swal.fire('Retirado:', `${this.nombreModel} ${e.name} retirado con éxito`, 'success');
      }, err => {
        if(err.status === 1000){
          Swal.fire('Retirado:', `${this.nombreModel} ${e.name} no se pudo retirar porque ya no cuentas con stock`, 'error');
        }
      });
    
    });  
  }

}
