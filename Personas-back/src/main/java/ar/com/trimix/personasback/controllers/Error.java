package ar.com.trimix.personasback.controllers;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

/**
 * Author: agili
 * Date: 27/04/19
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Error {
    private String message;
    private String cause;

    public Error() {
        this.message = null;
        this.cause = null;
    }

    public Error(String message, String cause) {
        this();
        this.message = message;
        this.cause = cause;
    }

    @JsonProperty("message")
    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @JsonProperty("cause")
    public String getCause() {
        return this.cause;
    }

    public void setCause(String cause) {
        this.cause = cause;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (o != null && this.getClass() == o.getClass()) {
            Error that = (Error)o;
            return Objects.equals(this.message, that.message) && Objects.equals(this.cause, that.cause);
        } else {
            return false;
        }
    }

    public int hashCode() {
        return Objects.hash(new Object[]{this.message, this.cause});
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class Error {\n");
        sb.append("  message: ").append(this.message).append("\n");
        sb.append("  cause: ").append(this.cause).append("\n");
        sb.append("}\n");
        return sb.toString();
    }
}