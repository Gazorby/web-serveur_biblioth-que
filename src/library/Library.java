package library;

import exceptions.AlreadyInMailingList;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.*;

public class Library {

    // Using singleton pattern
    private static Library uniqueInstance = null;
    private static Map<Integer, Document> documents = new HashMap<>();
    private static Map<Integer, List<Subscriber>> alerts = new HashMap<>();

    private static Map<Integer, Subscriber> subscribers = new HashMap<>();

    static {
        subscribers.put(1, new Subscriber(1, "mmacnab22@gmail.com"));
        subscribers.put(2, new Subscriber(2, "xavdeverdun@gmail.com"));
        subscribers.put(3, new Subscriber(3, "mmacnab22@gmail.com"));
    }

    static {
        documents.put(1, new Book(1));
        documents.put(2, new Book(2));
        documents.put(3, new Book(3));
    }

    private Library() {
    }

    public static synchronized Library getInstance() {
        if (uniqueInstance == null) {
            return new Library();
        }

        else {
            return uniqueInstance;
        }
    }

    public synchronized Document getDocument(int num) {
        return documents.get(num);
    }

    public synchronized Subscriber getSubscriber(int num) {
        return subscribers.get(num);
    }

    /**
     * Add a subscriber to the alert list
     * @param subscriber, subscriber to be added
     */
    public void addAlert(Document document, Subscriber subscriber) throws AlreadyInMailingList {
        if (alerts.get(document.getNum()) != null) {

            if (alerts.get(document.getNum()).contains(subscriber)) {
                throw new AlreadyInMailingList(subscriber);
            }

            else {
                alerts.get(document.getNum()).add(subscriber);
            }
        }

        else {
            List<Subscriber> subList = new ArrayList<>();
            subList.add(subscriber);
            alerts.put(document.getNum(), subList);
        }
    }

    /**
     *
     */
    public void sendAlert(int docNum) {

        if (alerts.get(docNum) != null) {

            for (Subscriber s : alerts.get(docNum)) {
                sendEmail(s, documents.get(docNum));
            }
        }
    }

    /**
     * Send an email notification to receiver Subscriber about the document given
     * Here we are using javamail API to send email, using a mailjet smtp server
     * @param receiver, the subscriber to notify
     * @param document, the document back in the library
     */
    private void sendEmail(Subscriber receiver, Document document) {

        // Sender's email ID needs to be mentioned
        String from = "mmacnab22@gmail.com";
        final String username = "192f081aa18a995b97ecf8cd96450d4a";//change accordingly
        final String password = "4fed317bfb027a0fa9c415ee340b46de";//change accordingly

        // Assuming we are sending email through in-v3.mailjet.com
        String host = "in-v3.mailjet.com";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", "465");
        props.put("mail.smtp.ssl.enable", "true");

        // Get the Session object.
        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {

                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {
            // Create a default MimeMessage object.
            Message message = new MimeMessage(session);

            // Set From: header field of the header.
            message.setFrom(new InternetAddress(from));

            // Set To: header field of the header.
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(receiver.getMail()));

            // Set Subject: header field
            message.setSubject(String.format("Document n° %d is back", document.getNum()));

            // Now set the actual message
            message.setText(String.format("Hello, subscriber n° %d the Document n° %d is back !", receiver.getNum(), document.getNum()));

            // Send message
            Transport.send(message);

            System.out.println("Sent message successfully....");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
