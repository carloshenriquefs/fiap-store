package br.com.fiap.fiapstore.teste;

import br.com.fiap.fiapstore.dao.ProdutoDao;
import br.com.fiap.fiapstore.exception.DBException;
import br.com.fiap.fiapstore.model.Produto;

import java.time.LocalDate;
import java.util.List;

import static br.com.fiap.fiapstore.factory.DaoFactory.getProdutoDao;

public class ProdutoDaoTeste {

    public static void main(String[] args) {

        ProdutoDao dao = getProdutoDao();

        Produto produto = new Produto(
                0,
                "Mouse Wireless",
                77.99,
                100,
                LocalDate.of(2024, 10, 21)
        );

        try {
            dao.cadastrar(produto);
        } catch (DBException e) {
            throw new RuntimeException(e);
        }

        produto = dao.buscar(1);
        produto.setNome("Monitor LED 24P");
        produto.setValor(891.99);

        try {
            dao.atualizar(produto);
            System.out.println("Produto atualizado.");
        } catch (DBException e) {
            e.printStackTrace();
        }

        List<Produto> lista = dao.listar();
        for (Produto item : lista) {
            System.out.println(
                    item.getNome() + " - " +
                            item.getQuantidade() + " - " +
                            item.getValor());
        }

        try {
            dao.remover(1);
            System.out.println("Produto removido.");
        } catch (DBException e) {
            throw new RuntimeException(e);
        }
    }
}
