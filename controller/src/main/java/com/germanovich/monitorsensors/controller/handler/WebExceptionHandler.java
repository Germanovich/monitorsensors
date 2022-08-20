package com.germanovich.monitorsensors.controller.handler;

import com.germanovich.monitorsensors.common.exception.ApplicationException;
import com.germanovich.monitorsensors.common.exception.BusinessException;
import com.germanovich.monitorsensors.common.exception.DaoException;
import com.germanovich.monitorsensors.dto.exception.ExceptionDto;
import com.hermanovich.accountingsystem.util.MessageForUser;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.validation.BindException;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.context.request.async.AsyncRequestTimeoutException;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolationException;

@Log4j2
@ControllerAdvice
public class WebExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(
            final HttpRequestMethodNotSupportedException ex,
            final HttpHeaders headers,
            final HttpStatus status,
            final WebRequest request
    ) {
        log.error(ex);
        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED)
                .body(MessageForUser.REQUEST_METHOD_NOT_SUPPORTED.get());
    }

    @Override
    protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(
            final HttpMediaTypeNotSupportedException ex,
            final HttpHeaders headers,
            final HttpStatus status,
            final WebRequest request
    ) {
        log.error(ex);
        return ResponseEntity.status(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
                .body(MessageForUser.MEDIA_TYPE_NOT_SUPPORTED.get());
    }

    @Override
    protected ResponseEntity<Object> handleHttpMediaTypeNotAcceptable(
            final HttpMediaTypeNotAcceptableException ex,
            final HttpHeaders headers,
            final HttpStatus status,
            final WebRequest request
    ) {
        log.error(ex);
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE)
                .body(MessageForUser.MEDIA_TYPE_NOT_ACCEPTABLE.get());
    }

    @Override
    protected ResponseEntity<Object> handleMissingPathVariable(
            final MissingPathVariableException ex,
            final HttpHeaders headers,
            final HttpStatus status,
            final WebRequest request
    ) {
        log.error(ex);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(MessageForUser.SERVER_ERROR.get());
    }

    @Override
    protected ResponseEntity<Object> handleMissingServletRequestParameter(
            final MissingServletRequestParameterException ex,
            final HttpHeaders headers,
            final HttpStatus status,
            final WebRequest request
    ) {
        log.error(ex);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(MessageForUser.BAD_REQUEST.get());
    }

    @Override
    protected ResponseEntity<Object> handleServletRequestBindingException(
            final ServletRequestBindingException ex,
            final HttpHeaders headers,
            final HttpStatus status,
            final WebRequest request
    ) {
        log.error(ex);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(MessageForUser.BAD_REQUEST.get());
    }

    @Override
    protected ResponseEntity<Object> handleConversionNotSupported(
            final ConversionNotSupportedException ex,
            final HttpHeaders headers,
            final HttpStatus status,
            final WebRequest request
    ) {
        log.error(ex);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(MessageForUser.SERVER_ERROR.get());
    }

    @Override
    protected ResponseEntity<Object> handleTypeMismatch(
            final TypeMismatchException ex,
            final HttpHeaders headers,
            final HttpStatus status,
            final WebRequest request
    ) {
        log.error(ex);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(MessageForUser.BAD_REQUEST.get());
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(
            final HttpMessageNotReadableException ex,
            final HttpHeaders headers,
            final HttpStatus status,
            final WebRequest request
    ) {
        log.error(ex);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(MessageForUser.BAD_REQUEST.get());
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotWritable(
            final HttpMessageNotWritableException ex,
            final HttpHeaders headers,
            final HttpStatus status,
            final WebRequest request
    ) {
        log.error(ex);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(MessageForUser.SERVER_ERROR.get());
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            final MethodArgumentNotValidException ex,
            final HttpHeaders headers,
            final HttpStatus status,
            final WebRequest request
    ) {
        log.error(ex);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(MessageForUser.BAD_REQUEST.get());
    }

    @Override
    protected ResponseEntity<Object> handleMissingServletRequestPart(
            final MissingServletRequestPartException ex,
            final HttpHeaders headers,
            final HttpStatus status,
            final WebRequest request
    ) {
        log.error(ex);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(MessageForUser.BAD_REQUEST.get());
    }

    @Override
    protected ResponseEntity<Object> handleBindException(
            final BindException ex,
            final HttpHeaders headers,
            final HttpStatus status,
            final WebRequest request
    ) {
        log.error(ex);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(MessageForUser.BAD_REQUEST.get());
    }

    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(
            final NoHandlerFoundException ex,
            final HttpHeaders headers,
            final HttpStatus status,
            final WebRequest request
    ) {
        log.error(ex);
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(MessageForUser.NO_HANDLER_FOUND.get());
    }

    @Override
    protected ResponseEntity<Object> handleAsyncRequestTimeoutException(
            final AsyncRequestTimeoutException ex,
            final HttpHeaders headers,
            final HttpStatus status,
            final WebRequest webRequest
    ) {
        log.error(ex);
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
                .body(MessageForUser.SERVICE_UNAVAILABLE.get());
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(
            final Exception ex,
            final Object body,
            final HttpHeaders headers,
            final HttpStatus status,
            final WebRequest request
    ) {
        log.error(ex);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(MessageForUser.SERVER_ERROR.get());
    }

    @ExceptionHandler(ApplicationException.class)
    protected ResponseEntity<ExceptionDto> onApplicationException(final ApplicationException applicationException) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(getExceptionDto(applicationException.getMassageForUser()));
    }

    @ExceptionHandler(BusinessException.class)
    protected ResponseEntity<ExceptionDto> onBusinessException(final BusinessException businessException) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(getExceptionDto(businessException));
    }

    @ExceptionHandler(DaoException.class)
    protected ResponseEntity<ExceptionDto> onBusinessException(final DaoException daoException) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(getExceptionDto(daoException));
    }

    @ExceptionHandler(ConstraintViolationException.class)
    ResponseEntity<ExceptionDto> handleConstraintViolationException(final ConstraintViolationException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(getExceptionDto(e.getMessage()));
    }

    private ExceptionDto getExceptionDto(final Exception ex) {
        ExceptionDto exceptionDto = new ExceptionDto();
        exceptionDto.setMessage(ex.getMessage());
        log.error(ex.getMessage());
        return exceptionDto;
    }

    private ExceptionDto getExceptionDto(final String message) {
        ExceptionDto exceptionDto = new ExceptionDto();
        exceptionDto.setMessage(message);
        log.error(message);
        return exceptionDto;
    }
}
