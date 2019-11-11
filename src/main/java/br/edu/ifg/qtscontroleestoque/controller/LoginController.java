package br.edu.ifg.qtscontroleestoque.controller;

import br.edu.ifg.qtscontroleestoque.dao.UsuarioDAO;
import br.edu.ifg.qtscontroleestoque.dto.UsuarioDTO;
import br.edu.ifg.qtscontroleestoque.entity.Usuario;
import br.edu.ifg.qtscontroleestoque.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.persistence.NoResultException;
import javax.servlet.http.HttpSession;

@Controller
public class LoginController {

    @Autowired
    private HttpSession session;

    @Autowired
    private UsuarioDAO usuarioDao;

    @Autowired
    private UsuarioService usuarioService;

    @RequestMapping(method = RequestMethod.GET, value = "/login")
    public ModelAndView init() {
        ModelAndView mav = new ModelAndView("login");
        return mav;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/login/autenticar")
    public ModelAndView autenticar(UsuarioDTO usuarioDto) {
        try {
            Usuario usuarioDb = usuarioDao.consultarPorEmail(usuarioDto.getEmail());
            if (usuarioDb != null && usuarioService.isUsuarioValido(usuarioDb)) {
                session.setAttribute("user", usuarioDb.getEmail());
                return new ModelAndView("redirect:/");
            }
            return new ModelAndView("redirect:/login");
        } catch (NoResultException e) {
            return new ModelAndView("redirect:/login");
        }
    }

    @RequestMapping(value = "/login/deslogar")
    public ModelAndView deslogar() {
        session.invalidate();
        return new ModelAndView("redirect:/");
    }
}
