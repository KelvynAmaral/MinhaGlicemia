package br.com.fiap.tc.gestaoglicemicaapi.utils;

import br.com.fiap.tc.gestaoglicemicaapi.enums.StatusGlicemicoEnum;

public class RegraStatusGlicemico {

    //TODO pode ser feito usando switch case
    public static StatusGlicemicoEnum calculaResultadoGlicemia(double valorGlicemia) {

        if (valorGlicemia < 70.0) {
            return StatusGlicemicoEnum.HIPOGLICEMIA;
        } else if (valorGlicemia < 110.0) {
            return StatusGlicemicoEnum.ESTAVEL;
        } else if (valorGlicemia < 200.0) {
            return StatusGlicemicoEnum.HIPERGLICEMIA;
        } else {
            return StatusGlicemicoEnum.CRITICO;
        }
    }
}
