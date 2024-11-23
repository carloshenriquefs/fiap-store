package br.com.fiap.fiapstore.controller;

import br.com.fiap.fiapstore.dao.ProdutoDao;
import br.com.fiap.fiapstore.exception.DBException;
import br.com.fiap.fiapstore.model.Produto;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import static br.com.fiap.fiapstore.factory.DaoFactory.getProdutoDao;
import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;
import static java.lang.Integer.valueOf;
import static java.time.LocalDate.parse;

@WebServlet("/produtos")
public class ProdutoServlet extends HttpServlet {

    private ProdutoDao dao;

    @Override
    public void init(ServletConfig config) throws ServletException {
        dao = getProdutoDao();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String acao = req.getParameter("acao");

        switch (acao) {
            case "cadastrar":
                cadastrar(req, resp);
                break;
            case "editar":
                editar(req, resp);
                break;
        }

    }

    private void cadastrar(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String nome = req.getParameter("nome");
        double valor = parseDouble(req.getParameter("valor"));
        int quantidade = valueOf(req.getParameter("quantidade"));
        LocalDate fabricacao = parse(req.getParameter("fabricacao"));

        Produto produto = new Produto(0, nome, valor, quantidade, fabricacao);

        try {
            dao.cadastrar(produto);
            req.setAttribute("mensagem", "Produto cadastrado com sucesso!");
        } catch (DBException e) {
            e.printStackTrace();
            req.setAttribute("erro", "Erro ao cadastrar produto");
        }

        req.getRequestDispatcher("cadastro-produto.jsp").forward(req, resp);
    }

    private void editar(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            int codigo = Integer.parseInt(req.getParameter("codigo"));
            String nome = req.getParameter("nome");
            double valor = parseDouble(req.getParameter("valor"));
            int quantidade = valueOf(req.getParameter("quantidade"));
            LocalDate fabricacao = parse(req.getParameter("fabricacao"));

            Produto produto = new Produto(codigo, nome, valor, quantidade, fabricacao);
            dao.atualizar(produto);

            req.setAttribute("mensagem", "Produto atualizado com sucesso!");
        } catch (DBException e) {
            e.printStackTrace();
            req.setAttribute("erro", "Erro ao atualizar produto");
        } catch (Exception e) {
            e.printStackTrace();
            req.setAttribute("erro", "Por favor, valide os dados");
        }

        listar(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String acao = req.getParameter("acao");

        switch (acao) {
            case "listar":
                listar(req, resp);
                break;
            case "abrir-form-edicao":
                abrirForm(req, resp);
                break;
        }
    }

    private void abrirForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = parseInt(req.getParameter("codigo"));
        Produto produto = dao.buscar(id);
        req.setAttribute("produto", produto);
        req.getRequestDispatcher("editar-produto.jsp").forward(req, resp);
    }

    private void listar(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Produto> lista = dao.listar();
        req.setAttribute("produtos", lista);
        req.getRequestDispatcher("lista-produto.jsp").forward(req, resp);
    }
}
