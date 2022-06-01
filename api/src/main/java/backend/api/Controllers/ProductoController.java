package backend.api.Controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import backend.api.Exceptions.SrcNotFoundException;

import backend.api.Models.Producto;

import backend.api.Repositories.ProductoRepo;

import backend.api.storage.StorageService;


@RestController
@RequestMapping("/api/")
@CrossOrigin(origins = "http://localhost:4200")

public class ProductoController {

    private final StorageService storageService;
    @Autowired ServletContext context;
    
    public ProductoController(StorageService storageService) {
        this.storageService = storageService;
    }
   

    @Autowired
    private ProductoRepo productoRepo;

    @GetMapping("/productos")
    public List<Producto> listarProductos(){
        return productoRepo.findAll();
        
    }
    
    @GetMapping("/productos/{id}")
    public ResponseEntity<Producto> listarProductoPorId(@PathVariable Long id){
       Producto producto = productoRepo.findById(id).orElseThrow(()-> new SrcNotFoundException("No se ha encontrado ningún producto con ID: " + id));
        return ResponseEntity.ok(producto);

    }

    @PostMapping("/productos")
    private Producto guardarProducto(@RequestBody Producto producto ){
        
        return productoRepo.save(producto);
    }
    @PostMapping("/productos/img")
	public String handleFileUpload(@RequestParam("name") MultipartFile file,
			RedirectAttributes redirectAttributes) {
        System.out.println(file);
		storageService.store(file);
		return "redirect:/";
	}


    @PutMapping("/productos/{id}")
    public ResponseEntity<Producto> actualizarProducto(@PathVariable Long id, @RequestBody Producto detallesProducto){
        Producto producto = productoRepo.findById(id).orElseThrow(()-> new SrcNotFoundException("No se ha encontrado ningún producto con ID: " + id));
        producto.setImagen(detallesProducto.getImagen());
        producto.setNombre(detallesProducto.getNombre());
        producto.setCategoria(detallesProducto.getCategoria());
        producto.setPrecio(detallesProducto.getPrecio());
        producto.setCantidad(detallesProducto.getCantidad());
        producto.setInventario(detallesProducto.getInventario());
        producto.setDescripcion(detallesProducto.getDescripcion());

        Producto productoActualizado = productoRepo.save(producto);
        return ResponseEntity.ok(productoActualizado);

    }

    @DeleteMapping("/productos/{id}")
    public ResponseEntity<Map<String, Boolean>> eliminarProducto(@PathVariable Long id){
        Producto producto = productoRepo.findById(id).orElseThrow(()-> new SrcNotFoundException("No se ha encontrado ningún producto con ID: " + id));
        productoRepo.delete(producto);
        Map<String, Boolean> respuesta = new HashMap<>();
		respuesta.put("eliminar",Boolean.TRUE);

        return ResponseEntity.ok(respuesta);
    }
}
