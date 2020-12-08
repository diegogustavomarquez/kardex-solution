import { NgModule, Component } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { MovementComponent } from './components/movement/movement.component';
import { ProductComponent } from './components/product/product.component';
import { CategoryComponent } from './components/category/category.component';
import { CategoryFormComponent } from './components/category/category-form.component';
import { ProductFormComponent } from './components/product/product-form.component';


const routes: Routes = [
  { path: '', pathMatch : 'full', redirectTo: 'product'},
  { path: 'product', component: ProductComponent},
  { path: 'product/form', component: ProductFormComponent},
  { path: 'product/form/:id', component: ProductFormComponent},
  { path: 'category', component: CategoryComponent},
  { path: 'category/form', component: CategoryFormComponent},
  { path: 'category/form/:id', component: CategoryFormComponent},
  { path: 'movement', component: MovementComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
