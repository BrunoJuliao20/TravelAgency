/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agenciaviagens;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * 
 * @author ADM
 */

public class Autocarro implements Serializable {
    String matricula;
    int lotacao;
    ArrayList<Viagem> viagens;

    /**
     *
     * @param matricula
     * @param lotacao
     */
    public Autocarro(String matricula, int lotacao) {
        this.matricula = matricula;
        this.lotacao = lotacao;
        viagens = new ArrayList<>();
    }


    public String getMatricula() {
        return matricula;
    }

    /**
     *
     * @param matricula
     */
    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    /**
     *
     * @return
     */
    public int getLotacao() {
        return lotacao;
    }

    /**
     *
     * @param lotacao
     */
    public void setLotacao(int lotacao) {
        this.lotacao = lotacao;
    }

    /**
     *
     * @return
     */
    public int numViagens(){
        return viagens.size();
    }

    /**
     *
     * @param viagem
     */
    public void eliminaViagem(Viagem viagem){
        this.viagens.remove(viagem);
    }

    /**
     *
     * @param viagem
     */
    public void inserirViagem(Viagem viagem) {
        this.viagens.add(viagem);
    }

    /**
     * @param dataPartida
     * @param dataChegada
     * @param dataPartida
     * @param dataChegada
     * @return ***************************************************************************************************/
    public int verificaDisponibilidade(Data dataPartida, Data dataChegada){
        //return 1->disponivel  return 0-> nÃ£o disponivel
        int i;
        Viagem aux;

        for(i=0;i < this.viagens.size();i++){
            aux=this.viagens.get(i);
            System.out.println("DATAS A COMPARAR: DATAP:"+dataPartida+"DATAC:"+dataChegada);
            if(!(dataChegada.comparaData(aux.getDataPartida())== -1 || dataPartida.comparaData(aux.getDataChegada())==1)){
                System.out.println("Não Disponivel");
                return 0;

            }
        }
        System.out.println("Disponivel");
        return 1;
    }

    /*****************************************************************************************************/

    public String toString(){
        return "(Autocarro) Matricula: "+this.matricula+" Lotação: "+this.lotacao;
    }
}