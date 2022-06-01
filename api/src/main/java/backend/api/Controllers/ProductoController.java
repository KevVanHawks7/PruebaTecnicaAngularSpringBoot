package backend.api.Controllers;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.http.HttpHeaders;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.annotation.Resource;
import javax.servlet.ServletContext;

import org.apache.tomcat.jni.File;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.hibernate.engine.jdbc.StreamUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

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
