package mx.com.ananda.jemia.osiris.controller;

import mx.com.ananda.jemia.osiris.model.entity.CalificacionComprasModel;
import mx.com.ananda.jemia.osiris.service.interfaces.ICalificacionComprasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ananda/osiris/compras")
public class CalificacionComprasController {

    @Autowired
    private ICalificacionComprasService sCompras;

    @GetMapping("/{id}")
    public ResponseEntity<?> traerCalificacionCompraById(@PathVariable Long id){
        return new ResponseEntity<>(sCompras.findByIdCalif(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> guardarCalificacionCompras(@RequestBody CalificacionComprasModel compras, @RequestParam Long id){
        return new ResponseEntity<>(sCompras.saveCalif(compras,id),HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<?> actualizarCalificacionCompras(@RequestBody CalificacionComprasModel compras){
        sCompras.updateCalif(compras);
        return new ResponseEntity<>("Se ha Actualizado",HttpStatus.NO_CONTENT);
    }

}
