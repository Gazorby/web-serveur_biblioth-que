package library;

import exceptions.AlreadyInMailingList;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class Library {

    // Using singleton pattern
    private static Library uniqueInstance = null;
    private static ConcurrentMap<Integer, Document> documents = new ConcurrentHashMap<>();
    private static ConcurrentMap<Integer, List<Subscriber>> alerts = new ConcurrentHashMap<>();
    private static ConcurrentMap<Integer, Subscriber> subscribers = new ConcurrentHashMap<>();

    /*
     * Create some subscribers for testing
     */
    static {
        subscribers.put(1, new Subscriber(1, "mmacnab22@gmail.com"));
        subscribers.put(2, new Subscriber(2, "xavdeverdun@gmail.com"));
        subscribers.put(3, new Subscriber(3, "lyx@outlook.fr"));
    }

    /*
     * Create some documents for testing
     */
    static {
        documents.put(1, new Book(1));
        documents.put(2, new Book(2));
        documents.put(3, new Book(3));
    }

    private Library() {
    }

    /**
     * GetInstance method needed for the singleton pattern
     * @return the same instance of {@link Library} and instantiate it if there's none
     */
    public static synchronized Library getInstance() {
        if (uniqueInstance == null) {
            return new Library();
        }

        else {
            return uniqueInstance;
        }
    }

    /**
     * Get the document corresponding to the given ID
     * @param num, document ID
     * @return a {@link Document} corresponding the ID
     */
    public Document getDocument(int num) {
        return documents.get(num);
    }

    /**
     * Get the subscriber corresponding to the given ID
     * @param num, the subscriber ID
     * @return a {@link Subscriber} corresponding to the given ID
     */
    public Subscriber getSubscriber(int num) {
        return subscribers.get(num);
    }

    /**
     * Add a subscriber to the alert list
     * @param subscriber, {@link Subscriber} to be added
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
     * Call sendEmail() for all subscribers mapping the given document ID in the alert map
     * @param docNum , the documet ID
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
            synchronized (this){
                Transport.send(message);
            }

            System.out.println("Sent message successfully....");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
