Mercado Estoque

Integrantes:
Michel Greiner e Inácio Kunz

Sobre o projeto:
Este projeto foi desenvolvido como trabalho final da disciplina de Desenvolvimento Mobile II.
A proposta foi criar um aplicativo Android utilizando Kotlin no Android Studio, aplicando os conceitos vistos durante o semestre.
O aplicativo tem como objetivo auxiliar no controle de produtos em estoque, permitindo cadastrar, visualizar, editar e excluir itens de forma simples.

Funcionalidades:
Login de usuário (simulado)
Cadastro de usuário,
Cadastro de produtos,
Listagem de produtos,
Edição de produtos,
Exclusão de produtos.

Tecnologias utilizadas
Kotlin,
Android Studio,
Room (SQLite),
XML (layouts),
Git e GitHub.

Estrutura do projeto
O projeto foi desenvolvido utilizando Activities e Fragment:

Activities:
LoginActivity,
MainActivity,
CadastroProdutoActivity,
CadastroUsuarioActivity,
ListaProdutosActivity.

Fragment:
ListaProdutosFragment (responsável pela listagem dos produtos)

A ListaProdutosActivity funciona como um container, enquanto o Fragment contém a lógica da lista, seguindo uma melhor organização do código.

Persistência de dados
Foi utilizado o Room para armazenar os dados localmente no dispositivo.

O sistema realiza operações de:
Create (criar produto)
Read (listar produtos)
Update (editar produto)
Delete (excluir produto)

Como executar o projeto
Clonar o repositório:
git clone
Abrir o projeto no Android Studio
Aguardar o Gradle sincronizar
Executar o app em um emulador

Telas do sistema:
Tela de login
Tela principal
Cadastro de produto
Listagem de produtos
