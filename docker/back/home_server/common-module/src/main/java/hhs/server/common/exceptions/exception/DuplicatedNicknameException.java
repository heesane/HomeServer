package hhs.server.common.exceptions.exception;

import hhs.server.domain.exception.ExceptionCode;

public class DuplicatedNicknameException extends BusinessException {

  public DuplicatedNicknameException() {
    super(ExceptionCode.DUPLICATE_NICKNAME);
  }

}
