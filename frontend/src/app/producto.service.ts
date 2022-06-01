import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Producto } from './producto';

@Injectable({
  providedIn: 'root'
})
export class ProductoService {

  private baseURL = "http://localhost:8080/api/productos"
  constructor(private httpClient: HttpClient) { }

  obtenerListaDeProductos():Observable<Producto[]>{
    return this.httpClient.get<Producto[]>(`${this.baseURL}`);
  }

  agregarProducto(producto: Producto):Observable<Object>{
    return this.httpClient.post(`${this.baseURL}`, producto);
  }


  actualizarProducto(id: number, producto: Producto): Observable<Object>{
    console.table(producto);
    return this.httpClient.put(`${this.baseURL}/${id}`, producto);
  }


  obtenerProductoPorId(id: number):Observable<Producto>{
    return this.httpClient.get<Producto>(`${this.baseURL}/${id}`);
  }

  eliminarProducto(id:number):Observable<Object>{
    return this.httpClient.delete(`${this.baseURL}/${id}`);
  }
}
