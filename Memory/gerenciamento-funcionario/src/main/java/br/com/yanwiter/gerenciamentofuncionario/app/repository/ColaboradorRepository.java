package br.com.yanwiter.gerenciamentofuncionario.app.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.yanwiter.gerenciamentofuncionario.app.model.Colaborador;

@Repository
public interface ColaboradorRepository extends JpaRepository<Colaborador, Long>{

    Colaborador findByCpf(String cpf);
    Colaborador findByIsPresidenteTrue();

    Page<Colaborador> findByNomeContainingIgnoreCase(String nome, Pageable paginavel);
    Page<Colaborador> findByDataAdmissaoContaining(String ano, Pageable paginavel);
    Page<Colaborador> findByIdGerente(Long idGerente, Pageable paginavel);
    List<Colaborador> findByIdGerente(Long idGerente);

} 