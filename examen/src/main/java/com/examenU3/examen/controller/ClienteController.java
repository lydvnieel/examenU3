package com.examenU3.examen.controller;


import com.examenU3.examen.dtos.CreateClienteDTO;
import com.examenU3.examen.model.Cliente;
import com.examenU3.examen.service.ClienteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/examen")
@CrossOrigin(origins = "*")

public class ClienteController {

    ClienteService clienteService;
    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findById(@PathVariable Integer id) {
        Map <String, Object> respuesta = clienteService.getClienteById(id);
        return new  ResponseEntity<>(respuesta, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<Object> create(@RequestBody CreateClienteDTO clienteDTO) {
        Map <String,Object> respuesta = clienteService.createCliente(clienteDTO);
        System.out.println(clienteDTO.getId());
        System.out.println(clienteDTO.getNombre());
        System.out.println(clienteDTO.getTelefono());
        System.out.println(clienteDTO.getEmail());
        return new  ResponseEntity<>(respuesta, HttpStatus.OK);

    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable Integer id, @RequestBody CreateClienteDTO clienteDTO) {
        Map<String, Object> respuesta = clienteService.actualizarCliente(id,clienteDTO);
        System.out.println(clienteDTO.getNombre());
        System.out.println(clienteDTO.getTelefono());
        System.out.println(clienteDTO.getEmail());
        return new  ResponseEntity<>(respuesta, HttpStatus.OK);
    }


}
