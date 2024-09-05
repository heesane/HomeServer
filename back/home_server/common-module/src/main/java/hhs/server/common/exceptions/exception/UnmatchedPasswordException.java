package hhs.server.common.exceptions.exception;

import hhs.server.common.exceptions.ExceptionCode;

public class UnmatchedPasswordException extends BusinessException {

  public UnmatchedPasswordException() {
    super(ExceptionCode.UNMATCHED_PASSWORD);
  }
}
