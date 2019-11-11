package br.edu.ifg.qtscontroleestoque.controller;


import br.edu.ifg.qtscontroleestoque.dao.ProdutoDAO;
import br.edu.ifg.qtscontroleestoque.service.UsuarioService;
import br.edu.ifg.qtscontroleestoque.service.EstoqueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@org.springframework.stereotype.Controller
public class InitController {

    @Autowired
    private ProdutoDAO produtoDao;

    @Autowired
    private EstoqueService estoqueService;

    @Autowired
    private UsuarioService usuarioService;


    @RequestMapping(value = "/")
    public ModelAndView init() {
        if (!usuarioService.isLogado()) {
            return new ModelAndView("login");
        }
        return new ModelAndView("movimentacao");
    }
}
