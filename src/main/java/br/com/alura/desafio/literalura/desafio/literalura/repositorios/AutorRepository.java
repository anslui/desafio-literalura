package br.com.alura.desafio.literalura.desafio.literalura.repositorios;
import br.com.alura.desafio.literalura.desafio.literalura.modelos.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface AutorRepository extends JpaRepository<Autor, Long> {
    Optional<Autor> findByNomeContainingIgnoreCase(String nome);

    @Query("SELECT a FROM Autor a WHERE :ano BETWEEN a.anoDeNascimento AND a.anoDeFalecimento")
    List<Optional<Autor>> buscarAutoresVivos(Integer ano);
}
