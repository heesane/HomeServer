package hhs.server.common.exceptions.exception;


import hhs.server.domain.exception.ExceptionCode;

public class DuplicateBadgeException extends BusinessException{

  public DuplicateBadgeException(ExceptionCode exceptionCode) {
    super(exceptionCode);
  }
}
