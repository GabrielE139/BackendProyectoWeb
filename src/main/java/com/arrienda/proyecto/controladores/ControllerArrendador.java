package com.arrienda.proyecto.controladores;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.arrienda.proyecto.dtos.*;
import com.arrienda.proyecto.servicios.ServicioArrendador;
import jakarta.persistence.EntityNotFoundException;

@RestController
@RequestMapping("/arrendador")
public class ControllerArrendador {

    @Autowired
    private ServicioArrendador servicioArrendador;

    @GetMapping("/arrendadores")
    public List<DTOArrendador> getAllArrendadores() {
        return servicioArrendador.traerArrendadores();
    }

    @GetMapping("/arrendador/{id}")
    public DTOArrendador getArrendador(@PathVariable Long id) {
        return servicioArrendador.traerArrendador(id);
    }

    @PostMapping("/crearArrendador")
    public DTOArrendador crearArrendador(@RequestBody DTOArrendador arrendador) {
        return servicioArrendador.crearArrendador(arrendador);
    }

    @PutMapping("/actualizarArrendador/{id}")
    public DTOArrendador actualizarArrendador(@PathVariable Long id, @RequestBody DTOArrendador arrendador) {
        return servicioArrendador.actualizarArrendador(id, arrendador);
    }

    @DeleteMapping("/eliminarArrendador/{id}")
    public ResponseEntity<String> eliminarArrendador(@PathVariable Long id) {
        try {
            servicioArrendador.eliminarArrendador(id);
            return ResponseEntity.ok("Arrendador eliminado con éxito.");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Arrendador no encontrado.");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al eliminar el arrendador.");
        }
    }
}