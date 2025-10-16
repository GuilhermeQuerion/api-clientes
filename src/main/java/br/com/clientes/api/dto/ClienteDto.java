package br.com.glandata.api.dto;

import java.util.List;
import java.util.stream.Collectors;

import br.com.glandata.api.model.Cliente;
import lombok.Getter;

public class ClienteDto {

    public ClienteDto(Cliente cliente) {
        this.nome = cliente.getNome();
        this.email = cliente.getEmail();
        this.situacao = cliente.getSituacao();
    }

    @Getter 
    private String nome;

    @Getter
	private String email;

    @Getter
	private String situacao;

    public static List<ClienteDto> converter(List<Cliente> clientes){
        return clientes.stream().map(ClienteDto::new).collect(Collectors.toList());
    }
    
}
