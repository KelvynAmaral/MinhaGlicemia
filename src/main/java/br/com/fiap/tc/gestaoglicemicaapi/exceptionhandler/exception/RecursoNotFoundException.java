package br.com.fiap.tc.gestaoglicemicaapi.exceptionhandler.exception;

public class RecursoNotFoundException extends RuntimeException {
  public RecursoNotFoundException(String mensagem) {
    super(mensagem);
  }
}
