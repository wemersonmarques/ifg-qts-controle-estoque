import br.edu.ifg.qtscontroleestoque.acceptance.tests.cadastro.movimentacao.MovimentacaoAcceptanceTest;
import br.edu.ifg.qtscontroleestoque.acceptance.tests.cadastro.produto.ProdutoAcceptanceTest;
import br.edu.ifg.qtscontroleestoque.acceptance.tests.cadastro.usuario.UsuarioAcceptanceTest;
import br.edu.ifg.qtscontroleestoque.unit.EstoqueServiceUnitTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        // Unitário
        EstoqueServiceUnitTest.class,
        // Aceitação (End-2-end)
        UsuarioAcceptanceTest.class,
        ProdutoAcceptanceTest.class,
        MovimentacaoAcceptanceTest.class
})
public class TestRunner {
}
