import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Producto } from '../producto';
import swal from 'sweetalert2';
import { ProductoService } from '../producto.service';
import { FileUploadService } from '../services/file-upload.service';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-lista-de-productos',
  templateUrl: './lista-de-productos.component.html',
  styleUrls: ['./lista-de-productos.component.css']
})
export class ListaDeProductosComponent implements OnInit {
  selectedFiles?: FileList;

  fileInfos?: Observable<any>;
  id:number;
  searchText:any;
  producto: Producto = new Producto();
  producto2: Producto = new Producto();
  productos: Producto[];

  constructor(private productoServicio: ProductoService, private router:Router,private uploadService: FileUploadService) { }


  ngOnInit(): void {
    this.obtenerProductos();
  }

  selectFiles(event: any): void {
    this.selectedFiles = event.target.files;
  }

  private obtenerProductos(){
    this.productoServicio.obtenerListaDeProductos().subscribe(dato => {
      this.productos = dato;
    });
  }

  guardarProducto(){
    this.producto2.cantidad = this.producto.cantidad;
    this.producto2.categoria = this.producto.categoria;
    this.producto2.descripcion = this.producto.descripcion;
    this.producto2.imagen = this.selectedFiles[0].name;
    this.producto2.inventario = this.producto.inventario;
    this.producto2.nombre = this.producto.nombre;
    this.producto2.precio = this.producto.precio;
    this.productoServicio.agregarProducto(this.producto2).subscribe(dato =>{
      this.uploadFiles();
      this.irAListaDeProductos();
    }, error => console.log(error));
  }
  upload(file: File): void {
    console.log(file.name)
    console.log(file)
    if (file) {
      this.uploadService.upload(file);
    }
  }

  uploadFiles(): void {
    if (this.selectedFiles) {
        this.upload(this.selectedFiles[0]);
    }
  }

  irAListaDeProductos(){
    this.router.navigate(['productos']).then(()=>{
      window.location.reload();
    });
  }

  actualizarProducto(id: number){
    this.router.navigate(['editar', id]);
  }

  eliminarProducto(id:number){
    swal({
      title: '¿Estas seguro?',
      text: "Confirma si deseas eliminar al producto",
      type: 'warning',
      showCancelButton: true,
      confirmButtonColor: '#3085d6',
      cancelButtonColor: '#d33',
      confirmButtonText: 'Si , elimínalo',
      cancelButtonText: 'No, cancelar',
      confirmButtonClass: 'btn btn-success',
      cancelButtonClass: 'btn btn-danger',
      buttonsStyling: true
    }).then((result) => {
      if(result.value){
        this.productoServicio.eliminarProducto(id).subscribe(dato => {
          console.log(dato);
          this.obtenerProductos();
          swal(
            'Producto eliminado',
            'El Producto ha sido eliminado con exito',
            'success'
          )
        })
      }
    })
  }


  onSubmit(){
    this.guardarProducto();
  }
}
