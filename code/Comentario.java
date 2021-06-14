/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agenciaviagens;

/**
 * classe a partir da qual os clientes podem expressar a opiniao sobre o serviço
 * @author ADM
 */

public class Comentario {

    private String comentario;
    private int pontuacao;

    /**
     *
     * @param comentario
     * @param pontuacao
     */
    public Comentario(String comentario, int pontuacao) {
        this.comentario = comentario;
        this.pontuacao = pontuacao;
    }

    /**
     *
     * @return
     */
    public String getComentario() {
        return comentario;
    }

    /**
     *
     * @param comentario
     */
    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    /**
     *
     * @return
     */
    public int getPontuacao() {
        return pontuacao;
    }

    /**
     *
     * @param pontuacao
     */
    public void setPontuacao(int pontuacao) {
        this.pontuacao = pontuacao;
    }

    public String toString(){
        return "(Comentário) Comentario: "+comentario+"\nPontuação: "+pontuacao;
    }
}