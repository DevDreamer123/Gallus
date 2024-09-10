package in.innovaneers.gallus.model;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class MessageModel {
    private String body;
    private long timestamp;
    private boolean sentByCurrentUser;

    public MessageModel(String body, long timestamp, boolean sentByCurrentUser) {
        this.body = body;
        this.timestamp = timestamp;
        this.sentByCurrentUser = sentByCurrentUser;
    }

    public String getBody() {
        return body;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public boolean isSentByCurrentUser() {
        return sentByCurrentUser;
    }

    public String getFormattedTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm", Locale.getDefault());
        return sdf.format(new Date(timestamp));
    }
    public String getFormattedDate(){
        SimpleDateFormat sdfDate = new SimpleDateFormat("d MMM", Locale.getDefault());
        Date messageDate = new Date(timestamp);

        Calendar messageCalendar = Calendar.getInstance();
        messageCalendar.setTime(messageDate);
        Calendar todayCalendar = Calendar.getInstance();
        Calendar yesterdayCalendar = Calendar.getInstance();
        yesterdayCalendar.add(Calendar.DAY_OF_YEAR, -1);

        if (isSameDay(messageCalendar, todayCalendar)) {
            return "Today";
        } else if (isSameDay(messageCalendar, yesterdayCalendar)) {
            return "Yesterday";
        } else {
            return sdfDate.format(messageDate);
        }
    }

    private boolean isSameDay(Calendar cal1, Calendar cal2) {
        return cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR) &&
                cal1.get(Calendar.DAY_OF_YEAR) == cal2.get(Calendar.DAY_OF_YEAR);
    }
}

