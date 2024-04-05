package br.com.yanwiter.gerenciamentofuncionario.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.yanwiter.gerenciamentofuncionario.app.model.Colaborador;
import br.com.yanwiter.gerenciamentofuncionario.app.repository.ColaboradorRepository;
import br.com.yanwiter.gerenciamentofuncionario.util.Util;

import java.util.List;

@Service
public class ColaboradorService {

    @Autowired
    private ColaboradorRepository colaboradorRepository;

    public void adicionarColaborador(Colaborador colaborador) {

        if (!Util.isValid(colaborador.getCpf()))
            throw new RuntimeException("CPF inválido");

        if (colaboradorRepository.findByCpf(colaborador.getCpf()) != null)
            throw new RuntimeException("Já existe um colaborador cadastrado com esse mesmo CPF.");

            colaborador.setDataAdmissao(Util.ObterDataFormatada(colaborador.getDataAdmissao()));             

            String cpfFormatado = Util.formatarCPF(colaborador.getCpf());
            colaborador.setCpf(cpfFormatado);

            String remuneracaoFormatada = Util.formatarRemuneracao(colaborador.getRemuneracao());
            colaborador.setRemuneracao(remuneracaoFormatada);

            if (Boolean.TRUE.equals(colaborador.getIsPresidente())) {
                Colaborador presidenteExistente = colaboradorRepository.findByIsPresidenteTrue();

                if (presidenteExistente != null) {
                    throw new RuntimeException("Já existe um presidente cadastrado.");
                }else{
                    colaborador.setPresidente(true);
                }
            }

        colaboradorRepository.save(colaborador);
    }

    public void removerColaborador(String cpf) {
        Colaborador colaborador = colaboradorRepository.findByCpf(cpf);

        if (colaborador == null) 
            throw new RuntimeException("Colaborador não encontrado.");

        colaboradorRepository.delete(colaborador);
    }

    public void alterarColaborador(String cpf, Colaborador novoColaborador) {
        Colaborador colaboradorExistente = colaboradorRepository.findByCpf(cpf);

        if (colaboradorExistente == null)
            throw new RuntimeException("Colaborador não encontrado.");

        if (novoColaborador.getNome() != null && !novoColaborador.getNome().isEmpty())
            colaboradorExistente.setNome(novoColaborador.getNome());

        if (novoColaborador.getDataAdmissao() != null)
            colaboradorExistente.setDataAdmissao(Util.ObterDataFormatada(novoColaborador.getDataAdmissao()));

        if (novoColaborador.getFuncao() != null && !novoColaborador.getFuncao().isEmpty())
            colaboradorExistente.setFuncao(novoColaborador.getFuncao());

        if (novoColaborador.getRemuneracao() != null && !novoColaborador.getRemuneracao().isEmpty())
            colaboradorExistente.setRemuneracao(Util.formatarRemuneracao(novoColaborador.getRemuneracao()));

        colaboradorRepository.save(colaboradorExistente);
    }

    public Page<Colaborador> listarColaboradoresAdmitidosNoAno(String ano, int pagina, int tamanho) {
        Pageable paginavel = PageRequest.of(pagina, tamanho);
        Page<Colaborador> colaboradores = colaboradorRepository.findByDataAdmissaoContaining(ano, paginavel);
        return ObterSubordinados(colaboradores);
    }

    public Page<Colaborador> listarSubordinados(String nome, int pagina, int tamanho) {
        Pageable paginavel = PageRequest.of(pagina, tamanho);
        Page<Colaborador> colaboradores = colaboradorRepository.findByNomeContainingIgnoreCase(nome, paginavel);
        return ObterSubordinados(colaboradores);
    }
    
    private Page<Colaborador> ObterSubordinados(Page<Colaborador> gerentes){
        for (Colaborador gerente : gerentes) {
            List<Colaborador> subordinados = colaboradorRepository.findByIdGerente(gerente.getId());
            gerente.setSubordinados(subordinados);
        }
        return gerentes;
    }

}
