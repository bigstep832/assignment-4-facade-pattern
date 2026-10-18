import java.util.ArrayList;
import java.util.List;

public class NotificationService {
    private final List<String> messages = new ArrayList<>();

    public String confirm(String customer, String shipmentId, int total) {
        String message = "Order confirmed for " + customer
                + ": " + shipmentId + ", total " + total + " KZT.";
        messages.add(message);
        return message;
    }

    public List<String> getMessages() {
        return List.copyOf(messages);
    }
}
