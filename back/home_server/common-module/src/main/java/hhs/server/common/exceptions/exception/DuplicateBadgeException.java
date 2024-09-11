package hhs.server.common.exceptions.exception;


import hhs.server.common.exceptions.ExceptionCode;

public class DuplicateBadgeException extends BusinessException{

  public DuplicateBadgeException(ExceptionCode exceptionCode) {
    super(exceptionCode);
  }
}
