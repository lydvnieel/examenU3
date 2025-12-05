package com.examenU3.examen.service;

import com.examenU3.examen.dtos.CreateClienteDTO;
import com.examenU3.examen.model.Cliente;
import com.examenU3.examen.repository.ClienteRepository;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class ClienteService {

    ClienteRepository clienteRepository;
    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }


    public Map<String, Object> getClienteById(Integer id) {
        Map<String,Object> mapResponse = new HashMap<>();
        Optional<Cliente> optCliente = clienteRepository.findById(id);
        if(optCliente.isPresent()) {
            mapResponse.put("registro", optCliente.get());
            mapResponse.put("mensaje", "Registro encontrado");
        }else{
            mapResponse.put("mensaje", "Registro no encontrado");
        }
        return mapResponse;
    }

    public Map<String, Object> createCliente(CreateClienteDTO clienteDTO) {
        Map<String,Object> respuesta = new HashMap<>();

        if(clienteRepository.existsByEmail(clienteDTO.getEmail())){
            respuesta.put("mensaje", "el  email ya existe");
            respuesta.put("code", 400);
            return respuesta;
        }

        Cliente cliente = new Cliente();
        cliente.setNombre(clienteDTO.getNombre());
        cliente.setTelefono(clienteDTO.getTelefono());
        cliente.setEmail(clienteDTO.getEmail());
        clienteRepository.save(cliente);
        respuesta.put("mensaje", "Cliente creado");
        return respuesta;

    }

    public Map<String, Object> actualizarCliente(Integer id, CreateClienteDTO clienteDTO) {
        Map<String,Object> respuesta = new HashMap<>();

        if(!clienteRepository.existsById(id)){
            respuesta.put("mensaje", "El cliente no existe");
            respuesta.put("code", 404);
        }

        Optional<Cliente> optCliente = clienteRepository.findById(id);
        if(optCliente.isPresent()) {
            Cliente clienteExiste = optCliente.get();
            clienteExiste.setNombre(clienteDTO.getNombre());
            clienteExiste.setTelefono(clienteDTO.getTelefono());
            clienteExiste.setEmail(clienteDTO.getEmail());
            clienteRepository.save(clienteExiste);
            respuesta.put("mensaje", "Cliente actualizado");
            respuesta.put("cliente",  clienteExiste);
            respuesta.put("code", 200);

        }
        return respuesta;

    }
}
