package br.com.alura.desafio.literalura.desafio.literalura;

import br.com.alura.desafio.literalura.desafio.literalura.dados.DadosAutor;
import br.com.alura.desafio.literalura.desafio.literalura.dados.DadosLivro;
import br.com.alura.desafio.literalura.desafio.literalura.dados.DadosResultados;
import br.com.alura.desafio.literalura.desafio.literalura.modelos.Autor;
import br.com.alura.desafio.literalura.desafio.literalura.modelos.Livro;
import br.com.alura.desafio.literalura.desafio.literalura.repositorios.AutorRepository;
import br.com.alura.desafio.literalura.desafio.literalura.repositorios.LivroRepository;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;

public class Principal {
    private Scanner leitura = new Scanner(System.in);
    private ConsumoApi buscaApi = new ConsumoApi();
    private ConverteDados conversor = new ConverteDados();

    private final String ENDERECO = "https://gutendex.com/books/?search=";
    private Locale local = Locale.of("pt", "BR");

    private LivroRepository livroRepositorio;
    private AutorRepository autorRepositorio;

    public Principal(LivroRepository livroRepositorio, AutorRepository autorRepositorio) {
        this.livroRepositorio = livroRepositorio;
        this.autorRepositorio = autorRepositorio;
    }

    public void exibeMenu(){
        var opcao = -1;
        while (opcao != 0){
            var menu = """
                    
                    __________ MENU DESAFIO LITERALURA ________
                    
                    ( 1 ) Cadastrar livro pelo título
                    ( 2 ) Listar livros registrados
                    ( 3 ) Listar autores registrados
                    ( 4 ) Listar autores vivos em determinado ano
                    ( 5 ) Listar livros em um determinado idioma
                    ( 6 ) Top 10 livros mais baixados
                    ( 7 ) Buscar autor por nome
                    
                    ( 0 ) Sair
                    -------------------------------------------
                    Digite o número da opção desejada:
                    """;

            System.out.println(menu);
            try {
                opcao = leitura.nextInt();
                leitura.nextLine();
            } catch (InputMismatchException e){
                System.out.println("\nCaractere inválido. Digite um número correspondente ao menu.\n");
                leitura.nextLine();
                continue;
            }
            switch (opcao) {
                case 1:
                    cadastrarLivroPeloTitulo();
                    break;
                case 2:
                    listarTodosLivros();
                    break;
                case 3:
                    listarTodosAutores();
                    break;
                case 4:
                    listarAutoresVivos();
                    break;
                case 5:
                    listarLivrosPorIdioma();
                    break;
                case 6:
                    listarTop10Downloads();
                    break;
                case 7:
                    buscarAutorPorNome();
                    break;
                case 0:
                    System.out.println("\nEncerrando...\n");
                    break;
                default:
                    System.out.println("\nOpção inválida.\n");

        }
    }
}

    private void listarTop10Downloads() {
        List<Livro> livros = livroRepositorio.findTop10ByOrderByQuantidadeDownloadsDesc();
        livros.forEach(this::exibirLivro);
    }

    private void buscarAutorPorNome() {
        System.out.println("Digite o nome do autor:");
        String nome = leitura.nextLine().trim();
        List<Optional<Autor>> autor = autorRepositorio.findByNomeContainingIgnoreCase(nome);
        if (autor.isEmpty()){
            System.out.println("\nAutor não encontrado no banco de dados.");
        } else {
            autor.forEach(a -> {
                a.ifPresent(this::exibirAutor);
            });
        }
    }

    private void listarAutoresVivos() {
        System.out.println("Digite o ano que deseja buscar:");
        try {
            var ano = leitura.nextInt();
            List<Optional<Autor>> autoresVivos = autorRepositorio.buscarAutoresVivos(ano);
            if (autoresVivos.isEmpty()) {
                System.out.println("\nNão foi encontrado nenhum autor.\n");
            } else {
                autoresVivos.stream().map(Optional::get).forEach(this::exibirAutor);
            }
        } catch (InputMismatchException e) {
            System.out.println("\nCaractere inválido.\n");
            leitura.nextLine();
        }
    }

    private void listarLivrosPorIdioma() {
        System.out.println("Selecione o código de um dos idiomas cadastrados no banco de dados:");
        livroRepositorio.buscarIdiomasDisponiveis().stream().sorted().forEach(s -> {
                String nome = Locale.of(s).getDisplayLanguage(local);
                System.out.printf("%s - %s\n",
                        s, nome.substring(0, 1).toUpperCase() + nome.substring(1));
                });
        String idioma = leitura.nextLine().trim();
        List<Optional<Livro>> livros = livroRepositorio.findByIdiomaIgnoreCase(idioma);
        if (livros.isEmpty()){
            System.out.println("\nNenhum livro foi encontrado.\n");
        } else {
          livros.stream().map(Optional::get).forEach(this::exibirLivro);
        }
    }

    private void listarTodosAutores() {
        List<Autor> autores = autorRepositorio.findAll();
        if (autores.isEmpty()){
            System.out.println("\nNão há autores cadastrados.\n");
        } else {
            autores.forEach(this::exibirAutor);
        }
    }

    private void exibirAutor(Autor a) {
        System.out.printf("""
                        ___________________AUTOR___________________
                         Autor: %s (Id: %d)
                         Ano de nascimento: %d
                         Ano de falecimento: %d
                         Livros: %s
                        -------------------------------------------
                        """,
                a.getNome(), a.getId(), a.getAnoDeNascimento(), a.getAnoDeFalecimento(), a.getLivros()
                        .stream().map(Livro::getTitulo).collect(Collectors.toList()));
    }

    private void listarTodosLivros() {
        List<Livro> livros = livroRepositorio.findAll();
        if (livros.isEmpty()){
            System.out.println("\nNão há livros cadastrados.\n");
        } else {
            livros.forEach(this::exibirLivro);
        }
    }

    private void exibirLivro(Livro l){
        String nomeIdioma = Locale.of(l.getIdioma()).getDisplayLanguage(local);
        System.out.printf("""
                __________________ LIVRO __________________
                 Título: %s
                 Autor: %s
                 Idioma: %s
                 Número de downloads: %d
                -------------------------------------------
                """,
                l.getTitulo(), l.getAutor().getNome(), nomeIdioma.substring(0, 1).toUpperCase() + nomeIdioma.substring(1),
                l.getQuantidadeDownloads());
    }

    private void cadastrarLivroPeloTitulo(){
        System.out.println("Digite o título do livro:");
        String titulo = leitura.nextLine().trim();
        if (titulo.isEmpty()){
            System.out.println("\nTítulo não pode estar em branco.\n");
            return;
        }
        try {
            var tituloEncoded = URLEncoder.encode(titulo, StandardCharsets.UTF_8);
            String endereco = ENDERECO + tituloEncoded;

            var json = buscaApi.obterDados(endereco);
            DadosResultados dadosResultados = conversor.converteDados(json, DadosResultados.class);

            DadosLivro dadosLivro = dadosResultados.results().getFirst();
            if (livroRepositorio.findByTituloIgnoreCase(dadosLivro.title()).isPresent()){
                System.out.println("\nEsse livro já foi cadastrado.\n");
                return;
            }
            DadosAutor dadosAutor = dadosLivro.authors().getFirst();
            Autor autor = autorRepositorio.findByNomeIgnoreCase(dadosAutor.name())
                    .orElseGet(() -> autorRepositorio.save(new Autor(dadosAutor)));

            Livro livro = new Livro(dadosLivro);
            livro.setAutor(autor);
            livroRepositorio.save(livro);

            System.out.println("\nLivro cadastrado com sucesso!\n");
            exibirLivro(livro);
        } catch (NoSuchElementException e){
            System.out.println("\nLivro não encontrado.\n");
        }
    }
}
