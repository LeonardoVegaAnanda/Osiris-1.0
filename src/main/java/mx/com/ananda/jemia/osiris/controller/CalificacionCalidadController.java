package mx.com.ananda.jemia.osiris.controller;


import mx.com.ananda.jemia.osiris.model.entity.CalificacionCalidadModel;
import mx.com.ananda.jemia.osiris.service.interfaces.ICalificacionCalidadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ananda/osiris/calidad")
public class CalificacionCalidadController {

    @Autowired
    private ICalificacionCalidadService sCalidad;

    @GetMapping("/{id}")
    public ResponseEntity<?> traerCalificacioncalidadById(@PathVariable Long id) {
        return new ResponseEntity<>(sCalidad.findByIdCalif(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> guardarCalificacionCalidad(@RequestBody CalificacionCalidadModel calidad) {
        return new ResponseEntity<>(sCalidad.saveCalif(calidad), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<?> actualizarCalificacionCalidad(@RequestBody CalificacionCalidadModel calidad) {
        sCalidad.updateCalif(calidad);
        return new ResponseEntity<>("Se ha actualizado",HttpStatus.NO_CONTENT);
    }
}
