package academy.kata.steps.checkResponse;

import academy.kata.models.ErrorResponse;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ErrorResponseCheck {

public void checkResponse(ErrorResponse errorResponse, String errorCode, String errorDetails, String errorMessage){
    assertEquals(errorResponse.getErrorCode(), errorCode);
    assertEquals(errorResponse.getErrorMessage(), errorMessage);
    assertEquals(errorResponse.getErrorDetails(), errorDetails);
}
}
