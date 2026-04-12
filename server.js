const express = require('express');
const cors = require('cors');
const app = express();
const PORT = 9000;

app.use(cors());
app.use(express.json());

// Banco de dados em memória
let produtos = [
    { id: 1, nome: "Arroz", quantidade: 50, preco: 25.90 },
    { id: 2, nome: "Feijão", quantidade: 30, preco: 8.50 }
];
let nextId = 3;

// GET - listar todos produtos
app.get('/produtos', (req, res) => {
    res.json(produtos);
});

// GET - buscar produto por id
app.get('/produtos/:id', (req, res) => {
    const id = parseInt(req.params.id);
    const produto = produtos.find(p => p.id === id);
    if (produto) {
        res.json(produto);
    } else {
        res.status(404).json({ error: "Produto não encontrado" });
    }
});

// POST - criar novo produto
app.post('/produtos', (req, res) => {
    const { nome, quantidade, preco } = req.body;
    const novoProduto = {
        id: nextId++,
        nome: nome,
        quantidade: quantidade,
        preco: preco
    };
    produtos.push(novoProduto);
    res.status(201).json(novoProduto);
});

// PUT - atualizar produto
app.put('/produtos/:id', (req, res) => {
    const id = parseInt(req.params.id);
    const { nome, quantidade, preco } = req.body;
    const index = produtos.findIndex(p => p.id === id);
    
    if (index !== -1) {
        produtos[index] = { ...produtos[index], nome, quantidade, preco };
        res.json(produtos[index]);
    } else {
        res.status(404).json({ error: "Produto não encontrado" });
    }
});

// DELETE - deletar produto
app.delete('/produtos/:id', (req, res) => {
    const id = parseInt(req.params.id);
    const index = produtos.findIndex(p => p.id === id);
    
    if (index !== -1) {
        produtos.splice(index, 1);
        res.status(204).send();
    } else {
        res.status(404).json({ error: "Produto não encontrado" });
    }
});

app.listen(PORT, () => {
    console.log(`API rodando em http://localhost:${PORT}`);
    console.log(`Para testar: http://localhost:${PORT}/produtos`);
});