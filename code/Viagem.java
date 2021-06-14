/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agenciaviagens;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author ADM
 */
public class Viagem implements Serializable {

    String codigo,origem,destino;
    float mediaPontuacao,preco;
    int duracao,nReservas,maxReservas;
    Data dataPartida;
    Data dataChegada;
    ArrayList<Autocarro> autocarrosUtilizados;
    ArrayList<Reserva> reservas,reservasEspera,reservasCanceladas;
    ArrayList<Comentario> comentarios;

    /**
     *
     * @param codigo
     * @param origem
     * @param destino
     * @param preco
     * @param dataPartida
     * @param dataChegada
     * @param duracao
     * @param autocarrosUtilizados
     */
    public Viagem(String codigo, String origem, String destino, float preco, Data dataPartida,Data dataChegada,int duracao, ArrayList<Autocarro> autocarrosUtilizados) {
        this.codigo = codigo;
        this.origem = origem;
        this.destino = destino;
        this.preco = preco;
        this.dataPartida = dataPartida;
        this.dataChegada = dataChegada;
        this.autocarrosUtilizados = autocarrosUtilizados;
        this.duracao = duracao;
        this.maxReservas=getMaxReservas();
        this.reservas = new ArrayList<>();
        this.reservasEspera = new ArrayList<>();
        this.reservasCanceladas = new ArrayList<>();
        this.comentarios = new ArrayList<>();
    }

    /**
     *
     * @return
     */
    public int getDuracao() {
        return this.duracao;
    }

    /**
     *
     * @param duracao
     */
    public void setDuracao(int duracao) {
        this.duracao = duracao;
    }

    /**
     *
     * @return
     */
    public String getCodigo() {
        return this.codigo;
    }

    /**
     *
     * @param codigo
     */
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    /**
     *
     * @return
     */
    public String getOrigem() {
        return this.origem;
    }

    /**
     *
     * @param origem
     */
    public void setOrigem(String origem) {
        this.origem = origem;
    }

    /**
     *
     * @return
     */
    public String getDestino() {
        return this.destino;
    }

    /**
     *
     * @param destino
     */
    public void setDestino(String destino) {
        this.destino = destino;
    }

    /**
     *
     * @return
     */
    public float getPreco() {
        return this.preco;
    }

    /**
     *
     * @param preco
     */
    public void setPreco(float preco) {
        this.preco = preco;
    }

    /**
     *
     * @return
     */
    public Data getDataPartida() {
        return this.dataPartida;
    }

    /**
     *
     * @param dataPartida
     */
    public void setDataPartida(Data dataPartida) {
        this.dataPartida = dataPartida;
    }

    /**
     *
     * @return
     */
    public ArrayList<Autocarro> getAutocarrosUtilizados() {
        return this.autocarrosUtilizados;
    }

    /**
     *
     * @param autocarrosUtilizados
     */
    public void setAutocarrosUtilizados(ArrayList<Autocarro> autocarrosUtilizados) {
        this.autocarrosUtilizados = autocarrosUtilizados;
    }

    /**
     *
     * @return
     */
    public ArrayList<Reserva> getReservas() {
        return reservas;
    }

    /**
     *
     * @param reservas
     */
    public void addReservas(Reserva reservas) {
        this.nReservas++;
        this.reservas.add(reservas);
    }

    /**
     *
     * @return
     */
    public int getnReservas(){
        return this.nReservas;
    }

    /**
     *
     * @return
     */
    public Data getDataChegada(){
        return this.dataChegada;
    }

    /**
     *
     * @param dataChegada
     */
    public void setDataChegada(Data dataChegada) {
        this.dataChegada = dataChegada;
    }

    /**
     *
     * @return
     */
    public int getMaxReservas(){
        int min,temp,i;

        min=this.autocarrosUtilizados.get(0).getLotacao();

        for(i=0;i<this.autocarrosUtilizados.size();i++){
            temp=this.autocarrosUtilizados.get(i).getLotacao();
            if(temp<min){
                min=temp;
            }
        }

        return min;
    }

    /**
     *
     * @param reserva
     */
    public void adicionarListaEspera(Reserva reserva){
        this.reservasEspera.add(reserva);
        //it.print("Reserva adicionada Ã  lista de Espera com sucesso!");
    }

    /**
     *
     * @param reserva
     */
    public void adicionarListaRCanceladas(Reserva reserva){
        this.reservasCanceladas.add(reserva);
    }

    /**
     *
     * @param reserva
     */
    public void eliminaReservaEspera(Reserva reserva){
        this.reservasEspera.remove(reserva);
    }

    /**
     *
     * @param reserva
     */
    public void eliminaReserva(Reserva reserva){
        if(this.nReservas > 0){
            this.nReservas--;
            if(this.reservasEspera.size()> 0){  //ve se tem reservas na lista de espera
                System.out.println("**NOTIFICAR**");
                notificaReservasEspera(reserva);

                this.reservas.remove(reserva);
            }
            else{
                this.reservas.remove(reserva);
            }
        }

        else{
            System.out.println("*Não existem reservas");
        }
    }

    /**
     *
     * @param reserva
     */
    public void notificaReservasEspera(Reserva reserva){
        //notifica todos os clientes em espera que hÃ¡ uma vaga
        int i;
        Cliente aux;

        for(i=0;i<this.reservasEspera.size();i++){
            aux=this.reservasEspera.get(i).getCliente();
            aux.adicionaVaga(reserva);
            System.out.println("(NOTIFICA)Cliente:"+aux+"Reserva"+reserva);
        }
    }

    /**
     *
     * @return
     */
    public boolean verificaReservasMax(){
        if(this.maxReservas == this.nReservas){
            return true;
        }
        return false;
    }

    /**
     *
     * @param reserva
     * @param cliente
     */
    public void reservarVaga(Reserva reserva,Cliente cliente){
        float valorPagamento=reserva.getValorPagamento();

        //atualiza valor do pagamento caso os tipos de cliente sejam diferentes
        if(reserva.getCliente() instanceof Premium && cliente instanceof Cliente){
            valorPagamento=reserva.getValorPagamento()/(float) 0.9;
        }
        if(reserva.getCliente() instanceof Cliente && cliente instanceof Premium){
            valorPagamento=reserva.getValorPagamento()*(float) 0.9;
        }

        reserva.setCliente(cliente);
        reserva.setValorPagamento(valorPagamento);
    }

    /**
     *
     * @param comentario
     */
    public void insereComentario(Comentario comentario){
        this.comentarios.add(comentario);

    }

    /**
     *
     */
    public void calculaMediaPontuacao(){
        int soma=0,i;

        for(i=0;i<this.comentarios.size();i++){
            soma=soma+this.comentarios.get(i).getPontuacao();
        }
        this.mediaPontuacao=soma/this.comentarios.size();
    }

    /**
     *
     */
    public void libertaAutocarros(){
        int i;
        for(i=0;i<this.autocarrosUtilizados.size();i++){
            this.autocarrosUtilizados.get(i).eliminaViagem(this);
        }
    }

    public String toString(){
        return ("(Viagem) Código: "+this.codigo+" Data Partida: "+this.dataPartida+ " Data Chegada: "+this.dataChegada+" Origem: "+this.origem+" Destino: "+this.destino+" Duração: "+this.duracao+" Preço: "+(float)this.preco);
    }

}