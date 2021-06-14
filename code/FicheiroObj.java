/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agenciaviagens;

import java.io.*;

/**
 * classe de leitura e armazenamento de dados
 * @author ADM
 */

public class FicheiroObj {
    private ObjectInputStream iS;
    private ObjectOutputStream oS;
    String nomeFicheiro;

    /**
     *
     * @param nomeDoFicheiro
     * @return
     */
    public boolean exists(String nomeDoFicheiro){
        File f = new File(nomeDoFicheiro);
        if(f.exists() && f.length()>0){
            return true;
        }
        return false;
    }

    /**
     *
     * @param nomeDoFicheiro
     * @return
     */
    public boolean abreLeitura(String nomeDoFicheiro) {
        try{
            iS = new ObjectInputStream(new FileInputStream(nomeDoFicheiro));
            return true;
        } catch (IOException e){
            return false;
        }
    }

    /**
     *
     * @param nomeDoFicheiro
     * @throws IOException
     */
    public void abreEscrita(String nomeDoFicheiro) throws  IOException
    {
        oS = new ObjectOutputStream(new FileOutputStream(nomeDoFicheiro));
    }

    /**
     *
     * @return
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public Object leObjecto() throws IOException, ClassNotFoundException
    {
        return iS.readObject();
    }

    /**
     *
     * @param o
     * @throws IOException
     */
    public void escreveObjecto(Object o) throws IOException
    {
        oS.writeObject(o);
    }

    /**
     *
     * @throws IOException
     */
    public void fechaLeitura() throws IOException
    {
        iS.close();
    }

    /**
     *
     * @throws IOException
     */
    public void fechaEscrita() throws IOException
    {
        oS.close();
    }
}