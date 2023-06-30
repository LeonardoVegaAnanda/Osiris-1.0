package mx.com.ananda.jemia.osiris.controller;

import mx.com.ananda.jemia.osiris.model.entity.CalificacionReciboModel;
import mx.com.ananda.jemia.osiris.service.interfaces.ICalificacionReciboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ananda/osiris/recibo")
public class CalificacionReciboController {

    @Autowired
    private ICalificacionReciboService sRecibo;

    @GetMapping("/{id}")
    public ResponseEntity<?> traerCalificacionRecibo(@PathVariable Long id){
        return new ResponseEntity<>(sRecibo.findByIdCalif(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> guardarCalificacionRecibo(@RequestBody CalificacionReciboModel recibo){
        return new ResponseEntity<>(sRecibo.saveCalif(recibo),HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<?> actualizarCalificacionRecibo(@RequestBody CalificacionReciboModel recibo){
        sRecibo.updateCalif(recibo);
        return new ResponseEntity<>("Se ha actualizado",HttpStatus.NO_CONTENT);
    }
}
