package hhs.server.common.exceptions.exception;

import hhs.server.common.exceptions.ExceptionCode;

public class DuplicatedNicknameException extends BusinessException {

  public DuplicatedNicknameException() {
    super(ExceptionCode.DUPLICATE_NICKNAME);
  }

}
