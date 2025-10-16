package br.com.glandata.api.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.glandata.api.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    List<Optional<Cliente>> findByNomeIgnoreCaseContains(String nome);

    Optional<Cliente> findByCpf(String cpf);

}
