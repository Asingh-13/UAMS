package model;

public class RequestDetailsDTO {
    private int requestId;
    private String employeeName;
    private String softwareName;
    private String accessType;
    private String reason;
    private String status;

    // Getters and Setters
    public int getRequestId() { return requestId; }
    public void setRequestId(int requestId) { this.requestId = requestId; }

    public String getEmployeeName() { return employeeName; }
    public void setEmployeeName(String employeeName) { this.employeeName = employeeName; }

    public String getSoftwareName() { return softwareName; }
    public void setSoftwareName(String softwareName) { this.softwareName = softwareName; }

    public String getAccessType() { return accessType; }
    public void setAccessType(String accessType) { this.accessType = accessType; }

    public String getReason() { return reason; }
    public void setReason(String reason) { this.reason = reason; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}