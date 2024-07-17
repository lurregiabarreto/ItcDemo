package com.lurregia.demo.model;


public class ITCRecord {
    private String tipoCartao;
    private String credito;
    private String debito;
    private String prePago;
    private String tipoPessoa;
    private String cpCnpVbv;
    private String mcc;
    private String produto;
    private String parcelamento;

    public String getCredito() {
        return credito;
    }

    public void setCredito(String credito) {
        this.credito = credito;
    }

    public String getDebito() {
        return debito;
    }

    public void setDebito(String debito) {
        this.debito = debito;
    }

    public String getPrePago() {
        return prePago;
    }

    public void setPrePago(String prePago) {
        this.prePago = prePago;
    }

    public String getRecorrente() {
        return recorrente;
    }

    public void setRecorrente(String recorrente) {
        this.recorrente = recorrente;
    }

    private String recorrente;

    public String getTipoCartao() {
        return tipoCartao;
    }

    public void setTipoCartao(String tipoCartao) {
        this.tipoCartao = tipoCartao;
    }

    public String getTipoPessoa() {
        return tipoPessoa;
    }

    public void setTipoPessoa(String tipoPessoa) {
        this.tipoPessoa = tipoPessoa;
    }

    public String getCpCnpVbv() {
        return cpCnpVbv;
    }

    public void setCpCnpVbv(String cpCnpVbv) {
        this.cpCnpVbv = cpCnpVbv;
    }

    public String getMcc() {
        return mcc;
    }

    public void setMcc(String mcc) {
        this.mcc = mcc;
    }

    public String getProduto() {
        return produto;
    }

    public void setProduto(String produto) {
        this.produto = produto;
    }

    public String getParcelamento() {
        return parcelamento;
    }

    public void setParcelamento(String parcelamento) {
        this.parcelamento = parcelamento;
    }

    public double getTaxa() {
        return taxa;
    }

    public void setTaxa(double taxa) {
        this.taxa = taxa;
    }

    public double getTeto() {
        return teto;
    }

    public void setTeto(double teto) {
        this.teto = teto;
    }

    private double taxa;
    private double teto;


}
