package br.com.gestor.despesas.app;

import androidx.annotation.NonNull;

import java.math.BigDecimal;
import java.util.Date;

public class Despesa {
    private String id;
    private Double valor;
    private String dataEmissao;
    private String dataVencimento;
    private String descricao = "";


    public Double getValor() {
        return valor;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public String getDataEmissao() {
        return dataEmissao;
    }

    public void setDataEmissao(String dataEmissao) {
        this.dataEmissao = dataEmissao;
    }

    public String getDataVencimento() {
        return dataVencimento;
    }

    public void setDataVencimento(String dataVencimento) {
        this.dataVencimento = dataVencimento;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @NonNull
    @Override
    public String toString() {

        return "R$"+valor +"           " +dataEmissao + "          "+dataVencimento +"           "+ descricao;
    }
}
