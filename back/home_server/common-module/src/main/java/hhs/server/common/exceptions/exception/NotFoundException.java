package hhs.server.common.exceptions.exception;


import hhs.server.common.exceptions.ExceptionCode;

public class NotFoundException extends BusinessException{

  public NotFoundException(ExceptionCode exceptionCode) {
    super(exceptionCode);
  }
}
