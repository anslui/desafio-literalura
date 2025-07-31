package br.com.alura.desafio.literalura.modelos;
import br.com.alura.desafio.literalura.dados.DadosAutor;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "autores")
public class Autor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String nome;

    private Integer anoDeNascimento;
    private Integer anoDeFalecimento;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "autor")
    private List<Livro> livros = new ArrayList<>();

    public Autor() {
    }

    public Autor(DadosAutor dadosAutor){
        this.nome = dadosAutor.name();
        this.anoDeNascimento = dadosAutor.birthYear();
        this.anoDeFalecimento = dadosAutor.deathYear();
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public Integer getAnoDeNascimento() {
        return anoDeNascimento;
    }

    public Integer getAnoDeFalecimento() {
        return anoDeFalecimento;
    }

    public List<Livro> getLivros() {
        return livros;
    }

    @Override
    public String toString() {
        return "Autor{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", anoDeNascimento=" + anoDeNascimento +
                ", anoDeFalecimento=" + anoDeFalecimento +
                '}';
    }
}
