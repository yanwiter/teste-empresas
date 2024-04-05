package br.com.yanwiter.gerenciamentofuncionario.app.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Colaborador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String cpf;

    private String nome;

    @Column(name = "data_admissao")
    private String dataAdmissao;

    private String funcao;

    @Column(name = "remuneracao")
    private String remuneracao;

    @Column(name = "isPresidente")
    private boolean isPresidente;

    @Column(name = "idGerente")
    private Long idGerente;

    @Transient
    private List<Colaborador> subordinados;

    public Colaborador() {
    }

    public Colaborador(Long id, String cpf, String nome, String dataAdmissao, String funcao, String remuneracao, boolean isPresidente, Long idGerente) {
        this.id = id;
        this.cpf = cpf;
        this.nome = nome;
        this.dataAdmissao = dataAdmissao;
        this.funcao = funcao;
        this.remuneracao = remuneracao;
        this.isPresidente = isPresidente;
        this.idGerente = idGerente;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDataAdmissao() {
        return dataAdmissao;
    }

    public void setDataAdmissao(String dataAdmissao) {
        this.dataAdmissao = dataAdmissao;
    }

    public String getFuncao() {
        return funcao;
    }

    public void setFuncao(String funcao) {
        this.funcao = funcao;
    }

    public String getRemuneracao() {
        return remuneracao;
    }

    public void setRemuneracao(String remuneracao) {
        this.remuneracao = remuneracao;
    }

    public boolean getIsPresidente() {
        return isPresidente;
    }

    public void setPresidente(boolean isPresidente) {
        this.isPresidente = isPresidente;
    }

    public Long getIdGerente() {
        return idGerente;
    }

    public void setIdGerente(Long idGerente) {
        this.idGerente = idGerente;
    }

    public List<Colaborador> getSubordinados() {
        return subordinados;
    }

    public void setSubordinados(List<Colaborador> subordinados) {
        this.subordinados = subordinados;
    }

}
