package br.edu.ifg.qtscontroleestoque.dao;

import br.edu.ifg.qtscontroleestoque.entity.Movimentacao;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class MovimentacaoDAO extends AbstractGenericDAO<Movimentacao> {

}
