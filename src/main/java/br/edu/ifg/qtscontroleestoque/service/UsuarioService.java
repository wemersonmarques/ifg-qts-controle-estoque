package br.edu.ifg.qtscontroleestoque.service;

import br.edu.ifg.qtscontroleestoque.dao.UsuarioDAO;
import br.edu.ifg.qtscontroleestoque.dto.UsuarioDTO;
import br.edu.ifg.qtscontroleestoque.entity.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.persistence.NoResultException;
import javax.servlet.http.HttpSession;

@Configuration
public class UsuarioService {

    @Autowired
    private UsuarioDAO usuarioDao;

    @Autowired
    private HttpSession session;

    public boolean hasCadastro(int id){
        Usuario usuario = (Usuario) usuarioDao.consultarPorId(Usuario.class, id);
        return usuario != null;
    }

    public boolean isLogado() {
        return session.getAttribute("user") != null;
    }

    public boolean isUsuarioValido(Usuario usuario) {
        try {
            Usuario userDb = usuarioDao.consultarPorEmail(usuario.getEmail());
            return (userDb != null && userDb.getEmail().equals(usuario.getEmail()) &&
                    userDb.getSenha().equals(usuario.getSenha()));
        } catch (NoResultException e) {
            return false;
        }
    }

    public boolean hasCadastro(Usuario usuario) {
        try {
            Usuario userDb = usuarioDao.consultarPorEmail(usuario.getEmail());
            return true;
        } catch (NoResultException e) {
            return false;
        }
    }

    public void cadastrar(UsuarioDTO usuarioDTO) {
        if (!hasCadastro(new Usuario(usuarioDTO))) {
            usuarioDao.salvar(new Usuario(usuarioDTO));
        } else {
            throw new RuntimeException("Email já cadastrado!");
        }
    }
}
