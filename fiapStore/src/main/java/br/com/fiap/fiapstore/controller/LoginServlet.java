package br.com.fiap.fiapstore.controller;

import br.com.fiap.fiapstore.bo.EmailBo;
import br.com.fiap.fiapstore.dao.UsuarioDao;
import br.com.fiap.fiapstore.exception.EmailException;
import br.com.fiap.fiapstore.model.Usuario;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.time.LocalDate;

import static br.com.fiap.fiapstore.factory.DaoFactory.getUsuarioDao;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    private UsuarioDao usuarioDao;
    private EmailBo bo;

    public LoginServlet() {
        usuarioDao = getUsuarioDao();
        bo = new EmailBo();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String email = req.getParameter("email");
        String senha = req.getParameter("senha");

        Usuario usuario = new Usuario(email, senha);

        if (usuarioDao.validar(usuario)) {

            HttpSession session = req.getSession();
            session.setAttribute("user", email);

            String mensagem = "Um login foi realizado na plataforma em " + LocalDate.now();
            req.getRequestDispatcher("home.jsp").forward(req, resp);

            try {
                bo.enviarEmail(email, "Login Realizado", mensagem);
            } catch (EmailException e) {
                e.printStackTrace();
            }

        } else {
            req.setAttribute("erro", "Usuário e/ou senha inválidos");
            req.getRequestDispatcher("index.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();
        session.invalidate();
        req.getRequestDispatcher("home.jsp").forward(req, resp);
    }
}
