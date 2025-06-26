package br.com.alura.desafio.literalura.desafio.literalura.dados;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosLivro(String title,
                         List<DadosAutor> authors,
                         List<String> summaries,
                         List<String> languages,
                         @JsonAlias("download_count") Long downloads) {
}
