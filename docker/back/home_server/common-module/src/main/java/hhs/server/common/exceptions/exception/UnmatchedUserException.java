package hhs.server.common.exceptions.exception;

import hhs.server.domain.exception.ExceptionCode;

public class UnmatchedUserException extends BusinessException {

  public UnmatchedUserException() {
    super(ExceptionCode.UNMATCHED_PROJECT_OWNER);
  }
}
