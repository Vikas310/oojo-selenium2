package common;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.logging.Logger;

public class Helper {

    public Logger logWrite = Logger.getLogger("Logger activated");

    public static String getDateWithSpecificMonthsInFuture(int months, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MONTH, months);
        String newDate = sdf.format(cal.getTime());
        return newDate;
    }

    public static String getFlightSearchResultOneWay(String fromLocation, String toLocation, String dateFrom){
       return "/result/"+fromLocation+"-"+toLocation+"/"+dateFrom;
    }

    public static String getFlightSearchResultRoundTrip(String fromLocation, String toLocation, String dateFrom, String dateTo){
        return "result/"+fromLocation+"-"+toLocation+"/"+dateFrom;
    }
}
