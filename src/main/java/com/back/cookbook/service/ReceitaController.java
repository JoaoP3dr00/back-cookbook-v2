package com.back.cookbook.service;

import com.back.cookbook.business.ReceitaManager;
import com.back.cookbook.dataac.entity.ReceitaEntity;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


@RestController
@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping("/receita")
public class ReceitaController {
    @Autowired(required = true)
    ReceitaManager receitaManager;

    @GetMapping("/")
    @ResponseBody
    public String getReceitas(){
        return "oi";
    }

    // Retornar um erro ou código de erro se der errado
    @PostMapping(value = "/adicionar", consumes = "multipart/form-data")
    public ResponseEntity<String> adicionar(
        @RequestParam String nome,
        @RequestParam(required = false, defaultValue = "") String modo_prep,
        @RequestParam(required = false, defaultValue = "") String ingredientes,
        @RequestParam(required = false, defaultValue = "") String tempo,
        @RequestParam(required = false, defaultValue = "") String qtd_pessoas,
        @RequestParam(required = false, defaultValue = "") String custo,
        @RequestParam(value = "imagem", required = false) MultipartFile imagem
    ) {
        try {
            String caminhoImagem = null;
            if (imagem != null && !imagem.isEmpty()) {
                caminhoImagem = salvarImagem(imagem);
            }
            receitaManager.criarReceita(nome, modo_prep, ingredientes, tempo, qtd_pessoas, custo, caminhoImagem);
            return ResponseEntity.ok("Receita adicionada com sucesso.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Erro: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao adicionar receita: " + e.getMessage());
        }
    }

    private static final String UPLOAD_DIR = "uploads/";

    private String salvarImagem(MultipartFile imagem) throws IOException {
        if (imagem.isEmpty() || !imagem.getContentType().startsWith("image/")) {
            throw new IllegalArgumentException("Arquivo enviado não é uma imagem válida.");
        }

        String nomeArquivo = UUID.randomUUID().toString() + "_" + imagem.getOriginalFilename();
        Path pastaDestino = Paths.get(UPLOAD_DIR);
        Path caminhoCompleto = pastaDestino.resolve(nomeArquivo);

        if (!Files.exists(pastaDestino)) {
            Files.createDirectories(pastaDestino);
        }

        Files.copy(imagem.getInputStream(), caminhoCompleto, StandardCopyOption.REPLACE_EXISTING);
        return caminhoCompleto.toString();
    }

    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<ReceitaEntity> obterReceita(@PathVariable Integer id) {
        ReceitaEntity receita = receitaManager.obterReceitaPorId(id);
        if (receita != null) {
            return ResponseEntity.ok(receita);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PutMapping("/atualizar")
    public ResponseEntity<String> atualizarReceita(
        @RequestParam Integer id,
        @RequestParam(required = false) String nome,
        @RequestParam(required = false) String modo_prep,
        @RequestParam(required = false) String ingredientes,
        @RequestParam(required = false) String tempo,
        @RequestParam(required = false) String qtd_pessoas,
        @RequestParam(required = false) String custo,
        @RequestParam(required = false) MultipartFile imagem
    ) {
        try {
            String caminhoImagem = null;
            if (imagem != null && !imagem.isEmpty()) {
                caminhoImagem = salvarImagem(imagem);
            }
            receitaManager.atualizarReceita(id, nome, modo_prep, ingredientes, tempo, qtd_pessoas, custo, caminhoImagem);
            return ResponseEntity.ok("Receita atualizada com sucesso.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao atualizar receita: " + e.getMessage());
        }
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<String> deletarReceita(@PathVariable Integer id) {
        try {
            receitaManager.deletarReceita(id);
            return ResponseEntity.ok("Receita deletada com sucesso.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao deletar receita: " + e.getMessage());
        }
    }

    @GetMapping("/listar")
    @ResponseBody
    public List<ReceitaEntity> listarReceitas() {
        return receitaManager.listarReceitas();
    }

}
