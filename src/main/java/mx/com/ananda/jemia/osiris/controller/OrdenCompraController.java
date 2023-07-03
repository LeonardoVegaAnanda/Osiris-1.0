package mx.com.ananda.jemia.osiris.controller;

import mx.com.ananda.jemia.osiris.service.interfaces.IOrdenCompraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/ananda/osiris/ordenes")
public class OrdenCompraController {

    @Autowired
    private IOrdenCompraService sOrden;

    @GetMapping()
    public ResponseEntity<?> traerOrdenes(){
        return new ResponseEntity<>(sOrden.findAllOrdenes(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> traerOrdenById(@PathVariable Long id){
        return new ResponseEntity<>(sOrden.findOrdenById(id),HttpStatus.OK);
    }

    @GetMapping("/docNum")
    public ResponseEntity<?> traerOrdenByDocNum(@RequestParam Long docNum){
        return new ResponseEntity<>(sOrden.findOrdenByDocNum(docNum),HttpStatus.OK);
    }

    @GetMapping("/eventos/{id}")
    public ResponseEntity<?> traerEventosByIdOrden(@PathVariable Long id){
        return new ResponseEntity<>(sOrden.findEventoByOrdenCompra(id),HttpStatus.OK);
    }

    @GetMapping("/asignarOrden/{id}")
    public ResponseEntity<?> asignarOrdenSAP(@PathVariable Long id, @RequestParam String docNum){
        Long docNumFormat = Long.parseLong(docNum);
        return new ResponseEntity<>(sOrden.asignarOrden(id,docNumFormat),HttpStatus.OK);
    }
}
