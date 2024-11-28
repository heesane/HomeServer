package hhs.server.domain.exception;

public class NotFoundException extends BusinessException{

  public NotFoundException(ExceptionCode exceptionCode) {
    super(exceptionCode);
  }
}
