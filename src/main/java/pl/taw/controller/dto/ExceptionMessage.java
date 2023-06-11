package pl.taw.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
@AllArgsConstructor(staticName = "of")
public class ExceptionMessage {

    String errorId;
    // dodanie wiadomości z błędem
    String errorMessage;

}
