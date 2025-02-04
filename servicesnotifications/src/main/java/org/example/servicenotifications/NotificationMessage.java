package org.example.servicenotifications;

public class NotificationMessage {
    private String email;
    private String subject;
    private String message;

    public NotificationMessage(String email, String subject, String message) {
        this.email = email;
        this.subject = subject;
        this.message = message;
    }

    // Getters et Setters
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

