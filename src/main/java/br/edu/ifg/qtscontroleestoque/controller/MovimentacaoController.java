package br.edu.ifg.qtscontroleestoque.controller;

import br.edu.ifg.qtscontroleestoque.dao.MovimentacaoDAO;
import br.edu.ifg.qtscontroleestoque.dao.ProdutoDAO;
import br.edu.ifg.qtscontroleestoque.dto.MovimentacaoDTO;
import br.edu.ifg.qtscontroleestoque.entity.Movimentacao;
import br.edu.ifg.qtscontroleestoque.entity.Produto;
import br.edu.ifg.qtscontroleestoque.service.EstoqueService;
import br.edu.ifg.qtscontroleestoque.type.ETipoMovimentacao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MovimentacaoController {

    @Autowired
    private MovimentacaoDAO movimentacaoDao;

    @Autowired
    private EstoqueService estoqueService;

    @Autowired
    private ProdutoDAO produtoDao;

    @RequestMapping(method = RequestMethod.GET, value = "/movimentacao")
    public ModelAndView init(){
        ModelAndView mav = new ModelAndView("movimentacao");
        mav.addObject("produtos", produtoDao.consultarTodos(Produto.class));
        mav.addObject("movimentacoes", movimentacaoDao.consultarTodos(Movimentacao.class));
        return mav;
    }

    @RequestMapping(value = "/movimentacao/cadastrar", method = RequestMethod.POST)
    public ModelAndView cadastrar(MovimentacaoDTO movimentacao) {
        ModelAndView mav = new ModelAndView("movimentacao");
        try {
            estoqueService.movimentar(ETipoMovimentacao.valueOf(movimentacao.getTipoMovimentacao()),
                    (Produto) produtoDao.consultarPorId(Produto.class, movimentacao.getCodigoProduto()),
                    movimentacao.getQuantidade());
        } catch(RuntimeException e) {
            MovimentacaoDTO dto = new MovimentacaoDTO();
            dto.setMensagem(e.getMessage());
            mav.addObject("movimentacaoDto", dto);
        } finally {
            return mav;
        }
    }

    @RequestMapping(method = RequestMethod.GET,value = "/movimentacao/remover")
    public ModelAndView remover(@RequestParam int id){
        ModelAndView mav = new ModelAndView("");
        movimentacaoDao.deletarPorId(Movimentacao.class, id);
        return mav;
    }
}
