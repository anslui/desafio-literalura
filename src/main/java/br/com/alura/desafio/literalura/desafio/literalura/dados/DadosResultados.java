package br.com.alura.desafio.literalura.desafio.literalura.dados;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;
@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosResultados(List<DadosLivro> results) {
}
