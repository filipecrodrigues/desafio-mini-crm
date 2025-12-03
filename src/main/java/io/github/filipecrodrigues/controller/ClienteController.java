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
        Cliente cliente  = clienteRepository.save(payload);
        return ResponseEntity
                .created(URI.create("/api/clientes"+ cliente.getId()))
                .body(cliente);
    }
    //listar todos os clientes
    @GetMapping
    public ResponseEntity<List<Cliente>> listarClientes(){
        return ResponseEntity.ok(clienteRepository.findAll());
    }

    @PostMapping("/{id}/contatos")
    public ResponseEntity<Contato> criarContato(@PathVariable Long id, @RequestBody Contato payload){
        var clienteOpt = clienteRepository.findById (id);
        if (clienteOpt.isEmpty()) {
            return ResponseEntity.notFound().build();

        }

        var cliente =clienteOpt.get();
        payload.setId(null);
        payload.setCliente(cliente);

        var salvo = contatoRepository.save(payload);
        return ResponseEntity
                .created(URI.create("/api/clientes"+id+"/contatos"+salvo.getId()))
                .body(salvo);
    }

    //endpoint listar contatos
    @GetMapping("/{id}/contatos")
    public ResponseEntity<List<Contato>> listarContatos(@PathVariable Long id){
        return clienteRepository.findById(id)
                .map( c -> ResponseEntity.ok(c.getContatos())) //o c é o parâmetro da expressão lambda, ou seja, uma variável que representa cada objeto Cliente que está sendo processado dentro do map
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
