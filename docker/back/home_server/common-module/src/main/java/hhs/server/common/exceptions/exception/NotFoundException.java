package hhs.server.common.exceptions.exception;


import hhs.server.domain.exception.ExceptionCode;

public class NotFoundException extends BusinessException{

  public NotFoundException(ExceptionCode exceptionCode) {
    super(exceptionCode);
  }
}
