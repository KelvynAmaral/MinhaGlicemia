package br.com.fiap.tc.gestaoglicemicaapi.exceptionhandler.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CampoErro {
  private final String nomeCampo;
  private final String mensagem;

}
