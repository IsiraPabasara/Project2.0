package model;

public class ReportEntry {
    private String parcelId, parcelType, senderEmail, senderName;
    private String receiverEmail, receiverName, deliveryPersonnel, scheduleDate;

    // Getters and Setters
    public String getParcelId() { return parcelId; }
    public void setParcelId(String parcelId) { this.parcelId = parcelId; }

    public String getParcelType() { return parcelType; }
    public void setParcelType(String parcelType) { this.parcelType = parcelType; }

    public String getSenderEmail() { return senderEmail; }
    public void setSenderEmail(String senderEmail) { this.senderEmail = senderEmail; }

    public String getSenderName() { return senderName; }
    public void setSenderName(String senderName) { this.senderName = senderName; }

    public String getReceiverEmail() { return receiverEmail; }
    public void setReceiverEmail(String receiverEmail) { this.receiverEmail = receiverEmail; }

    public String getReceiverName() { return receiverName; }
    public void setReceiverName(String receiverName) { this.receiverName = receiverName; }

    public String getDeliveryPersonnel() { return deliveryPersonnel; }
    public void setDeliveryPersonnel(String deliveryPersonnel) { this.deliveryPersonnel = deliveryPersonnel; }

    public String getScheduleDate() { return scheduleDate; }
    public void setScheduleDate(String scheduleDate) { this.scheduleDate = scheduleDate; }
}
