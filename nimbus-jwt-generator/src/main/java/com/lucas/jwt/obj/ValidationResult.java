package com.lucas.jwt.obj;

public class ValidationResult {
    private final boolean isValid;
    private final String message;

    public ValidationResult(boolean isValid, String message) {
        this.isValid = isValid;
        this.message = message;
    }

    public boolean isValid() {
        return isValid;
    }

    public String getMessage() {
        return message;
    }

    public static ValidationResult success() {
        return new ValidationResult(true, "Valid Token");
    }

    public static ValidationResult failure(String message){
        return new ValidationResult(false, message);
    }

}
