package br.com.alura.desafio.literalura.desafio.literalura.repositorios;
import br.com.alura.desafio.literalura.desafio.literalura.modelos.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LivroRepository extends JpaRepository<Livro, Long> {
    Optional<Livro> findByTituloIgnoreCase(String titulo);
    List<Optional<Livro>> findByIdiomaIgnoreCase(String idioma);
    List<Livro> findTop10ByOrderByQuantidadeDownloadsDesc();

    @Query("SELECT DISTINCT l.idioma FROM Livro l")
    List<String> buscarIdiomasDisponiveis();
}
