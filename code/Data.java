/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agenciaviagens;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author ADM
 */

public class Data implements Serializable{

    private int dia;
    private int mes;
    private int ano;
    private int min;

    /**
     *
     * @param dia
     * @param mes
     * @param ano
     * @param hora
     * @param min
     */
    public Data(int dia, int mes, int ano, int hora, int min) {
        this.dia = dia;
        this.mes = mes;
        this.ano = ano;
        this.min = min;
        this.hora = hora;
    }

    /**
     *
     * @return
     */
    public int getHora() {
        return hora;
    }

    /**
     *
     * @param hora
     */
    public void setHora(int hora) {
        this.hora = hora;
    }

    /**
     *
     * @return
     */
    public int getMin() {
        return min;
    }

    /**
     *
     * @param min
     */
    public void setMin(int min) {
        this.min = min;
    }

    /**
     *
     * @return
     */
    public int getAno() {
        return ano;
    }

    /**
     *
     * @param ano
     */
    public void setAno(int ano) {
        this.ano = ano;
    }

    /**
     *
     * @return
     */
    public int getMes() {
        return mes;
    }

    /**
     *
     * @param mes
     */
    public void setMes(int mes) {
        this.mes = mes;
    }

    /**
     *
     * @return
     */
    public int getDia() {
        return dia;
    }

    /**
     *
     * @param dia
     */
    public void setDia(int dia) {
        this.dia = dia;
    }

    private int hora;

    /**
     *
     * @param duracao
     * @return
     */
    public Data calcDataChegada(int duracao){
        int min,hora=this.getHora(),dia=this.getDia(),mes=this.getMes(),ano=this.getAno();
        //STEP 1
        min=this.getMin()+duracao;

        while(min>=60){
            min=min-60;
            hora++;
        }
        while(hora>=24){
            hora=hora-24;
            dia++;
        }
        while(dia>31){
            hora=hora-31;
            mes++;
        }
        while(mes>12){
            mes=mes-12;
            ano++;
        }

        Data dataChegada = new Data(dia,mes,ano,hora,min);
        return dataChegada;
    }

    /**
     *
     * @param data
     * @return
     */
    public int comparaData(Data data){
        // this < data ->return -1 || this ==data -> return 0 || this > data -> return 1
        if(this.ano == data.ano){
            if(this.mes == data.mes){
                if(this.dia == data.dia){
                    if(this.hora == data.hora){
                        if(this.min == data.min){
                            return 0;
                        }
                        else if(this.min < data.min){
                            return -1;
                        }
                        else if(this.min > data.min){
                            return 1;
                        }
                    }
                    else if(this.hora < data.hora){
                        return -1;
                    }
                    else if(this.hora > data.hora){
                        return 1;
                    }
                }
                else if(this.dia < data.dia){
                    return -1;
                }
                else if(this.dia > data.dia){
                    return 1;
                }

            }
            else if(this.mes < data.mes){
                return -1;
            }
            else if(this.mes > data.mes){
                return 1;
            }
        }
        else if(this.ano < data.ano){
            return -1;
        }
        return 1;
    }

    /**
     *
     * @param data
     * @return
     */
    public int daysBetween(Data data){
        long jd1,jd2;
        int dias;

        jd1=julianDays(data);
        jd2=julianDays(this);

        //if this < data
        if(this.comparaData(data)==-1){
            dias=(int)(jd2-jd1);
        }
        else {
            dias=(int)(jd1-jd2);
        }

        return dias;

    }

    //algoritmo para calcular dias entre datas atraves do metodo julian Days

    /**
     *
     * @param data
     * @return
     */
        public long julianDays(Data data){
        //JULIAN DAY NUMBERS
        int y,d,m;
        long jd;
        y=data.getAno()-1;
        d=data.getDia();
        //marÃ§o =0 ... janeiro=10 fevereiro = 11
        if(data.getMes() == 1){  m=10;  }
        if(data.getMes() == 2){  m=11;  }
        else {  m=data.getMes()-3;  }

        //calculo formula:
        jd=d+((153*m+2)/5)+365*y+(y/4)-(y/100)+(y/400);

        return jd;
    }

    @Override
    public String toString() {
        return (dia +"/" + mes +"/" + ano +"  Hora:" + hora +" : "+ min);
    }
}