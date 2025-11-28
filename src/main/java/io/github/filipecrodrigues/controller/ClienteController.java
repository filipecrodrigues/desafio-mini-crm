package io.github.filipecrodrigues.controller;

import io.github.filipecrodrigues.entity.Cliente;
import io.github.filipecrodrigues.entity.Contato;
import io.github.filipecrodrigues.repository.ClienteRepository;
import io.github.filipecrodrigues.repository.ContatoRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {


    //criando cliente repository
    private final ClienteRepository clienteRepository;
    //criando contato repository
    private final ContatoRepository  contatoRepository;

    //construtor passando cliente e contato como parametros
    public ClienteController(ClienteRepository clienteRepository, ContatoRepository contatoRepository) {
        this.clienteRepository = clienteRepository;
        this.contatoRepository = contatoRepository;
    }
    //criar um cliente
    @PostMapping
    public ResponseEntity<Cliente> criarCliente(@RequestBody Cliente payload){
        Cliente cliente  =clienteRepository.save(payload);
        return ResponseEntity.created(URI.create("/clientes"+ cliente.getId())).body(cliente);
    }
    //listar todos os clientes
    @GetMapping
    public ResponseEntity<List<Cliente>> listarClientes(){
        return ResponseEntity.ok(clienteRepository.findAll());
    }

    @PostMapping
    public ResponseEntity<Contato> criarContato(@PathVariable Long id, @RequestBody Contato payload){
        var clienteOpt =clienteRepository.findById (id);
        if (clienteOpt.isEmpty()) return ResponseEntity.notFound().build();


        var cliente =clienteOpt.get();
        payload.setId(null);
        payload.setCliente(cliente);

    }
}
