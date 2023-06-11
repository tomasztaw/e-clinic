package pl.taw.controller.api;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.*;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import pl.taw.controller.dto.ExceptionMessage;

import java.util.Map;
import java.util.UUID;

@Slf4j
@RestControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    private static final Map<Class<?>, HttpStatus> EXCEPTION_STATUS = Map.of(
            ConstraintViolationException.class, HttpStatus.BAD_REQUEST,
            EntityNotFoundException.class, HttpStatus.NOT_FOUND
    );

//    @Override
//    protected ResponseEntity<Object> handleExceptionInternal(
//            @NotNull Exception ex,
//            Object body,
//            @NotNull HttpHeaders headers,
//            @NotNull HttpStatusCode statusCode,
//            @NotNull WebRequest request
//    ) {
//        final String errorId = UUID.randomUUID().toString();
//        log.error("Exception: ID={}, HttpStatus={}", errorId, statusCode, ex);
//        return super.handleExceptionInternal(
//                ex,
//                ExceptionMessage.of(errorId),
//                headers,
//                statusCode,
//                request);
//    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(
            @NotNull Exception ex,
            Object body,
            @NotNull HttpHeaders headers,
            @NotNull HttpStatusCode statusCode,
            @NotNull WebRequest request
    ) {
        final String errorId = UUID.randomUUID().toString();
        log.error("Exception: ID={}, HttpStatus={}", errorId, statusCode, ex);

        String errorMessage = "Wystąpił błąd";
        if (ex instanceof ConstraintViolationException) {
            errorMessage = "Wystąpił błąd walidacji: " + ex.getMessage();
        } else if (ex instanceof EntityNotFoundException) {
            errorMessage = "Nie znaleziono encji: " + ex.getMessage();
        }

        return super.handleExceptionInternal(
                ex,
                ExceptionMessage.of(errorId, errorMessage),
                headers,
                statusCode,
                request);
    }


//    @ExceptionHandler(Exception.class)
//    public ResponseEntity<?> handle(Exception exception) {
//        return doHandle(exception, getHttpStatusFromException(exception.getClass()));
//    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handle(Exception exception) {
        String errorMessage = "Wystąpił ogólny błąd: " + exception.getMessage();
        ModelMap model = new ModelMap();
        model.addAttribute("errorMessage", errorMessage);
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .contentType(MediaType.APPLICATION_JSON)
                .body(model);
    }


//    private ResponseEntity<?> doHandle(Exception exception, HttpStatus status) {
//        final String errorId = UUID.randomUUID().toString();
//        log.error("Exception: ID={}, HttpStatus={}", errorId, status, exception);
//        return ResponseEntity
//                .status(status)
//                .contentType(MediaType.APPLICATION_JSON)
//                .body(ExceptionMessage.of(errorId));
//    }

    private ResponseEntity<?> doHandle(Exception exception, HttpStatus status) {
        final String errorId = UUID.randomUUID().toString();
        log.error("Exception: ID={}, HttpStatus={}", errorId, status, exception);

        String errorMessage = "Wystąpił błąd";
        if (exception instanceof ConstraintViolationException) {
            errorMessage = "Wystąpił błąd walidacji: " + exception.getMessage();
        } else if (exception instanceof EntityNotFoundException) {
            errorMessage = "Nie znaleziono encji: " + exception.getMessage();
        }

        ModelMap model = new ModelMap();
        model.addAttribute("errorMessage", errorMessage);

        return ResponseEntity
                .status(status)
                .contentType(MediaType.APPLICATION_JSON)
                .body(model);
    }


    private HttpStatus getHttpStatusFromException(final Class<?> exceptionClass) {
        return EXCEPTION_STATUS.getOrDefault(exceptionClass, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
