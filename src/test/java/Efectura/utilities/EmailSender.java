package Efectura.utilities;

import jakarta.mail.*;
import jakarta.mail.internet.*;
import java.util.Properties;

public class EmailSender {

    private static final String SENDER_EMAIL = "efectura.report@gmail.com";  // Gönderici mail
    private static final String SENDER_PASSWORD = "efectura1667";   // Gmail App Şifresi

    public static void sendEmail(String recipientEmail, String subject, String messageText) {
        // SMTP Ayarları
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        // Oturum Aç
        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(SENDER_EMAIL, SENDER_PASSWORD);
            }
        });

        try {
            // Mail İçeriği
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(SENDER_EMAIL));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientEmail));
            message.setSubject(subject);
            message.setText(messageText);

            // Maili Gönder
            message.saveChanges();  // MIME başlıklarını günceller
            Transport.send(message);
            System.out.println("Mail başarıyla gönderildi!");
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
