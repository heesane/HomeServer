package hhs.server.common.exceptions.exception;

import hhs.server.domain.exception.ExceptionCode;

public class TokenNotExistsException extends BusinessException {

  public TokenNotExistsException() {
    super(ExceptionCode.TOKEN_ACCESS_NOT_EXISTS);
  }

}
