/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agenciaviagens;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * cliente com vantagens
 * @author ADM
 */
public class Premium extends Cliente implements Serializable {
    ArrayList<Reserva> reservas;

    /**
     *
     * @param nome
     * @param morada
     * @param email
     * @param password
     * @param nif
     * @param telefone
     * @param app
     * @param it
     */
    public Premium(String nome, String morada, String email, String password, long nif, long telefone,Agencia app,Interface it) {
        super(nome, morada, email, password, nif, telefone,app,it);
        reservas = new ArrayList<>();
    }

    /**
     *
     * @param reserva
     * @return
     */
    public float calculaReembolso(Reserva reserva){
        float reembolso=0;
        if(reserva.getViagem().getDataPartida().daysBetween(app.getDataAtual())>=2){
            reembolso= reserva.getValorPagamento();
            return reembolso;
        }
        else {
            reembolso=0;
        }
        return reembolso;
    }
}
