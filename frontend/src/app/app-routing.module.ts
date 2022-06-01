import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { EditarProductoComponent } from './editar-producto/editar-producto.component';
import { ListaDeProductosComponent } from './lista-de-productos/lista-de-productos.component';

const routes: Routes = [
  {path: 'productos', component: ListaDeProductosComponent},
  {path: '', redirectTo: 'productos', pathMatch: 'full'},
  {path: 'editar/:id', component: EditarProductoComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
