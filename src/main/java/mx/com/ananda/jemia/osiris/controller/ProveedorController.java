package mx.com.ananda.jemia.osiris.controller;

import mx.com.ananda.jemia.osiris.service.interfaces.IProveedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/ananda/osisirs/proveedores")
public class ProveedorController {

    @Autowired
    private IProveedorService sProveedor;

    @GetMapping
    public ResponseEntity<?> traerProveedores(){
        return new ResponseEntity<>(sProveedor.findAllProveedores(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> traerProveedorById(@PathVariable Long id){
        return new ResponseEntity<>(sProveedor.findProveedorById(id),HttpStatus.OK);
    }

    @GetMapping("/cardCode")
    public ResponseEntity<?> traerProveedorByCardCode(@RequestParam String cardCode){
        return new ResponseEntity<>(sProveedor.findProveedorByCardCode(cardCode),HttpStatus.OK);
    }

    @GetMapping("/ordenes/{id}")
    public ResponseEntity<?> traerOrdenesByIdProveedor(@RequestParam Long id){
        return new ResponseEntity<>(sProveedor.findOrdenesByProveedor(id),HttpStatus.OK);
    }
}
