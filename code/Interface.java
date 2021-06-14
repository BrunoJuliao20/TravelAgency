/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agenciaviagens;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * classe de interaçao utilizador-agencia
 * @author ADM
 */

public class Interface implements Serializable{

    /**
     *
     * @return
     */
    public String leString(){
        String s;
        int check=1;
        Scanner sc = new Scanner(System.in);

        s=sc.nextLine();

        return s;
    }

    /**
     *
     * @return
     */
    public int leInteiro(){
        int i=0,check=1;
        Scanner sc = new Scanner(System.in);

        while(check==1){
            try{
                i=sc.nextInt();
                check=0;
            }
            catch (InputMismatchException exception){
                print("*Erro! Inteiros apenas!");
                sc.next();
            }
        }
        return i;
    }

    /**
     *
     * @return
     */
    public float leFloat(){
        float f = (float)0.0;
        int check=1;
        Scanner sc = new Scanner(System.in);

        while(check==1){
            try{
                f=sc.nextFloat();
                check=0;
            }
            catch (InputMismatchException exception){
                print("*Erro! Float apenas!");
                sc.next();
            }
        }
        return f;
    }

    /**
     *
     * @return
     */
    public long leLong(){
        long l=0;
        int check=1;
        Scanner sc = new Scanner(System.in);

        while(check==1){
            try{
                l=sc.nextLong();
                check=0;
            }
            catch (InputMismatchException exception){
                print("*Erro! Long apenas!");
                sc.next();
            }
        }
        return l;
    }
    
    /**
     * UTILIZADORES
     * @return
     */
    public String pedeNome(){
        String nome;
        System.out.println("Nome:");
        nome=leString();
        return nome;
    }

    /**
     *
     * @return
     */
    public String pedeMorada(){
        String morada;
        print("Morada:");
        morada=leString();
        return morada;
    }

    /**
     *
     * @return
     */
    public String pedeEmail(){
        String email;
        print("Email:");
        email=leString();
        while(!email.contains("@")){
            print("*Erro! Email incorreto(tem de conter @)*");
            email=leString();
        }
        return email;
    }

    /**
     *
     * @return
     */
    public String pedePassword(){
        String pass;
        print("Password:");
        pass=leString();
        return pass;
    }

    /**
     *
     * @return
     */
    public long pedeNif(){
        long nif;
        String snif="";
        print("NIF:");
        nif=leLong();

        while(String.valueOf(nif).length()!=9){
            print("Tem de conter 9 digitos!");
            nif=leLong();
        }

        return nif;
    }

    /**
     *
     * @return
     */
    public long pedeTelefone(){
        long telefone;
        String snif="";
        System.out.println("Telefone:");

        telefone=leLong();
        while(String.valueOf(telefone).length()!=9){
            print("Tem de conter 9 digitos");
            telefone=leLong();
        }
        return telefone;
    }

    /**
     *
     * @return
     */
    public int escolhaAlteraCliente(){
        int escolha=0;
        while(!(escolha>0 && escolha<7)){
            System.out.println("Escolha o que pretende alterar:\n1-Nome\n2-Morada\n3-Email\n4-Password\n5-NIF\n6-Telefone");
            escolha = leInteiro();
        }
        return escolha;
    }

    /**
     *
     * @return
     */
    public int escolhaTipoCliente(){
        int e=0;
        while(!(e==1 || e==2)){
            System.out.println("Tipo de Cliente:\n1-Regular\n2-Premium");
            e=leInteiro();
        }
        return e;
    }


    /**
     * AUTOCARROS
     * @return
     */
    public String pedeMatricula(){
        String mat;
        System.out.println("Matricula:");
        mat=leString();
        return mat;
    }

    /**
     *
     * @return
     */
    public int pedeLotacao(){
        int lot;
        System.out.println("Lotação:");
        lot=leInteiro();
        return lot;
    }

    /**
     *
     * @return
     */
    public int escolhaAlteraAutocarro(){
        int escolha=0;
        while(!(escolha>0 && escolha<3)){
            System.out.println("Escolha o que pretende alterar:\n1-Matricula\n2-Lotação\n");
            escolha = leInteiro();
        }
        return escolha;
    }
    
    /**
     * VIAGENS
     * @return
     */
    public String pedeCodigo(){
        String codigo;
        print("Codigo: ");
        codigo = leString();
        return codigo;
    }

    /**
     *
     * @return
     */
    public String pedeOrigem(){
        String origem;
        System.out.println("Origem: ");
        origem = leString();
        return origem;
    }

    /**
     *
     * @return
     */
    public String pedeDestino(){
        String destino;
        System.out.println("Destino: ");
        destino = leString();
        return destino;
    }

    /**
     *
     * @return
     */
    public float pedePreco(){
        float preco;
        System.out.println("Preço: ");
        preco = leFloat();
        return preco;
    }

    /**
    * DATAS
    * @return
    */
    public int pedeAno(){
        int ano;
        System.out.println("Ano: ");
        ano = leInteiro();
        return ano;
    }

    /**
     *
     * @return
     */
    public int pedeMes(){
        int mes;
        System.out.println("Mes: ");
        mes = leInteiro();
        return mes;
    }

    /**
     *
     * @return
     */
    public int pedeDia(){
        int dia;
        System.out.println("Dia: ");
        dia = leInteiro();
        return dia;
    }

    /**
     *
     * @return
     */
    public int pedeHora(){
        int hora;
        System.out.println("Hora: ");
        hora = leInteiro();
        return hora;
    }

    /**
     *
     * @return
     */
    public int pedeMin(){
        int min;
        System.out.println("Min: ");
        min = leInteiro();
        return min;
    }

    /**
     *
     * @return
     */
    public Data pedeData(){
        Data data = new Data(pedeDia(),pedeMes(),pedeAno(),pedeHora(),pedeMin());
        return data;
    }

    /**
     *
     * @return
     */
    public int pedeDuracao(){
        int dur;
        System.out.println("Duração(em minutos): ");
        dur = leInteiro();
        return dur;
    }

    /**
     *
     * @return
     */
    public int escolhaAlteraViagem(){
        int escolha=0;
        while(!(escolha>0 && escolha<8)){
            System.out.println("Escolha o que pretende alterar:\n1-Código\n2-Origem\n3-Destino\n4-Preço\n5-Data Partida\n6-Duração\n7-Autocarros Utilizados\n");
            escolha = leInteiro();
        }
        return escolha;
    }

    /**
     *
     * @param autocarrosDisponiveis
     * @return
     */
    public ArrayList<Autocarro> escolhaAutocarrosPViagem(ArrayList<Autocarro> autocarrosDisponiveis){
        int i,esc;
        Autocarro aux;
        ArrayList<Autocarro> autoUtilizados= new ArrayList<>();

        for(i=0;i<autocarrosDisponiveis.size();i++){
            aux=autocarrosDisponiveis.get(i);
            print(i+"-> "+aux);
        }
        print("Escolha os autocarros a serem utilizados(inserir -1 no fim da seleção)");
        esc=leInteiro();

        while(esc != -1){
            if(esc < autocarrosDisponiveis.size()){
                autoUtilizados.add(autocarrosDisponiveis.get(esc));
                print("*Autocarro adicionado á  viagem.*");
            }
            else{
                print("Escolha invalida. Tente de novo.");
            }
            esc=leInteiro();
        }
        return autoUtilizados;

    }

    /**
     *
     * @param viagens
     */
    public void listaArrayViagens(ArrayList<Viagem> viagens){
        int i;
        for(i=0;i<viagens.size();i++){
            print(i+"-"+viagens.get(i));
        }
    }

    /**
     *
     * @param reservas
     */
    public void listarViagensRealizadas(ArrayList<Reserva> reservas){
        int i;

        if(reservas.size() > 0){
            for(i=0;i<reservas.size();i++){
                print(i+"-"+reservas.get(i));
            }
        }
        else{
            print("*Não tem viagens Realizadas.");
        }

    }

    /**
     * RESERVAS
     * @return
     */
    public int pedeListaEspera(){
        int res=0;
        System.out.println("A Viagem atingiu o numero limite de reservas. Pretende ficar em lista de espera?(1-sim 2-nÃ£o)");
        while(!(res==1 || res==2)){
            res=leInteiro();
        }
        return res;
    }

    /**
     *
     * @param cliente
     */
    public void listaReservasCliente(Cliente cliente){
        int i;
        System.out.println("-------------------Reservas---------------------");
        for(i=0;i<cliente.getReservas().size();i++){
            print(i+"->"+cliente.getReservas().get(i));
        }
        System.out.println("----------Reservas em Lista de Espera-----------");

        for(i=0;i<cliente.getReservasEspera().size();i++){
            print((i+cliente.getReservas().size())+"->"+cliente.getReservasEspera().get(i));
        }
    }

    /**
     *
     * @param reserva
     * @return
     */
    public int notificacao(Reserva reserva){
        int res=-1;
        System.out.println("**NOVA VAGA DISPONÃ�VEL!\n"+reserva.getViagem()+"\nPretende Reservar?(0-não 1-sim )");
        while(!(res==1 || res ==0)){
            res=leInteiro();
        }

        return res;
    }

    /**
     *
     * @param str
     */
    public void print(String str){
        System.out.println(str);
    }

    /**
     *
     * @return
     */
    public int menuAdmin(){
        int op=0;
        System.out.println("---------BEM VINDO---------");
        System.out.println("Escolha uma opção:\n1-Gerir Clientes\n2-Gerir Viagens\n3-Gerir Autocarros\n4-Estatisticas\n5-Criar Administrador\n6-Sair da Aplicação");
        op=leInteiro();
        while(!(op>0 && op<7)){
            System.out.println("Opção inválida! (1-5)");
            op=leInteiro();
        }
        return op;
    }

    /**
     *
     * @return
     */
    public int menuGerirClientes(){
        int op=0;
        System.out.println("------GERIR CLIENTES------");
        System.out.println("Escolha uma op��o:\n1-Criar Cliente\n2-Eliminar Clientes\n3-Alterar Cliente\n4-Listar Clientes\n5-Voltar atrás");
        op=leInteiro();
        while(!(op>0 && op<6)){
            System.out.println("Op��o inv�lida! (1-5)");
            op=leInteiro();
        }
        return op;
    }

    /**
     *
     * @return
     */
    public int menuGerirViagens(){
        int op=0;
        System.out.println("------GERIR VIAGENS------");
        System.out.println("Escolha uma op��o:\n1-Criar Viagem\n2-Eliminar Viagem\n3-Alterar Viagem\n4-Listar Viagens\n5-Voltar atrás");
        op=leInteiro();
        while(!(op>0 && op<6)){
            System.out.println("Op��o inv�lida! (1-5)");
            op=leInteiro();
        }
        return op;
    }

    /**
     *
     * @return
     */
    public int menuGerirAutocarros(){
        int op=0;
        System.out.println("------GERIR AUTOCARROS------");
        System.out.println("Escolha uma op��o:\n1-Criar Autocarro\n2-Eliminar Autocarro\n3-Alterar Autocarro\n4-Listar Autocarros\n5-Voltar atrás");
        op=leInteiro();
        while(!(op>0 && op<6)){
            System.out.println("Op��o inv�lida! (1-5)");
            op=leInteiro();
        }
        return op;
    }

    /**
     *
     * @return
     */
    public int menuEstatisticas(){
        int op=0;
        System.out.println("------ESTATISTICAS------");
        System.out.println("Consultar:\n1-Viagem mais vendida num mes\n2-Cliente com mais viagens compradas\n3-Listar viagens sem reservas num mes\n4-Listar Reservas da viagem\n5-Listar reservas canceladas de uma viagem\n6-Listar as reservas\n7-Listar clientes em espera\n8-Viagem com melhor Pontuaçãoo num mes\n9-Dados estatisticos relativos a um ano");
        op=leInteiro();
        while(!(op>0 && op<10)){
            System.out.println("Opção inválida! (1-9)");
            op=leInteiro();
        }
        return op;
    }

    /**
     *
     * @return
     */
    public int menuCliente(){
        int op=0;
        System.out.println("---------BEM VINDO---------");
        System.out.println("Escolha uma opção:\n1-Consultar Viagens\n2-Reservar Viagem\n3-Consultar Reserva\n4-Cancelar Reserva\n5-Comentar viagens realizadas\n6-Ler comentários de viagens realizadas\n7-Sair da Aplicação");
        op=leInteiro();
        while(!(op>0 && op<8)){
            System.out.println("Opção invalida! (1-7)");
            op=leInteiro();
        }
        return op;
    }

}
