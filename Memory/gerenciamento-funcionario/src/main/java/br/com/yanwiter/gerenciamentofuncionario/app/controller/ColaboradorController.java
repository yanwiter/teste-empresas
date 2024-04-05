package br.com.yanwiter.gerenciamentofuncionario.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.yanwiter.gerenciamentofuncionario.app.model.Colaborador;
import br.com.yanwiter.gerenciamentofuncionario.app.service.ColaboradorService;

@RestController()
@RequestMapping
public class ColaboradorController {

    @Autowired
    private ColaboradorService service;

    @PostMapping("/adicionar")
    public ResponseEntity<String> adicionarColaborador(@RequestBody Colaborador colaborador) {
        service.adicionarColaborador(colaborador);
        return ResponseEntity.status(HttpStatus.CREATED).body("Colaborador adicionado com sucesso.");
    }

    @DeleteMapping("/remover/{cpf}")
    public ResponseEntity<String> removerColaborador(@PathVariable String cpf) {
        service.removerColaborador(cpf);
        return ResponseEntity.status(HttpStatus.OK).body("Colaborador removido com sucesso.");
    }

    @PutMapping("/alterar/{cpf}")
    public ResponseEntity<String> alterarColaborador(@PathVariable String cpf, @RequestBody Colaborador novoColaborador) {
        service.alterarColaborador(cpf, novoColaborador);
        return ResponseEntity.status(HttpStatus.OK).body("Colaborador alterado com sucesso.");
    }

    @GetMapping("/listar/{ano}")
    public ResponseEntity<Page<Colaborador>> listarColaboradoresAdmitidosNoAno(@PathVariable String ano, int pagina, int tamanho) {
        Page<Colaborador> colaboradores = service.listarColaboradoresAdmitidosNoAno(ano, pagina, tamanho);
        return ResponseEntity.status(HttpStatus.OK).body(colaboradores);
    }

    @GetMapping("/subordinados/{nome}")
    public ResponseEntity<Page<Colaborador>> listarSubordinados(@PathVariable String nome, int pagina, int tamanho) {
        Page<Colaborador> subordinados = service.listarSubordinados(nome, pagina, tamanho);
        return ResponseEntity.status(HttpStatus.OK).body(subordinados);
    }

}
