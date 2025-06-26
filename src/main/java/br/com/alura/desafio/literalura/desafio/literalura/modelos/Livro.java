package br.com.alura.desafio.literalura.desafio.literalura.modelos;
import br.com.alura.desafio.literalura.desafio.literalura.dados.DadosLivro;
import jakarta.persistence.*;

@Entity
@Table(name = "livros", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"autor_id", "nome"}),
        @UniqueConstraint(columnNames = "titulo")
})
public class Livro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String titulo;

    @ManyToOne
    @JoinColumn(name = "autor_id")
    private Autor autor;

    private String idioma;
    private Long quantidadeDownloads;

    public Livro() {
    }

    public Livro(DadosLivro dadosLivro) {
        this.titulo = dadosLivro.title();
        this.idioma = dadosLivro.languages().getFirst();
        this.quantidadeDownloads = dadosLivro.downloads();
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    public Long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public Autor getAutor() {
        return autor;
    }

    public String getIdioma() {
        return idioma;
    }

    public Long getQuantidadeDownloads() {
        return quantidadeDownloads;
    }

    @Override
    public String toString() {
        return
                "titulo='" + titulo + '\'' +
                ", autor=" + autor +
                ", idioma='" + idioma + '\'' +
                ", quantidadeDownloads=" + quantidadeDownloads;
    }
}
