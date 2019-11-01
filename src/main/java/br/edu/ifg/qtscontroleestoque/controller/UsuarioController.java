package br.edu.ifg.qtscontroleestoque.controller;

import br.edu.ifg.qtscontroleestoque.dao.UsuarioDAO;
import br.edu.ifg.qtscontroleestoque.dto.UsuarioDTO;
import br.edu.ifg.qtscontroleestoque.entity.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UsuarioController {

    @Autowired
    private UsuarioDAO usuarioDao;

    @RequestMapping(method = RequestMethod.POST, value = "/usuario/cadastrar")
    public ModelAndView cadastrar(UsuarioDTO usuarioDto) {
        ModelAndView mav = new ModelAndView("");

        Usuario usuario = new Usuario(usuarioDto);
        usuarioDao.salvar(usuario);

        return mav;
    }
}
