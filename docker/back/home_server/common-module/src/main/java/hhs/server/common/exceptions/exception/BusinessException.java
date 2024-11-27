package hhs.server.common.exceptions.exception;


import hhs.server.common.exceptions.ExceptionCode;
import lombok.Getter;

@Getter
public class BusinessException extends RuntimeException {

  private final ExceptionCode exceptionCode;

  public BusinessException(ExceptionCode exceptionCode) {
    super(exceptionCode.getMessage());
    this.exceptionCode = exceptionCode;
  }
}
