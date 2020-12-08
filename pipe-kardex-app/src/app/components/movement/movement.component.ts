import { Component, OnInit } from '@angular/core';
import { CommonListarComponent } from '../common-listar.component';
import { Movement } from 'src/app/models/movement';
import { MovementService } from 'src/app/services/movement.service';


@Component({
  selector: 'app-movement',
  templateUrl: './movement.component.html',
  styleUrls: ['./movement.component.css']
})
export class MovementComponent extends CommonListarComponent<Movement, MovementService> implements OnInit {

  constructor(service: MovementService) {
    super(service);
    this.titulo = 'Listado de Movimientos';
    this.nombreModel = 'Movimiento';
   }

   getTipo(name: string){
    if(name == 'CREATED'){
      return "Creado";
    } else if(name == 'ADD'){
      return "Agregado";
    } else if(name == 'GET'){
      return "Retirado";
    }

  }

}
