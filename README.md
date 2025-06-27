# Desafio LiterAlura

*Projeto desenvolvido durante o curso [ONE - Oracle Next Education](https://www.oracle.com/br/education/oracle-next-education/ "ONE - Oracle Next Education").*

Uma aplicação Java com interação textual(via console) para catalogar, filtrar e exibir livros utilizando a API Gutendex.
## Funcionalidades

- **Cadastrar livro** - Busca livro na API Gutendex e o cadastra no banco de dados
- **Listar livros** - Exibe todos os livros cadastrados no banco de dados
- **Listar autores** - Exibe todos os autores registrados no banco de dados
- **Autores vivos** - Filtra autores que estavam vivos em um ano específico
- **Livros por idioma** - Filtra livros em um idioma específico
- **Top 10 livros** - Ranking dos livros mais baixados
- **Buscar autor** - Busca autor pelo nome

## Tecnologias Utilizadas

- **Java**
- **Spring Boot**
- **Spring Data JPA**
- **PostgreSQL**
- **Jackson**

## API

**Gutendex API**: fornece informações do Project Gutenberg, uma livraria virtual de livros gratuitos.

- **Não requer chave de API**
- **Documentação e mais informações**: https://gutendex.com/

## Exemplo de uso

```
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

> 1

Digite o título do livro:
> alice's adventure in wonderland

Livro cadastrado com sucesso!

__________________ LIVRO __________________
 Título: Alice's Adventures in Wonderland
 Autor: Carroll, Lewis
 Idioma: Inglês
 Número de downloads: 47514
-------------------------------------------
```
