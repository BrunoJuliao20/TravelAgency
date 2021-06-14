/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agenciaviagens;


import java.io.Serializable;
import java.util.ArrayList;

/**
 * classe que gere a agencia
 * @author ADM
 */

public class Administrador extends Utilizador implements Serializable {

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
    public Administrador(String nome, String morada, String email, String password, long nif, long telefone,Agencia app,Interface it) {
        super(nome, morada, email, password, nif, telefone,app,it);

    }
    
    private static ArrayList<Viagem> viagens;
    private static ArrayList<Utilizador> utilizadores;
    private static ArrayList<Reserva> reservas,reservasEspera,vagas,viagensRealizadas,reservasCanceladas;
    
    /**************************************MENUS***********************************************/
    @Override
    public void menuInicial(){
        int op=0;
        Interface it = new Interface();
        op=it.menuAdmin();

        switch (op){
            case 1:
                gerirClientes();
                break;
            case 2:
                gerirViagens();
                break;
            case 3:
                gerirAutocarros();
                break;
            case 4:
                it.menuEstatisticas();
                estatisticas();
                break;
            case 5:
                criarAdministrador();
                menuInicial();
                break;
            case 6:
                return;

        }

    }

    /**
     *
     */
    public void gerirClientes(){
        int op=0;
        Interface it = new Interface();

        while(!(op>0 && op<5)){
            op=it.menuGerirClientes();
            System.out.println("OpÁ„o escolhida com sucesso");
            switch (op){
                case 1:
                    criarCliente();
                    Agencia.listaUtilizadores(this);

                    break;
                case 2:
                    eliminaCliente();

                    break;
                case 3:
                    alterarCliente();

                    break;
                case 4:
                    Agencia.listaUtilizadores(this);

                    break;
                case 5:
                    menuInicial();
            }

        }
        menuInicial();

    }

    /**
     *
     */
    public void gerirViagens(){
        int op=-1;
        Interface it = new Interface();

        while(!(op>0 && op<5)) {
            op = it.menuGerirViagens();
            switch (op) {
                case 1:
                    criarViagem();
                    break;
                case 2:
                    eliminarViagem();
                    break;
                case 3:
                    alterarViagem();
                    break;
                case 4:
                    //listar viagens
                    Agencia.listaViagens();
                    break;
                case 5:
                    menuInicial();

            }
        }
        menuInicial();
    }

    /**
     *
     */
    public void gerirAutocarros(){
        int op=-1;
        Interface it = new Interface();

        while(!(op>0 && op<6)) {
            op=it.menuGerirAutocarros();
            switch (op) {
                case 1:
                    criarAutocarro();
                    break;
                case 2:
                    eliminaAutocarro();
                    break;
                case 3:
                    alteraAutocarro();
                    break;
                case 4:
                    Agencia.listaAutocarros();
                    break;
                case 5:
                    //menuInicial();
            }
        }
        menuInicial();
    }

    /**
     *
     */
    public void estatisticas(){
        int op;
        op = it.menuEstatisticas();
        switch (op) {
            case 1:
                conta_viagem(it.pedeMes());
                break;
            case 2:
                System.out.println("erro");
                break;
            case 3:
                System.out.println("erro");
                break;
            case 4:
                System.out.println("erro");
                break;
            case 5:
                lista_reservas_canceladas();
                break;
            case 6:
                lista_reservas();
                break;
            case 7:
                lista_clientes_espera();
                break;
            case 8:
                System.out.println("erro");
                break;
            case 9:
                System.out.println("erro");
                break;
        }
        menuInicial();
    }
    
    /********************************************************************************************/
        
    public void criarAdministrador(){
        String nome,morada,email,password;
        long nif,telefone;
        Interface it = new Interface();
        nome=it.pedeNome();
        morada=it.pedeMorada();
        email=it.pedeEmail();
        password=it.pedePassword();
        nif=it.pedeNif();
        telefone=it.pedeTelefone();

        Administrador novoAdmin= new Administrador(nome,morada,email,password,nif,telefone,app,it);
        Agencia.adicionaUtilizador(novoAdmin,this);
    }


    /***************************GESTAO CLIENTES*************************************************/
    public void criarCliente(){
        String nome,morada,email,password;
        long nif,telefone;
        int esc;
        Interface it = new Interface();

        nome=it.pedeNome();
        morada=it.pedeMorada();
        email=it.pedeEmail();
        password=it.pedePassword();
        nif=it.pedeNif();
        telefone=it.pedeTelefone();

        esc=it.escolhaTipoCliente();

        if(esc==1){
            Cliente novoCliente= new Cliente(nome,morada,email,password,nif,telefone,app,it);
            Agencia.adicionaUtilizador(novoCliente,this);
            it.print("*Cliente Regular criado com sucesso*");
        }
        else if(esc == 2){
            Premium novoCliente= new Premium(nome,morada,email,password,nif,telefone,app,it);
            Agencia.adicionaUtilizador(novoCliente,this);
            it.print("*Cliente Premium criado com sucesso*");
        }

    }

    /**
     *
     */
    public void alterarCliente(){
        Interface it = new Interface();

        int e=0,ind;
        String nome,morada,email,password;
        long nif,telefone;
        Cliente cliente;

        it.print("Cliente a alterar: ");
        nif = it.pedeNif();
        ind = Agencia.getIndCliente(nif,this);
        System.out.println("Indice. "+ind);


        if(ind == -1){
            it.print("Cliente n„o encontrado!");
        }
        else{
            cliente=(Cliente)Agencia.getUtilizador(ind);
            it.print("Cliente a alterar:"+cliente);

            e=it.escolhaAlteraCliente();
            switch (e){
                case 1:
                    nome=it.pedeNome();
                    cliente.setNome(nome);
                    break;
                case 2:
                    morada=it.pedeMorada();
                    cliente.setMorada(morada);
                    break;
                case 3:
                    email=it.pedeEmail();
                    cliente.setEmail(email);
                    break;
                case 4:
                    password=it.pedePassword();
                    cliente.setPassword(password);
                    break;
                case 5:
                    nif=it.pedeNif();
                    cliente.setNif(nif);
                    break;
                case 6:
                    telefone=it.pedeTelefone();
                    cliente.setTelefone(telefone);
                    break;
                default:
                    it.print("Erro. Valor errado!");
            }
            it.print("Cliente alterado com sucesso!");
        }

    }

    /**
     *
     */
    public void eliminaCliente(){
        Interface it = new Interface();
        int ind=-1;
        long nif;
        it.print("\n---Eliminar cliente---");
        nif=it.pedeNif();
        ind = Agencia.getIndCliente(nif,this);

        if(ind == -1){
            it.print("*N√£o existe um cliente com o NIF dado*");
        }
        else{
            Cliente aux = (Cliente) Agencia.getUtilizador(ind);
            it.print("Cliente eliminado:"+aux);

            Agencia.eliminaUtilizador(aux,this);
        }
    }

    /*******************************GESTAO AUTOCARROS***************************************/

    public void criarAutocarro(){
        String matricula;
        int lotacao;
        Interface it = new Interface();

        matricula=it.pedeMatricula();
        lotacao=it.pedeLotacao();

        Autocarro novoAutocarro = new Autocarro(matricula,lotacao);
        Agencia.adicionaAutocarro(novoAutocarro,this);
    }

    /**
     *
     */
    public void eliminaAutocarro(){
        String mat;
        Interface it = new Interface();
        int ind;

        mat = it.pedeMatricula();
        ind=Agencia.getIndAutocarro(mat,this);

        Autocarro autocarro =Agencia.getAutocarro(ind);
        Agencia.eliminaAutocarro(autocarro,this);

    }

    /**
     *
     */
    public void alteraAutocarro(){
        Interface it = new Interface();

        int e=0,ind,lotacao;
        String matricula;
        Autocarro autocarro;

        it.print("Autocarro a alterar: ");
        matricula = it.pedeMatricula();
        ind = Agencia.getIndAutocarro(matricula,this);

        if(ind == -1){
            it.print("Autocarro n√£o encontrado!");
        }
        else{
            autocarro= Agencia.getAutocarro(ind);
            it.print("Autocarro a alterar:"+autocarro);
            if(autocarro.numViagens()==0){
                e=it.escolhaAlteraAutocarro();
                switch (e){
                    case 1:
                        matricula=it.pedeMatricula();
                        autocarro.setMatricula(matricula);
                        break;
                    case 2:
                        lotacao=it.pedeLotacao();
                        autocarro.setLotacao(lotacao);
                        break;

                    default:
                        it.print("Erro. Valor errado!");
                }
                it.print("Autocarro alterado com sucesso!");
            }
            else{
                it.print("Autocarro a ser utilizado. Impossivel Alterar.");
            }

        }
    }

    /***************************GESTAO VIAGENS*********************************************/

    public void criarViagem(){
        String codigo, origem, destino;
        int i,ano,mes,dia,hora,min,duracao;
        float preco;
        ArrayList<Autocarro> autoUtilizados,autoDisponiveis;
        Interface it = new Interface();

        codigo=it.pedeCodigo();
        origem=it.pedeOrigem();
        destino=it.pedeDestino();
        preco=it.pedePreco();
        ano=it.pedeAno();
        mes=it.pedeMes();
        dia=it.pedeDia();
        hora=it.pedeHora();
        min=it.pedeMin();
        duracao=it.pedeDuracao();

        Data dataP = new Data(dia,mes,ano,hora,min);
        Data dataC = dataP.calcDataChegada(duracao);
        System.out.println(dataC);
        autoDisponiveis=Agencia.getAutocarrosDisponiveis(dataP,dataC,this);

        if(autoDisponiveis.size()<1){
            it.print("N„o h· autocarros Disponiveis para a Viagem");
        }
        else{
            autoUtilizados = it.escolhaAutocarrosPViagem(autoDisponiveis);
            Viagem viagem= new Viagem(codigo,origem,destino,preco,dataP,dataC,duracao,autoUtilizados);
            for(i=0;i<autoUtilizados.size();i++){
                autoUtilizados.get(i).inserirViagem(viagem);
            }
            Agencia.adicionaViagens(viagem,this);
        }
    }

    /**
     *
     */
    public void alterarViagem(){
        Interface it = new Interface();

        int e=0,ind,duracao;
        String codigo,origem,destino;
        float preco;
        Data dataPartida;
        ArrayList<Autocarro> autoUtilizados,autoDispon;
        Viagem viagem;

        it.print("Viagem a alterar: ");
        codigo = it.pedeCodigo();

        ind = Agencia.getIndViagem(codigo,this);

        if(ind == -1){
            it.print("Viagem n√£oo encontrada!");
        }
        else{
            //String codigo, String origem, String destino, float preco, Data dataPartida,Data dataChegada,int duracao, ArrayList<Autocarro> autocarrosUtilizados
            viagem= Agencia.getViagem(ind);
            it.print("Viagem a alterar:"+viagem);
            if(viagem.getnReservas()==0){
                e=it.escolhaAlteraViagem();
                switch (e){
                    case 1:
                        codigo=it.pedeCodigo();
                        viagem.setCodigo(codigo);
                        break;
                    case 2:
                        origem=it.pedeOrigem();
                        viagem.setOrigem(origem);
                        break;
                    case 3:
                        destino=it.pedeDestino();
                        viagem.setDestino(destino);
                        break;
                    case 4:
                        preco=it.pedePreco();
                        viagem.setPreco(preco);
                        break;
                    case 5:
                        dataPartida=it.pedeData();
                        viagem.setDataPartida(dataPartida);
                        viagem.setDataChegada(dataPartida.calcDataChegada(viagem.getDuracao()));

                        break;
                    case 6:
                        duracao=it.pedeDuracao();
                        viagem.setPreco(duracao);
                        break;
                    case 7:
                        viagem.libertaAutocarros();
                        autoUtilizados=it.escolhaAutocarrosPViagem(Agencia.getAutocarrosDisponiveis(viagem.getDataPartida(),viagem.getDataChegada(),this));
                        viagem.setAutocarrosUtilizados(autoUtilizados);
                        break;

                    default:
                        it.print("Erro. Valor errado!");
                }
                it.print("Viagem alterada com sucesso!");
            }
            else{
                it.print("Autocarro a ser utilizado. Impossivel Alterar.");
            }

        }

    }

    /**
     *
     */
    public void eliminarViagem(){
        String codigo;
        int ind;
        Interface it = new Interface();

        codigo = it.pedeCodigo();
        ind=Agencia.getIndViagem(codigo,this);
        if(ind >= 0){
            Viagem viagem =Agencia.getViagem(ind);
            Agencia.eliminaViagem(viagem,this);
        }
        else {
            it.print("N„o foi encontrada a viagem");
        }

    }

    
    
    public String conta_viagem(int mes){
        int aux=0,num_vendas_max=0;
        String melhor_codigo="";
        ArrayList<String> codigos=new ArrayList<>();
        for(Viagem v_aux:viagens)
            if (v_aux.dataPartida.getMes() == mes)
                codigos.add(v_aux.getCodigo());
        
        for(String codigo:codigos){
            String aux_codigo = codigo;
            if (codigo == aux_codigo){
                aux++;
            }
            if(aux>= num_vendas_max){
                num_vendas_max = aux;
                melhor_codigo = codigo;
            }
        }
        System.out.println("A viagem mais vendida tem o codigo: "+melhor_codigo);
        return melhor_codigo;
    }
    
    public void lista_reservas_canceladas(){
        int i;
        for(i=0;i<reservasCanceladas.size();i++){
            System.out.println(i+"\n"+reservasCanceladas.get(i));
        }
    }
    
    public void lista_reservas(){
        int i;
        for(i=0;i<reservas.size();i++){
            System.out.println(i+"\n"+reservas.get(i));
        }
    }
    
    public void lista_clientes_espera(){
        int i;
        for(i=0;i<reservasEspera.size();i++){
            System.out.println(i+"\n"+reservasEspera.get(i).getCliente());
        }
    }
    
    @Override
    public String toString(){
        return ("(Administrador) Nome: " +nome+" | Morada: "+morada+" | Email: "+email+" | NIF: "+nif+" | Telefone: "+telefone);
    }

}