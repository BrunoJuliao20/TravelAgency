/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agenciaviagens;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * inclui todos os clientes
 * @author ADM
 */

public class Cliente extends Utilizador implements Serializable {
    ArrayList<Reserva> reservas;
    ArrayList<Reserva> reservasEspera,vagas,viagensRealizadas,reservasCanceladas;

    /**
     *
     * @param nome
     * @param morada
     * @param email
     * @param password
     * @param nif
     * @param telefone
     * @param agencia
     * @param it
     */
    public Cliente(String nome, String morada, String email, String password, long nif, long telefone,Agencia agencia,Interface it) {
        super(nome, morada, email, password, nif, telefone,agencia,it);
        this.reservas = new ArrayList<>();
        this.reservasEspera= new ArrayList<>();
        this.reservas = new ArrayList<>();
        this.viagensRealizadas = new ArrayList<>();
        this.reservasCanceladas = new ArrayList<>();
        this.vagas = new ArrayList<>();
    }

    /**
     *
     * @return
     */
    public int consultarViagensDispon(){
        ArrayList<Viagem> viagensDisp;
        String origem,destino;
        Interface it=new Interface();

        origem=it.pedeOrigem();
        destino=it.pedeDestino();

        viagensDisp = app.viagensDispon(origem,destino);
        if(viagensDisp.size() == 0){
            it.print("*Não hà¡ viagens disponiveis");
        }
        else{
            it.print("*Viagens Disponiveis:");
            it.listaArrayViagens(viagensDisp);
        }


        return (viagensDisp.size());
    }

    /**
     *
     */
    public void reservarViagem(){
        ArrayList<Viagem> viagensDisp;
        String origem,destino;
        Viagem viagem;
        int tam,ind=-1,espera,nLugar;
        Interface it = new Interface();

        tam=consultarViagensDispon();

        if(tam > 0){
            while(!(ind>=0 && ind<tam)){
                it.print("Nº da viagem a reservar:");
                ind=it.leInteiro();
            }

            viagem=app.getViagem(ind);
            System.out.println(viagem.nReservas);

            if(viagem.verificaReservasMax()){
                espera=it.pedeListaEspera();
                if(espera==1){
                    Reserva reservaEspera = new Reserva(this,viagem,0,(float)0.0);
                    viagem.adicionarListaEspera(reservaEspera);
                    this.reservasEspera.add(reservaEspera);
                    it.print("*Adicionado á lista de Espera*");
                }
            }
            else {
                Reserva reserva = new Reserva(this,viagem,viagem.getnReservas(),viagem.getPreco());
                this.reservas.add(reserva);
                viagem.addReservas(reserva);
                it.print("*Viagem Reservada com sucesso*");
            }
        }

    }

    /**
     *
     */
    public void consultarReservas(){
        Interface it = new Interface();
        it.listaReservasCliente(this);
    }

    /**
     *
     * @param reservas
     */
    public void setReservas(ArrayList<Reserva> reservas) {
        this.reservas = reservas;
    }

    /**
     *
     * @return
     */
    public ArrayList<Reserva> getReservasEspera() {
        return reservasEspera;
    }

    /**
     *
     * @param reservasEspera
     */
    public void setReservasEspera(ArrayList<Reserva> reservasEspera) {
        this.reservasEspera = reservasEspera;
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
     * @param reserva
     */
    public void eliminaReserva(Reserva reserva){
        reservas.remove(reserva);
    }

    /**
     *
     * @param reserva
     */
    public void eliminaReservaEspera(Reserva reserva){
        reservasEspera.remove(reserva);
    }

    /**
     *
     */
    public void cancelarReserva(){
        int esc=-1;
        int ind;
        Reserva aux;
        float reembolso;
        Reserva cancelada;
        Interface it = new Interface();
        //listar todas as reservas
        it.listaReservasCliente(this);
        //escolha das reservas

        while(!(esc>=0 && esc < (reservas.size()+reservasEspera.size()))){
            it.print("Reserva a cancelar:");
            esc=it.leInteiro();
        }

        if(esc >= reservas.size()){ //SE RESERVA ESTIVER EM LISTA DE ESPERA
            ind= esc - (reservas.size());
            aux=reservasEspera.get(ind);

            aux.getViagem().eliminaReservaEspera(aux);
            reservasEspera.remove(ind);
        }
        else{
            aux=reservas.get(esc);
            reembolso=this.calculaReembolso(aux);
            aux.setValorPagamento(reembolso);
            //cancelada=aux;
            //cancelada=new Reserva(this,aux.getViagem(),aux.nLugar,reembolso);
            //adicionar Ã¡s listas de reservas canceladas
            System.out.println("canc: "+aux);
            this.reservasCanceladas.add(aux);
            aux.getViagem().adicionarListaRCanceladas(aux);

            //eliminar das listas de reservas
            this.reservas.remove(aux);
            aux.getViagem().eliminaReserva(aux);
        }
    }

    /**
     *
     * @param reserva
     * @return
     */
    public float calculaReembolso(Reserva reserva){
        float reembolso=0;
        if(reserva.getViagem().getDataPartida().daysBetween(app.getDataAtual())>=7){
            reembolso= (float) 0.5*(reserva.getValorPagamento());
            return reembolso;
        }
        else {
            reembolso=0;
        }

        return reembolso;
    }

    /**
     *
     * @param reserva
     */
    public void adicionaVaga(Reserva reserva){
        this.vagas.add(reserva);
    }

    /**
     *
     */
    public void verificaNovasVagas(){
        int i,res=0;
        Reserva vaga;


        for(i=0;i<vagas.size();i++){
            vaga=vagas.get(i);
            if(vaga.getViagem().verificaReservasMax()== false){
                it.print("Há¡ uma vaga na viagem:\n"+vaga.getViagem()+"\nPretende ficar com a vaga? (1-sim 2-não)");
                while(!(res==1)||(res==2)){
                    res=it.leInteiro();
                    vaga.getViagem().reservarVaga(vaga,this);
                }
            }
        }
    }

    /**************************************************************************/

    public void atualizaReservas(){
        int i;
        Reserva aux;
        System.out.println(app.getDataAtual());
        Interface it = new Interface();

        for (i=0;i<reservas.size();i++){
            aux=reservas.get(i);
            System.out.println(aux.getViagem().getDataPartida());
            if(app.getDataAtual().comparaData(aux.getViagem().getDataPartida())== 1){
                viagensRealizadas.add(aux);
                reservas.remove(aux);
            }
        }

        for (i=0;i<reservasEspera.size();i++){
            aux=reservasEspera.get(i);
            if(app.getDataAtual().comparaData(aux.getViagem().getDataPartida())== 1){
                reservasEspera.remove(aux);
            }
        }

        it.print("Reservas Atualizadas");
    }

    /********************COMENTARIO E PONTUAÃ‡AO VIAGENS***************************/
    public void inserirComentarioPont(){
        int ind=-1,pontuacao=0;
        String comentario;
        Interface it = new Interface();


        it.listarViagensRealizadas(viagensRealizadas);
        if(viagensRealizadas.size()>0){
            while(!(ind>=0 && ind < viagensRealizadas.size())){
                it.print("Escolha viagem que pretende comentar e pontuar:");
                ind=it.leInteiro();//escolha da viagem
            }

            Viagem v=viagensRealizadas.get(ind).getViagem();
            it.print("->"+v+"\nInsira Comentário:");
            comentario=it.leString();
            it.print("Pontuação:");
            while(!(pontuacao>0 && pontuacao<6)){
                pontuacao = it.leInteiro();
            }

            Comentario coment = new Comentario(comentario,pontuacao);

            v.insereComentario(coment);
            it.print("Comentãrio inserido com sucesso");
        }

    }

    /**
     *
     */
    public void menuInicial(){

        int op=0;
        Interface it = new Interface();
        verificaNovasVagas();
        atualizaReservas(); //Atualiza reservas para viagens realizadas

        op=it.menuCliente();

        switch (op){
            case 1:
                consultarViagensDispon();
                menuInicial();
                break;
            case 2:
                reservarViagem();
                menuInicial();
                break;
            case 3:
                consultarReservas();
                menuInicial();
                break;
            case 4:
                cancelarReserva();
                menuInicial();
                break;
            case 5:

                inserirComentarioPont();
                menuInicial();
                break;
        }
    }

    public String toString(){
        return ("(Cliente) Nome: " +nome+" | Morada: "+morada+" | Email: "+email+" | NIF: "+nif+" | Telefone: "+telefone);
    }
}
