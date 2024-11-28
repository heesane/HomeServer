package hhs.server.common.exceptions.exception;


import hhs.server.domain.exception.ExceptionCode;

public class DuplicatedEmailException extends BusinessException {

  public DuplicatedEmailException() {
    super(ExceptionCode.DUPLICATE_EMAIL);
  }
}
