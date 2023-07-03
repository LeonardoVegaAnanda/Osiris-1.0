package mx.com.ananda.jemia.osiris.controller;

import mx.com.ananda.jemia.osiris.model.entity.EventoModel;
import mx.com.ananda.jemia.osiris.service.interfaces.IEventoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ananda/osiris/eventos")
public class EventoController {

    @Autowired
    private IEventoService sEvento;

    @GetMapping()
    public ResponseEntity<?> traerEventos(){
        List<EventoModel> listaEventos = sEvento.findAlleventos();
        return new ResponseEntity<>(listaEventos,HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> traerEventoById(@PathVariable Long id){
        return new ResponseEntity<>(sEvento.findEventoModelById(id),HttpStatus.OK);
    }

    @GetMapping("/calidad/{id}")
    public ResponseEntity<?> traerCalifCalidadEvento(@PathVariable Long id){
        return new ResponseEntity<>(sEvento.findByEventoCalidad(id),HttpStatus.OK);
    }

    @GetMapping("/compras/{id}")
    public ResponseEntity<?> traerCalifComprasEvento(@PathVariable Long id){
        return new ResponseEntity<>(sEvento.findByEventoCompras(id),HttpStatus.OK);
    }

    @GetMapping("/recibo/{id}")
    public ResponseEntity<?> traerCalifRecivoEvento(@PathVariable Long id){
        return new ResponseEntity<>(sEvento.findByEventoRecibo(id),HttpStatus.OK);
    }
    @PostMapping("/docNum")
    public ResponseEntity<?> guardarEventoDocNum(@RequestParam Long docNum){
        return new ResponseEntity<>(sEvento.saveEventoDocNum(docNum),HttpStatus.CREATED);
    }

    @PostMapping("/cardCode")
    public ResponseEntity<?> guardarEventoCardCode(@RequestParam(value = "nota",defaultValue = "0") String nota, @RequestParam String cardCode){
        return new ResponseEntity<>(sEvento.saveEventoCardCode(cardCode,nota),HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<?> actualizarevento(@RequestBody EventoModel evento){
        sEvento.updateEvento(evento);
        return new ResponseEntity<>("Se ha actualizado",HttpStatus.NO_CONTENT);
    }

}
