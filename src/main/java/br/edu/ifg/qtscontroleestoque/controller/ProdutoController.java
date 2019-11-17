package br.edu.ifg.qtscontroleestoque.controller;

import br.edu.ifg.qtscontroleestoque.dao.ProdutoDAO;
import br.edu.ifg.qtscontroleestoque.dto.ProdutoDTO;
import br.edu.ifg.qtscontroleestoque.entity.Produto;
import br.edu.ifg.qtscontroleestoque.service.ProdutoService;
import br.edu.ifg.qtscontroleestoque.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@Controller
public class ProdutoController {

    @Autowired
    private ProdutoDAO produtoDao;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private ProdutoService produtoService;

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
    public RedirectView cadastrar(ProdutoDTO produto, RedirectAttributes redir) {
        if (!usuarioService.isLogado()) {
            return new RedirectView("/login");
        }

        try {
            produtoService.cadastrar(produto);
        } catch (RuntimeException e) {
            produto.setMensagemErro(e.getMessage());
            redir.addFlashAttribute("produtoDto", produto);
        }

        return new RedirectView("/produto");
    }

    @RequestMapping(method = RequestMethod.GET, value = {"/produto/excluir"})
    public RedirectView excluir(@RequestParam("id") int id, RedirectAttributes redir) {
        if (!usuarioService.isLogado()) {
            return new RedirectView("/login");
        }

        if (produtoService.excluir(id)) {
            return new RedirectView("/produto");
        } else {
            ProdutoDTO produtoDTO = new ProdutoDTO();
            produtoDTO.setMensagemErro("O produto já possui movimentações e por isso não pode ser excluído.");
            redir.addFlashAttribute("produtoDtoExclusao", produtoDTO);
            return new RedirectView("/produto");
        }
    }





}
