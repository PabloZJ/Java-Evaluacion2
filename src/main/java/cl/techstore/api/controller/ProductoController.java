package cl.techstore.api.controller;

import cl.techstore.api.dto.ProductoDTO;
import cl.techstore.api.model.Producto;
import cl.techstore.api.service.ProductoService;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @GetMapping
    public ResponseEntity<List<Producto>> listar() {
        return ResponseEntity.ok(productoService.listarTodos());
    }
    
    @GetMapping("/{id}")
        public ResponseEntity<Producto> buscarPorId(@PathVariable Long id) {
            return ResponseEntity.ok(productoService.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<Producto> crear(@RequestBody ProductoDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(productoService.crear(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Producto> modificar(@PathVariable Long id, @RequestBody ProductoDTO dto) {
        return ResponseEntity.ok(productoService.modificar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        productoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
