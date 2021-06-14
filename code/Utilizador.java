/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agenciaviagens;

import java.io.Serializable;

/**
 * inclui os admins e todos os clientes que usam a agencia
 * @author ADM
 */

public abstract class Utilizador implements Serializable {

    protected String nome;
    protected String morada;
    protected String email;
    protected String password;
    protected long nif,telefone;
    protected Agencia app;
    protected Interface it;

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
    public Utilizador(String nome, String morada, String email, String password, long nif, long telefone,Agencia app,Interface it) {
        this.nome = nome;
        this.morada = morada;
        this.email = email;
        this.password = password;
        this.nif = nif;
        this.telefone = telefone;
        this.app = app;
        this.it = it;
    }

    /**
     *
     * @return
     */
    public String getNome() {
        return nome;
    }

    /**
     *
     * @param nome
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     *
     * @return
     */
    public String getMorada() {
        return morada;
    }

    /**
     *
     * @param morada
     */
    public void setMorada(String morada) {
        this.morada = morada;
    }

    /**
     *
     * @return
     */
    public String getEmail() {
        return email;
    }

    /**
     *
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     *
     * @return
     */
    public long getNif() {
        return nif;
    }

    /**
     *
     * @param nif
     */
    public void setNif(long nif) {
        this.nif = nif;
    }

    /**
     *
     * @return
     */
    public long getTelefone() {
        return telefone;
    }

    /**
     *
     * @param telefone
     */
    public void setTelefone(long telefone) {
        this.telefone = telefone;
    }

    /**
     *
     * @return
     */
    public String getPassword() {
        return password;
    }

    /**
     *
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }
    /********************************************/

    /**
     *
     */
    public void menuInicial(){
    }
}