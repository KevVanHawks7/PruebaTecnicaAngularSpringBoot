package backend.api.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import backend.api.Models.Producto;

@Repository
public interface ProductoRepo extends JpaRepository<Producto, Long>{
    
}
