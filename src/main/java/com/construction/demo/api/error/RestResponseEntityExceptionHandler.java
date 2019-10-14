package com.construction.demo.api.error;

import javax.persistence.EntityNotFoundException;

import org.hibernate.exception.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.construction.demo.api.model.ErrorResponse;
import com.construction.demo.common.exceptions.ApiException;
import com.construction.demo.common.exceptions.UnprocesableEntityException;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

	private static final Logger LOGGER = LoggerFactory.getLogger(RestResponseEntityExceptionHandler.class);

	/**
	 * Handle MissingServletRequestParameterException. Triggered when a 'required'
	 * request parameter is missing.
	 *
	 * @param ex      {@link RestResponseEntityExceptionHandler}
	 * @param headers {@link HttpHeaders}
	 * @param status  {@link HttpStatus}
	 * @param request {@link WebRequest}
	 * @return the {@link ErrorResponse} object
	 */
	@Override
	protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		String message = ex.getParameterName() + " parameter is missing";
		return new ResponseEntity<>(new ErrorResponse(HttpStatus.BAD_REQUEST, message, ex), HttpStatus.BAD_REQUEST);
	}

	/**
	 * Handle HttpMediaTypeNotSupportedException. This one triggers when JSON is
	 * invalid as well.
	 *
	 * @param ex      {@link HttpMediaTypeNotSupportedException}
	 * @param headers {@link HttpHeaders}
	 * @param status  {@link HttpStatus}
	 * @param request {@link WebRequest}
	 * @return the {@link ErrorResponse} object
	 */
	@Override
	protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(HttpMediaTypeNotSupportedException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		StringBuilder builder = new StringBuilder();
		builder.append(ex.getContentType());
		builder.append(" media type is not supported. Supported media types are ");
		ex.getSupportedMediaTypes().forEach(t -> builder.append(t).append(", "));

		String message = builder.substring(0, builder.length() - 2);
		return new ResponseEntity<>(new ErrorResponse(HttpStatus.UNSUPPORTED_MEDIA_TYPE, message, ex),
				HttpStatus.UNSUPPORTED_MEDIA_TYPE);
	}

	/**
	 * Handles EntityNotFoundException. Created to encapsulate errors with more
	 * detail than javax.persistence.EntityNotFoundException.
	 *
	 * @param ex the {@link EntityNotFoundException}
	 * @return the ErrorResponse object
	 */
	@ExceptionHandler(EntityNotFoundException.class)
	protected ResponseEntity<Object> handleEntityNotFound(EntityNotFoundException ex) {
		return new ResponseEntity<>(new ErrorResponse(HttpStatus.NOT_FOUND, ex.getMessage(), ex), HttpStatus.NOT_FOUND);
	}

	/**
	 * Handle HttpMessageNotReadableException. Happens when request JSON is
	 * malformed.
	 *
	 * @param ex      {@link HttpMessageNotReadableException}
	 * @param headers {@link HttpHeaders}
	 * @param status  {@link HttpStatus}
	 * @param request {@link WebRequest}
	 * @return the {@link ErrorResponse} object
	 */
	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		ServletWebRequest servletWebRequest = (ServletWebRequest) request;
		LOGGER.info("{} to {}", servletWebRequest.getHttpMethod(), servletWebRequest.getRequest().getServletPath());
		String message = "Malformed JSON request";

		return new ResponseEntity<>(new ErrorResponse(HttpStatus.BAD_REQUEST, message, ex), HttpStatus.BAD_REQUEST);
	}

	/**
	 * Handle DataIntegrityViolationException, inspects the cause for different DB
	 * causes.
	 *
	 * @param ex {@link RestResponseEntityExceptionHandler}
	 * @return the {@link ErrorResponse} object
	 */
	@ExceptionHandler(DataIntegrityViolationException.class)
	protected ResponseEntity<Object> handleDataIntegrityViolation(DataIntegrityViolationException ex,
			WebRequest request) {
		if (ex.getCause() instanceof ConstraintViolationException) {
			return new ResponseEntity<>(new ErrorResponse(HttpStatus.CONFLICT, "Database error", ex),
					HttpStatus.CONFLICT);
		}

		return new ResponseEntity<>(new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, ex),
				HttpStatus.INTERNAL_SERVER_ERROR);
	}

	/**
	 * Handle Exception, handle generic Exception.class
	 *
	 * @param ex      the {@link Exception}
	 * @param request {@link WebRequest}
	 * @return the {@link ErrorResponse} object
	 */
	@ExceptionHandler(value = { Exception.class })
	protected ResponseEntity<Object> handleGenericError(Exception ex, WebRequest request) {
		return new ResponseEntity<>(new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Unexpected error", ex),
				HttpStatus.INTERNAL_SERVER_ERROR);
	}

	/**
	 * Handle custom ApiException
	 * 
	 * @param ex      {@link Exception}
	 * @param request {@link WebRequest}
	 * @return the {@link ErrorResponse} object
	 */
	@ExceptionHandler(value = { ApiException.class })
	protected ResponseEntity<Object> handleApiError(Exception ex, WebRequest request) {
		ApiException e = (ApiException) ex;
		LOGGER.info("Returning error response: {}", e.getMessage());

		return new ResponseEntity<>(new ErrorResponse(e.getStatusCode(), e.getMessage(), e), e.getStatusCode());
	}

	/**
	 * Handle custom UnprocesableEntityException
	 * 
	 * @param ex      {@link UnprocesableEntityException}
	 * @param request {@link WebRequest}
	 * @return the {@link ErrorResponse} object
	 */
	@ExceptionHandler(value = { UnprocesableEntityException.class })
	protected ResponseEntity<Object> handleUniqueConstraintException(Exception ex, WebRequest request) {
		UnprocesableEntityException e = (UnprocesableEntityException) ex;

		return new ResponseEntity<>(new ErrorResponse(HttpStatus.UNPROCESSABLE_ENTITY, e.getMessage(), e),
				HttpStatus.UNPROCESSABLE_ENTITY);
	}

}