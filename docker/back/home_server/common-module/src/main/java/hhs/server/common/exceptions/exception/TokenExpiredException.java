package hhs.server.common.exceptions.exception;

import hhs.server.common.exceptions.ExceptionCode;

public class TokenExpiredException extends BusinessException {

  public TokenExpiredException() {
    super(ExceptionCode.TOKEN_EXPIRED);
  }

}