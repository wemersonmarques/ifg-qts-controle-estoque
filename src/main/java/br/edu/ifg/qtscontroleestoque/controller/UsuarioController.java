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

import javax.servlet.http.HttpSession;

@Controller
public class UsuarioController {

    @Autowired
    private UsuarioDAO usuarioDao;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private HttpSession session;

    @RequestMapping(method = RequestMethod.GET, value="/usuario")
    public ModelAndView init() {
        ModelAndView mav = new ModelAndView("usuario-cadastro");
        return mav;
    }


    @RequestMapping(method = RequestMethod.POST, value = "/cadastrar")
    public ModelAndView cadastrar(UsuarioDTO usuarioDto) {
        ModelAndView mav = new ModelAndView("redirect:/login");
        Usuario usuario = new Usuario(usuarioDto);
        if (!usuarioService.hasCadastro(usuario)) {
            usuarioDao.salvar(usuario);
        }
        return mav;
    }

    @RequestMapping(value = "/perfil")
    public ModelAndView visualizarPefil() {
        if (usuarioService.isLogado()) {
            ModelAndView mav = new ModelAndView("usuario-perfil");
            String email = (String) session.getAttribute("user");
            Usuario usuario = usuarioDao.consultarPorEmail(email);
            mav.addObject("usuario", usuario);

            return mav;
        }

        return new ModelAndView("redirect:login");
    }
}
