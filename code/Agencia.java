/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agenciaviagens;

import java.io.EOFException;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * classe de uma aplicaçao de uma agencia de autocarros
 * @author ADM
 */

public class Agencia implements Serializable{

    private static ArrayList<Autocarro> autocarros;
    private static ArrayList<Viagem> viagens;
    private static ArrayList<Utilizador> utilizadores;
    private static Data dataAtual;
    private static Interface it;

    /**
     *
     */
    public Agencia(){

    }

    /**
     *
     * @param args
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public static void main(String args[]) throws IOException, ClassNotFoundException {
        int ind=-1,v=0;
        Utilizador user;
        it = new Interface();
        dataAtual = initDataAtual();
        autocarros = new ArrayList<>();
        utilizadores = new ArrayList<>();
        viagens = new ArrayList<>();
        Agencia app = new Agencia();

        Administrador admin = new Administrador("admin","coimbra","admin@hotmail.com","123456789",123456789,239914123,app,it);

        try{
            inicializaDados(utilizadores,viagens,autocarros,admin);
        }

        catch (IOException e){
            it.print("Erro a inicializar dados: "+e);
        }

        it.print(""+dataAtual);

        listaUtilizadores(admin);

        while(ind == -1){
            ind=login();
        }

        user=utilizadores.get(ind);
        user.menuInicial();

        try {
            guardaDados();
            it.print("*Dados guardados, boas viagens!!!*");
        }
        catch (IOException e){
            it.print("Erro a guardar dados -> "+e);
        }

    }

    /**
     *
     * @return
     */
    public static int login(){
        Interface it=new Interface();
        int i;
        it.print("\nLOGIN");
        long nif;
        for(i=0;i<utilizadores.size();i++){
        	it.print(utilizadores.get(i).getPassword());   
        }
        nif=it.pedeNif();
        String password = it.pedePassword();

        for(i=0;i<utilizadores.size();i++){
            if(utilizadores.get(i).getNif() == nif && utilizadores.get(i).getPassword().equals(password)){
                return i;
            }
        }

        it.print("\nLogin Falhou, nif ou password incorretos");
        return -1;
    }

    /**
     *
     * @return
     */
    public static Data initDataAtual(){

        int ano,mes,dia,hora,min;
        ano = Calendar.getInstance().get(Calendar.YEAR);
        mes = 1+Calendar.getInstance().get(Calendar.MONTH);
        dia = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
        hora = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
        min = Calendar.getInstance().get(Calendar.MINUTE);

        Data dataAtual = new Data(dia,mes,ano,hora,min);

        return dataAtual;
    }

    /**
     *
     * @return
     */
    public static Data getDataAtual(){
        return dataAtual;
    }

    /**
     *
     * @param utilizadores
     * @param viagens
     * @param autocarros
     * @param adminDefault
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public static void inicializaDados(ArrayList<Utilizador> utilizadores,ArrayList<Viagem> viagens, ArrayList<Autocarro> autocarros,Administrador adminDefault) throws IOException, ClassNotFoundException {

        Data dataAtual = initDataAtual();

        initAutocarros(autocarros,"autocarros1.txt");
        initUtilizadores(utilizadores,"utilizadores1.txt",adminDefault);
        initViagens(viagens,"viagens1.txt");

    }

    /**
     *
     * @param autocarros
     * @param nomeFicheiro
     * @return
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public static boolean initAutocarros(ArrayList<Autocarro> autocarros,String nomeFicheiro) throws IOException, ClassNotFoundException {
        Autocarro aux;
        boolean fimLista=false;
        FicheiroObj fich = new FicheiroObj();
        Interface it = new Interface();

        try{
            if(fich.exists(nomeFicheiro)){

                fich.abreLeitura(nomeFicheiro);
                while(fimLista == false){
                    aux=(Autocarro) fich.leObjecto();
                    autocarros.add(aux);
                }
                fich.fechaLeitura();
            }

        }
        catch(EOFException e){
            fimLista=true;
            it.print("fim da lista---debug");
        }
        catch(IOException e){
            it.print("Erro a carregar autocarros ("+e+")");
            return false;
        }
        return true;

    }

    /**
     *
     * @param viagens
     * @param nomeFicheiro
     * @return
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public static boolean initViagens(ArrayList<Viagem> viagens,String nomeFicheiro) throws IOException, ClassNotFoundException {
        Viagem aux;
        boolean fimLista=false;
        FicheiroObj fich = new FicheiroObj();
        Interface it = new Interface();

        try{
            if(fich.exists(nomeFicheiro)){

                fich.abreLeitura(nomeFicheiro);
                while(fimLista == false){
                    aux=(Viagem) fich.leObjecto();
                    viagens.add(aux);
                }
                fich.fechaLeitura();
            }

        }
        catch(EOFException e){
            fimLista=true;
            it.print("fim da lista---debug");
        }
        catch(IOException e){
            it.print("Erro a carregar viagens ("+e+")");
            return false;
        }
        return true;
    }

    /**
     *
     * @param utilizadores
     * @param nomeFicheiro
     * @param adminDefault
     * @return
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public static boolean initUtilizadores(ArrayList<Utilizador> utilizadores,String nomeFicheiro,Administrador adminDefault) throws IOException, ClassNotFoundException {
        Utilizador aux;
        boolean fimLista=false;
        FicheiroObj fich = new FicheiroObj();
        Interface it = new Interface();

        try{
            if(fich.exists(nomeFicheiro)){

                fich.abreLeitura(nomeFicheiro);
                while(fimLista == false){
                    aux=(Utilizador) fich.leObjecto();
                    utilizadores.add(aux);
                }

                fich.fechaLeitura();
            }
            else{
                adicionaUtilizador(adminDefault,adminDefault);
            }

        }
        catch(EOFException e){
            fimLista=true;
            it.print("fim da lista---debug");
        }
        catch(IOException e){
            it.print("Erro a carregar viagens ("+e+")");
            return false;
        }
        return true;
    }

    /***********************************GUARDAR DADOS
     * @throws java.io.IOException*********************************************/
    public static void guardaDados() throws IOException {
        guardaAutocarros();
        guardaUtilizadores();
        guardaViagens();
    }

    /**
     *
     * @throws IOException
     */
    public static void guardaAutocarros() throws IOException {
        int i;
        FicheiroObj f = new FicheiroObj();

        f.abreEscrita("autocarros1.txt");
        for(i=0;i<autocarros.size();i++){
            f.escreveObjecto(autocarros.get(i));
        }
        f.fechaEscrita();
    }

    /**
     *
     * @throws IOException
     */
    public static void guardaUtilizadores() throws IOException {
        int i;
        FicheiroObj f = new FicheiroObj();

        f.abreEscrita("utilizadores1.txt");

        for(i=0;i<utilizadores.size();i++){
            f.escreveObjecto(utilizadores.get(i));
        }
        //f.escreveArray(utilizadores);

        f.fechaEscrita();
    }

    /**
     *
     * @throws IOException
     */
    public static void guardaViagens() throws IOException {
        int i;
        FicheiroObj f = new FicheiroObj();

        f.abreEscrita("viagens1.txt");
        for(i=0;i<viagens.size();i++){
            f.escreveObjecto(viagens.get(i));
        }
        f.fechaEscrita();

    }
    /******************************UTILIZADORES*************************************/

    /**
     * UTILIZADORES
     * @param u
     * @param admin
     */
    public static void adicionaUtilizador(Utilizador u, Administrador admin){
        Interface it = new Interface();
        utilizadores.add(u);
        it.print("*Adicionado com sucesso!*");
    }

    /**
     *
     * @param u
     * @param admin
     */
    public static void eliminaUtilizador(Utilizador u, Administrador admin){
        Interface it = new Interface();

        if(utilizadores.remove(u)){
            it.print("*Utilizador removido com Sucesso!*");
        }
        else{
            it.print("*Erro a eliminar Utilizador*");
        }
    }

    /**
     *
     * @param administrador
     */
    public static void listaUtilizadores(Administrador administrador){
        int i;
        for(i=0;i<utilizadores.size();i++){
            System.out.println(i+"- "+utilizadores.get(i));
        }

    }

    /**
     *
     * @param nif
     * @param admin
     * @return
     */
    public static int getIndCliente(long nif,Utilizador admin){
        int i;
        System.out.println("*Entrei!*");
        for(i = 0;i<utilizadores.size();i++){
            if(utilizadores.get(i).getNif() == nif){
                System.out.println(i);
                return i;
            }
        }
        return -1;
    }

    /**
     *
     * @param indice
     * @return
     */
    public static Utilizador getUtilizador(int indice){
        return utilizadores.get(indice);
    }
    /******************************AUTOCARRO
     * @param a*
     * @param admin***********************************/
    public static void adicionaAutocarro(Autocarro a, Administrador admin){
        Interface it = new Interface();
        autocarros.add(a);
        it.print("*Adicionado com sucesso!*");
    }

    /**
     *
     * @param a
     * @param admin
     */
    public static void eliminaAutocarro(Autocarro a, Administrador admin){
        Interface it = new Interface();
        if(autocarros.remove(a)){
            it.print("*Autocarro removido com Sucesso!*");
        }
        else{
            it.print("*Erro a eliminar Autocarro*");
        }
    }

    /**
     *
     */
    public static void listaAutocarros(){
        int i;
        Interface it = new Interface();

        if(autocarros.isEmpty()){
            it.print("*Não existem autocarros.");
        }
        for(i=0;i<autocarros.size();i++){
            System.out.println(i+"- "+autocarros.get(i));
        }
    }

    /**
     *
     * @param matricula
     * @param administrador
     * @return
     */
    public static int getIndAutocarro(String matricula,Administrador administrador){
        int i;

        for(i=0;i<autocarros.size();i++){
            //System.out.println("Matricula i"+autocarros.get(i).getMatricula() +"compara com: "+matricula);
            if(autocarros.get(i).getMatricula().equals(matricula)){

                return i;
            }
        }

        return -1;
    }

    /**
     *
     * @param dataPartida
     * @param dataChegada
     * @param admin
     * @return
     */
    public static ArrayList<Autocarro> getAutocarrosDisponiveis(Data dataPartida,Data dataChegada,Administrador admin) {
        ArrayList<Autocarro> autoDisponiveis = new ArrayList<>();
        int i;

        for(i=0;i<autocarros.size();i++){
            if(autocarros.get(i).verificaDisponibilidade(dataPartida,dataChegada)== 1){
                autoDisponiveis.add(autocarros.get(i));
            }
        }
        return autoDisponiveis;
    }

    /**
     *
     * @param indice
     * @return
     */
    public static Autocarro getAutocarro (int indice){
        return autocarros.get(indice);
    }

    /******************************VIAGE
     * @param v*
     * @param admin**********************************/
    public static void adicionaViagens(Viagem v, Administrador admin){
        Interface it = new Interface();
        viagens.add(v);
        it.print("*Adicionado com sucesso!*");
    }

    /**
     *
     * @param v
     * @param admin
     */
    public static void eliminaViagem(Viagem v, Administrador admin){
        Interface it = new Interface();

        if(viagens.remove(v)){
            v.libertaAutocarros();
            it.print("*Viagem removida com Sucesso!*");
        }
        else{
            it.print("*Erro a eliminar Viagem*");
        }
    }

    /**
     *
     */
    public static void listaViagens(){
        int i;
        Interface it = new Interface();

        if(viagens.isEmpty()){
            it.print("*Não existem viagens.*");
        }
        for(i=0;i<viagens.size();i++){
            System.out.println(i+"- "+viagens.get(i));
        }
    }

    /**
     *
     * @param codigo
     * @param administrador
     * @return
     */
    public static int getIndViagem(String codigo,Administrador administrador){
        int i;

        for(i=0;i<viagens.size();i++){
            if(viagens.get(i).getCodigo().equals(codigo)){
                return i;
            }
        }
        return -1;
    }

    /**
     *
     * @param indice
     * @return
     */
    public static Viagem getViagem (int indice){
        return viagens.get(indice);
    }

    /**
     *
     * @param origem
     * @param destino
     * @return
     */
    public static ArrayList<Viagem> viagensDispon(String origem,String destino){
        int i;
        ArrayList<Viagem> viagensDisp = new ArrayList<>();
        Viagem aux;
        for(i=0;i<viagens.size();i++){
            aux=viagens.get(i);
            if(aux.getOrigem().equals(origem) && aux.getDestino().equals(destino) && aux.getDataPartida().comparaData(dataAtual)==1){
                viagensDisp.add(aux);
            }
        }

        return viagensDisp;
    }

}
