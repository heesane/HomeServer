package hhs.server.common.exceptions.exception;

import hhs.server.domain.exception.ExceptionCode;

public class UnmatchedPasswordException extends BusinessException {

  public UnmatchedPasswordException() {
    super(ExceptionCode.UNMATCHED_PASSWORD);
  }
}
