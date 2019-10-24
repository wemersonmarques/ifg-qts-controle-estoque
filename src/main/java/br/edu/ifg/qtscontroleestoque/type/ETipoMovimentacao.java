package br.edu.ifg.qtscontroleestoque.type;

public enum ETipoMovimentacao {
    ENTRADA("Operação que adiciona saldo"), SAIDA("Operacação que subtrai saldo");

    private int codigo;
    private String descricao;

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    ETipoMovimentacao(String descricao) {
        this.descricao = descricao;
    }


}
