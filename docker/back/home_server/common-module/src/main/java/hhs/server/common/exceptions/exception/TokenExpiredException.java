package hhs.server.common.exceptions.exception;

import hhs.server.domain.exception.ExceptionCode;

public class TokenExpiredException extends BusinessException {

  public TokenExpiredException() {
    super(ExceptionCode.TOKEN_EXPIRED);
  }

}