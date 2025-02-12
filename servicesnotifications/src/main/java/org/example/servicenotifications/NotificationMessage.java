package org.example.servicenotifications;






public class NotificationMessage {
    private String clientId;
    private String contractId;
    private String operation;
    private String message;

    // Default constructor (required for deserialization)
    public NotificationMessage() {}

    // Parameterized constructor
    public NotificationMessage(String clientId, String contractId, String operation, String message) {
        this.clientId = clientId;
        this.contractId = contractId;
        this.operation = operation;
        this.message = message;
    }

    // Getters and setters
    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getContractId() {
        return contractId;
    }

    public void setContractId(String contractId) {
        this.contractId = contractId;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "NotificationMessage{" +
                "clientId='" + clientId + '\'' +
                ", contractId='" + contractId + '\'' +
                ", operation='" + operation + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}


