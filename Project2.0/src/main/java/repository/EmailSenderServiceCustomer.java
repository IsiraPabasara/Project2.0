package repository;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailSenderServiceCustomer {

    private final JavaMailSender mailSender;

    public EmailSenderServiceCustomer(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendShipmentEmail(String toEmail, String customerName, String parcelId, String deliveryDate) {
        String body = String.format("""
                Dear %s,

                Your shipment #%s is currently "In Transit".
                Estimated Delivery Date: %s.
                No delays at the moment.

                Thank you,
                FastTrack Logistics
                """, customerName, parcelId, deliveryDate);

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("youremail@example.com"); // Replace with your actual sender email
        message.setTo(toEmail);
        message.setSubject("Shipment Status Update");
        message.setText(body);

        mailSender.send(message);
    }
}
