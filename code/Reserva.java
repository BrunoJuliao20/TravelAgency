/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agenciaviagens;

import java.io.Serializable;

/**
 * classe onde o cliente pode reservas as viagens
 * @author ADM
 */

public class Reserva implements Serializable {
    Cliente cliente;
    Viagem viagem;
    Autocarro autocarro;
    int nLugar;
    float valorPagamento;

    /**
     *
     * @param cliente
     * @param viagem
     * @param nLugar
     * @param valorPagamento
     */
    public Reserva(Cliente cliente, Viagem viagem, int nLugar, float valorPagamento) {
        this.cliente = cliente;
        this.viagem = viagem;
        this.nLugar = nLugar;
        this.valorPagamento = valorPagamento;
    }

    /**
     *
     * @return
     */
    public Cliente getCliente() {
        return cliente;
    }

    /**
     *
     * @param cliente
     */
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    /**
     *
     * @return
     */
    public Viagem getViagem() {
        return viagem;
    }

    /**
     *
     * @return
     */
    public String getCodigoViagem(){
        return viagem.getCodigo();
    }

    /**
     *
     * @param viagem
     */
    public void setViagem(Viagem viagem) {
        this.viagem = viagem;
    }

    /**
     *
     * @return
     */
    public Autocarro getAutocarro() {
        return autocarro;
    }

    /**
     *
     * @param autocarro
     */
    public void setAutocarro(Autocarro autocarro) {
        this.autocarro = autocarro;
    }

    /**
     *
     * @return
     */
    public int getnLugar() {
        return nLugar;
    }

    /**
     *
     * @param nLugar
     */
    public void setnLugar(int nLugar) {
        this.nLugar = nLugar;
    }

    /**
     *
     * @return
     */
    public float getValorPagamento() {
        return valorPagamento;
    }

    /**
     *
     * @param valorPagamento
     */
    public void setValorPagamento(float valorPagamento) {
        this.valorPagamento = valorPagamento;
    }


    @Override
    public String toString() {
        return "(Reserva) Viagem:" + viagem + " NÂº Lugar=" + nLugar + "Valor Pagamento: " + valorPagamento;
    }
}
