package br.com.glandata.api.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="clientes")
public class Cliente {

    public Cliente() {
	}
	
	public Cliente(Long id) {
		this.id = id;
	}

	@Getter @Setter
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Getter @Setter
	@NotBlank(message = "Informe o nome do cliente")
	private String nome;
	
	@Getter @Setter
	@NotBlank
	private String cpf;
	
	@Getter @Setter
	@NotBlank
	private String situacao;
	
	@Getter @Setter
	@Email (message = "Email inválido")
	@NotBlank(message = "Informe um email válido")
	private String email;

	@JsonIgnore
	@Getter @Setter
	@Column(name = "data_cadastro")
	private LocalDate dataCadastro = LocalDate.now();
    
}
