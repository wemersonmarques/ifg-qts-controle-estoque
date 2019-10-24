package br.edu.ifg.qtscontroleestoque.controller;


import br.edu.ifg.qtscontroleestoque.dao.ProdutoDAO;
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

    @RequestMapping(value = "/")
    public ModelAndView init() {
        return new ModelAndView("home");
    }


}
