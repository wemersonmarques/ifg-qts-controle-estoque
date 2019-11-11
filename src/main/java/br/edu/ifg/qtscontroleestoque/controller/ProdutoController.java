package br.edu.ifg.qtscontroleestoque.controller;

import br.edu.ifg.qtscontroleestoque.dao.ProdutoDAO;
import br.edu.ifg.qtscontroleestoque.entity.Produto;
import br.edu.ifg.qtscontroleestoque.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class ProdutoController {

    @Autowired
    private ProdutoDAO produtoDao;

    @Autowired
    private UsuarioService usuarioService;

    @RequestMapping(method = RequestMethod.GET, value = {"/produto"})
    public ModelAndView init() {
        if (!usuarioService.isLogado()) {
            return new ModelAndView("login");
        }

        ModelAndView mav = new ModelAndView("produto");
        List<Produto> produtos = produtoDao.consultarTodos(Produto.class);
        mav.addObject("produtos", produtos);
        return mav;
    }

    @RequestMapping(method = RequestMethod.POST, value = {"/produto/cadastrar"})
    public ModelAndView cadastrar(Produto produto) {
        if (!usuarioService.isLogado()) {
            return new ModelAndView("login");
        }

        produtoDao.salvar(produto);
        return new ModelAndView("redirect:/produto");
    }

    @RequestMapping(method = RequestMethod.GET, value = {"/produto/consultar/id"})
    public ModelAndView consultarPorId(@RequestParam int id) {
        if (!usuarioService.isLogado()) {
            return new ModelAndView("login");
        }

        Produto prod = (Produto) produtoDao.consultarPorId(Produto.class, id);
        return new ModelAndView("");
    }

    @RequestMapping(method = RequestMethod.GET, value = {"/produto/consultar/all"})
    public ModelAndView consultarTodos() {
        if (!usuarioService.isLogado()) {
            return new ModelAndView("login");
        }

        List<Produto> produtos = produtoDao.consultarTodos(Produto.class);
        return new ModelAndView("");
    }



}
