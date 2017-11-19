
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;


public class Main {

    final static String TAG_SEEN = "Last seen ";
    final static String TAG_YESTERDAY = "Yesterday ";
    final static String TAG_SEC = " seconds ago ";
    final static String TAG_MINUTE = " minute ago ";
    final static String TAG_MINUTES = " minutes ago ";
    final static String TAG_HOUR = " hour ";
    final static String TAG_HOURS = " hours ";
    final static String TAG_DAY = " day ago ";
    final static String TAG_DAYS = " days ago ";
    final static String TAG_MONTH = " month ago ";
    final static String TAG_MONTHS = " months ago ";
    final static String TAG_YEAR = " year ago ";
    final static String TAG_YEARS = " years ago ";

    public static void main(String[] args) {


        // uses this pattern for time from hh:mm and date yyyy-mm-dd
        System.out.println(dateAlgorithem(splitTime(generalTime("22:45")), splitDate("2017-11-15")));
    }


    public static String[] splitTime(String time) {

        String[] timeAndPm = arabicToDecimal(time).split(":");

        return timeAndPm;
    }

    public static String[] splitDate(String getDate) {

        String[] date = getDate.split("-");

        return date;
    }

    public static String dayName(String[] inputDate) {


        String dateString = inputDate[0] + "-" + inputDate[1] + "-" + inputDate[2];
        Date date = null;
        try {
            date = new SimpleDateFormat("yyyy-MM-dd").parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return new SimpleDateFormat("EEEE", Locale.ENGLISH).format(date);
    }


    public static String dateAlgorithem(String[] time, String[] date) {

        /**
         * same year
         */
        if (date[0].equals(splitDate(GetTime.getDate())[0])) {

            /**
             * same month
             */
            if (date[1].equals(splitDate(GetTime.getDate())[1])) {

                /**
                 * same day
                 */
                if (date[2].equals(splitDate(GetTime.getDate())[2])) {

                    /**
                     * same hour
                     */
                    if (time[0].equals(splitTime(generalTime(GetTime.getTime()))[0])) {

                        if (Integer.parseInt(splitTime(generalTime(GetTime.getTime()))[1]) - Integer.parseInt(time[1]) > 0) {

                            return TAG_SEEN + String.valueOf(Integer.parseInt(splitTime(generalTime(GetTime.getTime()))[1]) - Integer.parseInt(time[1])) + TAG_MINUTES;

                        } else if (Integer.parseInt(splitTime(generalTime(GetTime.getTime()))[1]) - Integer.parseInt(time[1]) < 0) {

                            return TAG_SEEN + String.valueOf(60 + Integer.parseInt(splitTime(generalTime(GetTime.getTime()))[1]) - Integer.parseInt(time[1])) + TAG_MINUTES;

                        } else if (Integer.parseInt(splitTime(generalTime(GetTime.getTime()))[1]) - Integer.parseInt(time[1]) == 0) {

                            return TAG_SEEN + TAG_SEC;

                        }
                    }
                    /**
                     * Not same hour
                     */
                    else if (!time[0].equals(splitTime(generalTime(GetTime.getTime()))[0])) {

                        if (Integer.parseInt(splitTime(generalTime(GetTime.getTime()))[0]) - Integer.parseInt(time[0]) > 1) {

                            if (Integer.parseInt(splitTime(generalTime(GetTime.getTime()))[1]) - Integer.parseInt(time[1]) > 0) {

                                return TAG_SEEN + String.valueOf(Integer.parseInt(splitTime(generalTime(GetTime.getTime()))[0]) - Integer.parseInt(time[0])) + TAG_HOURS + String.valueOf(Integer.parseInt(splitTime(generalTime(GetTime.getTime()))[1]) - Integer.parseInt(time[1])) + TAG_MINUTES;

                            } else if (Integer.parseInt(splitTime(generalTime(GetTime.getTime()))[1]) - Integer.parseInt(time[1]) < 0) {

                                /**
                                 * check hours or hour
                                 */
                                if (Integer.parseInt(splitTime(generalTime(GetTime.getTime()))[0]) - Integer.parseInt(time[0]) - 1 > 1) {

                                    return TAG_SEEN + String.valueOf(Integer.parseInt(splitTime(generalTime(GetTime.getTime()))[0]) - Integer.parseInt(time[0]) - 1) + TAG_HOURS + String.valueOf(60 + Integer.parseInt(splitTime(generalTime(GetTime.getTime()))[1]) - Integer.parseInt(time[1])) + TAG_MINUTES;

                                } else {

                                    return TAG_SEEN + String.valueOf(Integer.parseInt(splitTime(generalTime(GetTime.getTime()))[0]) - Integer.parseInt(time[0]) - 1) + TAG_HOUR + String.valueOf(60 + Integer.parseInt(splitTime(generalTime(GetTime.getTime()))[1]) - Integer.parseInt(time[1])) + TAG_MINUTES;

                                }

                            } else if (Integer.parseInt(splitTime(generalTime(GetTime.getTime()))[1]) - Integer.parseInt(time[1]) == 0) {

                                return TAG_SEEN + String.valueOf(Integer.parseInt(splitTime(generalTime(GetTime.getTime()))[0]) - Integer.parseInt(time[0])) + TAG_HOURS;

                            }


                        } else if (Integer.parseInt(splitTime(generalTime(GetTime.getTime()))[0]) - Integer.parseInt(time[0]) < 1) {

                            return TAG_SEEN + String.valueOf(12 + Integer.parseInt(splitTime(generalTime(GetTime.getTime()))[0]) - Integer.parseInt(time[0])) + TAG_HOURS;

                        } else if (Integer.parseInt(splitTime(generalTime(GetTime.getTime()))[0]) - Integer.parseInt(time[0]) == 1) {

                            /**
                             * number of minutes less than 60 or equal
                             */
                            if (Integer.parseInt(splitTime(generalTime(GetTime.getTime()))[1]) - Integer.parseInt(time[1]) > 0) {


                                return TAG_SEEN + String.valueOf(Integer.parseInt(splitTime(generalTime(GetTime.getTime()))[0]) - Integer.parseInt(time[0])) + TAG_HOURS;


                            } else if (Integer.parseInt(splitTime(generalTime(GetTime.getTime()))[1]) - Integer.parseInt(time[1]) < 0) {

                                return TAG_SEEN + String.valueOf(60 + Integer.parseInt(splitTime(generalTime(GetTime.getTime()))[1]) - Integer.parseInt(time[1])) + TAG_MINUTE;

                            } else if (Integer.parseInt(splitTime(generalTime(GetTime.getTime()))[1]) - Integer.parseInt(time[1]) == 0) {

                                return TAG_SEEN + String.valueOf(Integer.parseInt(splitTime(generalTime(GetTime.getTime()))[0]) - Integer.parseInt(time[0])) + TAG_HOURS;

                            }

                        }
                    }

                    /**
                     * Not same day and 1 : 2
                     */
                } else if (!date[2].equals(splitDate(GetTime.getDate())[2]) && Integer.parseInt(splitDate(GetTime.getDate())[2]) - Integer.parseInt(date[2]) == 1) {

                    /**
                     * if result >0 is day
                     */
                    if (Integer.parseInt(splitTime(generalTime(GetTime.getTime()))[0]) - Integer.parseInt(time[0]) > 0) {

                        if (Integer.parseInt(splitDate(GetTime.getDate())[2]) - Integer.parseInt(date[2]) > 1) {


                            return TAG_SEEN + String.valueOf(Integer.parseInt(splitDate(generalTime(GetTime.getDate()))[2]) - Integer.parseInt(date[2])) + TAG_DAYS + "( " + dayName(date) + " )" + " at " + time[0] + ":" + time[1];

                        } else if (Integer.parseInt(splitDate(GetTime.getDate())[2]) - Integer.parseInt(date[2]) == 1) {

                            return TAG_SEEN + TAG_YESTERDAY + "( " + dayName(date) + " )" + " at " + time[0] + ":" + time[1];

                        }

                        /**
                         * if result =0 is day
                         */
                    } else if (Integer.parseInt(splitTime(generalTime(GetTime.getTime()))[0]) - Integer.parseInt(time[0]) == 0) {

                        if (Integer.parseInt(splitDate(GetTime.getDate())[2]) - Integer.parseInt(date[2]) == 1) {

                            return TAG_SEEN + TAG_YESTERDAY + "( " + dayName(date) + " )" + " at " + time[0] + ":" + time[1];

                        } else {

                            return TAG_SEEN + String.valueOf(Integer.parseInt(splitDate(GetTime.getDate())[2]) - Integer.parseInt(date[2])) + TAG_DAY + "( " + dayName(date) + " )" + " at " + time[0] + ":" + time[1];

                        }
                    }
                    /**
                     * if result <0 is hours
                     */
                    else if (Integer.parseInt(splitTime(generalTime(GetTime.getTime()))[0]) - Integer.parseInt(time[0]) < 0) {

                        if (Integer.parseInt(splitTime(generalTime(GetTime.getTime()))[0]) - Integer.parseInt(time[0]) < 1) {

                            if (Integer.parseInt(splitTime(generalTime(GetTime.getTime()))[1]) - Integer.parseInt(time[1]) > 0) {

                                return TAG_SEEN + String.valueOf(24 + Integer.parseInt(splitTime(generalTime(GetTime.getTime()))[0]) - Integer.parseInt(time[0])) + TAG_HOURS + String.valueOf(Integer.parseInt(splitTime(generalTime(GetTime.getTime()))[1]) - Integer.parseInt(time[1])) + TAG_MINUTES;

                            } else if (Integer.parseInt(splitTime(generalTime(GetTime.getTime()))[1]) - Integer.parseInt(time[1]) < 0) {

                                return TAG_SEEN + String.valueOf(24 + Integer.parseInt(splitTime(generalTime(GetTime.getTime()))[0]) - Integer.parseInt(time[0])) + TAG_HOURS + String.valueOf(60 + Integer.parseInt(splitTime(generalTime(GetTime.getTime()))[1]) - Integer.parseInt(time[1])) + TAG_MINUTES;

                            }

                        }
                    }
                }
                /**
                 * Not same day
                 */
                else if (!date[2].equals(splitDate(GetTime.getDate())[2]) && Integer.parseInt(splitDate(GetTime.getDate())[2]) - Integer.parseInt(date[2]) <= 3) {

                    if (Integer.parseInt(splitDate(GetTime.getDate())[2]) - Integer.parseInt(date[2]) > 1) {

                        return TAG_SEEN + String.valueOf(Integer.parseInt(splitDate(GetTime.getDate())[2]) - Integer.parseInt(date[2])) + TAG_DAYS + "( " + dayName(date) + " )" + " at " + time[0] + ":" + time[1];

                    } else {

                        return TAG_SEEN + String.valueOf(Integer.parseInt(splitDate(GetTime.getDate())[2]) - Integer.parseInt(date[2])) + TAG_DAY + "( " + dayName(date) + " )" + " at " + time[0] + ":" + time[1];

                    }
                } else {

                    if (Integer.parseInt(splitDate(GetTime.getDate())[2]) - Integer.parseInt(date[2]) > 1) {

                        return TAG_SEEN + String.valueOf(Integer.parseInt(splitDate(GetTime.getDate())[2]) - Integer.parseInt(date[2])) + TAG_DAYS + "( " + dayName(date) + " )" + " at " + time[0] + ":" + time[1];


                    } else {

                        return TAG_SEEN + String.valueOf(Integer.parseInt(splitDate(GetTime.getDate())[2]) - Integer.parseInt(date[2])) + TAG_DAYS + "( " + dayName(date) + " )" + " at " + time[0] + ":" + time[1];


                    }
                }


            }
            if (!date[1].equals(splitDate(GetTime.getDate())[1])) {
                return TAG_SEEN + date[0] + "-" + date[1] + "-" + date[2] + " at " + time[0] + ":" + time[1];
            }
        }

        return "";
    }

    public static String getTime() {

        SimpleDateFormat f = new SimpleDateFormat("HH:mm");

        f.setTimeZone(TimeZone.getTimeZone("UTC"));

        //return initTime();
        return ArabicNumbers.arabicToDecimal(f.format(new Date()));
    }


    public static String proccessDate(String time, String date) {

        String[] splitTime = time.split(":");

        if (getDeviceHour() > getUTCHour()) {

            if (checkHour(Integer.parseInt(splitTime[0])) + cTime() > 24) {

                SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
                f.setTimeZone(TimeZone.getTimeZone("UTC"));

                Calendar c = Calendar.getInstance();
                try {
                    c.setTime(f.parse(date));
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                c.add(Calendar.DATE, 1);  // number of days to add
                date = f.format(c.getTime());  //
                //increase day
                return date;

            } else {

                return date;

            }
        } else if (getUTCHour() > getDeviceHour()) {

            if (checkHour(Integer.parseInt(splitTime[0])) + cTime() < 24) {

                SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
                f.setTimeZone(TimeZone.getTimeZone("UTC"));

                Calendar c = Calendar.getInstance();
                try {
                    c.setTime(f.parse(date));
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                c.add(Calendar.DATE, -1);  // number of days to add
                date = f.format(c.getTime());  //
                //increase day
                return date;

            } else {

                return date;

            }

        } else {

            return date;
        }
    }

    private static int checkHour(int hour) {

        if (hour == 0) {

            return 24;

        } else {

            return hour;
        }
    }

    public static String getUTCDate() {

        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");

        f.setTimeZone(TimeZone.getTimeZone("UTC"));

        return ArabicNumbers.arabicToDecimal(f.format(new Date()));

    }

    public static String getTime_Minute() {

        SimpleDateFormat f = new SimpleDateFormat("mm");

        f.setTimeZone(TimeZone.getTimeZone("UTC"));


        return ArabicNumbers.arabicToDecimal(f.format(new Date()));

    }

    public static int getDeviceHour() {

        return Integer.parseInt(ArabicNumbers.arabicToDecimal(new SimpleDateFormat("HH").format(Calendar.getInstance().getTime())));
    }

    public static int getUTCHour() {

        SimpleDateFormat f = new SimpleDateFormat("HH");

        f.setTimeZone(TimeZone.getTimeZone("UTC"));

        f.format(new Date());

        return Integer.parseInt(ArabicNumbers.arabicToDecimal(f.format(new Date())));
    }


    public static String initTime() {

        return calculateTime().concat(":").concat(getTime_Minute());

    }

    public static String generalTime(String server_time) {

        String[] time = server_time.split(":");

        int sum = checkHour(Integer.parseInt(ArabicNumbers.arabicToDecimal(time[0]))) + cTime();


        if (sum <= 24 && sum > 0) {

            return String.valueOf(sum) + ":" + ArabicNumbers.arabicToDecimal(time[1]);

        } else if (sum > 24) {

            return String.valueOf(sum - 24).concat(":").concat(ArabicNumbers.arabicToDecimal(time[1]));

        } else if (sum < 0) {

            return String.valueOf(sum + 24) + ":" + ArabicNumbers.arabicToDecimal(time[1]);

        } else if (sum == 0) {

            return String.valueOf(initDate(sum)) + ":" + ArabicNumbers.arabicToDecimal(time[1]);

        }

        return "";
    }

    public static int cTime() {

        return getDeviceHour() - getUTCHour();
    }

    public static String calculateTime() {


        if (getDeviceHour() > getUTCHour()) {

            int sum = getDeviceHour() - getUTCHour();

            return String.valueOf(sum + getUTCHour());

        } else if (getDeviceHour() < getUTCHour()) {

            int sum = getUTCHour() - getDeviceHour();

            return String.valueOf(getUTCHour() - sum);

        } else {

            return String.valueOf(getUTCHour());
        }

    }

    public static String getDate() {

        return proccessDate();

    }

    public static int initDate(int time) {

        if (time == 0) {

            return 24;

        } else {

            return time;
        }

    }

    private static String proccessDate() {


        //مش مظبوطة
        if (initDate(getDeviceHour()) > initDate(getUTCHour())) {

            return getUTCDate();

        } else if (initDate(getDeviceHour()) < initDate(getUTCHour())) {

            return getDateEX();

        } else {

            return getDateEX();
        }

    }

    public static String getDateEX() {

        return ArabicNumbers.arabicToDecimal(new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime()));
    }

    public static String arabicToDecimal(String number) {

        char[] chars = new char[number.length()];

        for (int i = 0; i < number.length(); i++) {
            char ch = number.charAt(i);
            if (ch >= 0x0660 && ch <= 0x0669)
                ch -= 0x0660 - '0';
            else if (ch >= 0x06f0 && ch <= 0x06F9)
                ch -= 0x06f0 - '0';
            chars[i] = ch;
        }
        return new String(chars);
    }
}
