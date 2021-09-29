package common;

import constants.TestData;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;
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

    public static String generateRandomName(){
        int n = 6;
        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                + "0123456789"
                + "abcdefghijklmnopqrstuvxyz";
        StringBuilder sb = new StringBuilder(n);
        for (int i = 0; i < n; i++) {
            int index
                    = (int)(AlphaNumericString.length()
                    * Math.random());
            sb.append(AlphaNumericString
                    .charAt(index));
        }
        return "Akm" + sb.toString();
    }

    public static String generateRandomLastName(){
        int n = 5;
        String AlphaNumericString = "ABCDEFGKLMNOPQRSTUVWXYZ"
                + "56789"
                + "abcdefghijklmnopuvxyz";
        StringBuilder sb = new StringBuilder(n);
        for (int i = 0; i < n; i++) {
            int index
                    = (int)(AlphaNumericString.length()
                    * Math.random());
            sb.append(AlphaNumericString
                    .charAt(index));
        }
        return "Sub" + sb.toString();
    }

    public static String getRandomName(){
            String[] name = TestData.name;
            int getLength = name.length;
            Random rn = new Random();
            int max = rn.nextInt(getLength);
            return name[max];
    }

    public static String getRandomLastName(){
        String[] name = TestData.surname;
        int getLength = name.length;
        Random rn = new Random();
        int max = rn.nextInt(getLength);
        return name[max];
    }

    public static String getRandomFlight (){
        String[] arrayOfFlights = TestData.flightCodes;
        Random r =new Random();
        int randomNumber=r.nextInt(arrayOfFlights.length);
        return arrayOfFlights[randomNumber];
    }

}
