package br.com.glandata.api.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.glandata.api.dto.ClienteDto;
import br.com.glandata.api.model.Cliente;
import br.com.glandata.api.repository.ClienteRepository;

@RestController
@RequestMapping("clientes")
public class ClienteController {

    @Autowired
    private ClienteRepository clienterepository;

    @GetMapping
    public ResponseEntity<List<Cliente>> listAll() {
        List<Cliente> cliente = new ArrayList<>();
        cliente = clienterepository.findAll();
        return new ResponseEntity<List<Cliente>>(cliente, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Cliente> save(@RequestBody Cliente cliente){
        clienterepository.save(cliente);
        return new ResponseEntity<Cliente>(cliente, HttpStatus.CREATED);
    }

    // @GetMapping("/nome/{nomeCliente}")
    // public ResponseEntity<List<Optional<Cliente>>> trazPorNome(@PathVariable("nomeCliente") String name) {
    //     return clienterepository.findByNomeIgnoreCaseContains(name)
    //             .map(cliente -> ResponseEntity.ok().body(cliente))
    //             .orElse(ResponseEntity.notFound().build());
    // }

    @GetMapping("/{cpfCliente}")
    public ResponseEntity<Cliente> trazProduto(@PathVariable("cpfCliente") String cpf) {
        return clienterepository.findByCpf(cpf)
                .map(cliente -> ResponseEntity.ok().body(cliente))
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{idCliente}")
    public ResponseEntity<?> deleteCliente(@PathVariable("idCliente") Long id) {
        return clienterepository.findById(id)
                .map(cliente-> {
                    clienterepository.deleteById(cliente.getId());
                    return ResponseEntity.noContent().build();
                }).orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{idCliente}")
    public ResponseEntity<?> atualizaCliente(@PathVariable("idCliente") Long id, @RequestBody Cliente cliente) {
        return clienterepository.findById(id)
                .map(cli-> {
                    cli.setNome(cliente.getNome());
                    cli.setEmail(cliente.getEmail());
                    cli.setSituacao(cliente.getSituacao());
                    Cliente clienteAtualizado = clienterepository.save(cli);
                    return ResponseEntity.ok().body(clienteAtualizado);
                }).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("listaDto")
    public ResponseEntity<List<ClienteDto>> listDto() {
        List<Cliente> cliente = new ArrayList<>();
        cliente = clienterepository.findAll();
        return new ResponseEntity<>(ClienteDto.converter(cliente), HttpStatus.OK);
    }

}
