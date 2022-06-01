import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import swal from 'sweetalert2';
import { ProductoService } from '../producto.service';
import { Producto } from '../producto';
import { Observable } from 'rxjs';
import { FileUploadService } from '../services/file-upload.service';


@Component({
  selector: 'app-editar-producto',
  templateUrl: './editar-producto.component.html',
  styleUrls: ['./editar-producto.component.css']
})
export class EditarProductoComponent implements OnInit {
  id:number;
  selectedFiles?: FileList;

  fileInfos?: Observable<any>;
  imagenes: Producto = new Producto();
  producto: Producto = new Producto();
  producto2: Producto = new Producto();
  constructor(private productoServicio: ProductoService, private router:Router, private route:ActivatedRoute, private uploadService: FileUploadService) { }

  ngOnInit(): void {
    this.id = this.route.snapshot.params['id'];
    this.productoServicio.obtenerProductoPorId(this.id).subscribe(dato => {
      this.producto = dato;
      console.log("Obtener Por ID: <- Aquí se recibe el ID " + this.id);
    }, error => console.log(error));
  }

  selectFiles(event: any): void {
    this.selectedFiles = event.target.files;
  }

  irAlaListadeProductos(){
    this.router.navigate(['/productos']);
    swal('Producto actualizado',`El producto ${this.producto.nombre} ha sido actualizado con exito`,`success` );
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

  onSubmit(){
    console.log(this.selectFiles[0]);
    console.log("On Submit: " + this.id);
    this.producto2.cantidad = this.producto.cantidad;
    this.producto2.categoria = this.producto.categoria;
    this.producto2.descripcion = this.producto.descripcion;
    this.producto2.imagen = this.selectedFiles[0].name;
    this.producto2.inventario = this.producto.inventario;
    this.producto2.nombre = this.producto.nombre;
    this.producto2.precio = this.producto.precio;

    console.table(this.producto2);
    this.productoServicio.actualizarProducto(this.id, this.producto2).subscribe(dato=> {
      this.uploadFiles();
      this.irAlaListadeProductos();
    },error => console.log(error));
  }
}
