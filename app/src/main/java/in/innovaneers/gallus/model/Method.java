package in.innovaneers.gallus.model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class Method {
    public static String UnixToDate(String unixDate)
    {
        unixDate = unixDate.substring(6,19);




        long unixSeconds = 1372339860;

        unixSeconds =  Long.valueOf(unixDate);
// convert seconds to milliseconds
        Date date = new Date(unixSeconds*1L + 3600000L);
// the format of your date
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
// give a timezone reference for formatting (see comment at the bottom)
        sdf.setTimeZone(TimeZone.getTimeZone("GMT+00:00"));
        String formattedDate = sdf.format(date);
        return formattedDate;
    }
}
