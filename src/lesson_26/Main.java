package lesson_26;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.IsoFields;
import java.time.temporal.TemporalField;
import java.time.temporal.WeekFields;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        Set<String> timezones = ZoneId.getAvailableZoneIds();
        for (String timezone : timezones) {
            ZonedDateTime zonedDateTime = ZonedDateTime.of(LocalDateTime.now(), ZoneId.of(timezone));
            System.out.println(zonedDateTime);

            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yy OOOO HH:mm");
            System.out.println(dateTimeFormatter.format(zonedDateTime));
            break;
        }

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd=MM/yy HH:mm");
        String date = "14=04/19 18:22";
        LocalDateTime ldt = LocalDateTime.parse(date, dtf);
        System.out.println(ldt);
    }

    private static void localDateTimeExample() {
        LocalDate localDate = LocalDate.of(2050, 12, 21);

        LocalDate inThePast = localDate.minusDays(10);

        // Buying a ticket which is valid for 3 weeks
        LocalDate ticketValidUntil = localDate.plusWeeks(3);

        LocalTime localTime = LocalTime.now();
        LocalTime previously = localTime.minusHours(5);

        LocalDateTime localDateTime = LocalDateTime.of(localDate, previously);
        System.out.println(localDateTime);

        Instant instant = Instant.now();
    }

    private static void calendarExample() {
        Calendar calendar = Calendar.getInstance();

        Calendar calendar1 = new GregorianCalendar(2020, Calendar.APRIL, 10);

        calendar.set(Calendar.YEAR, 2002);

        System.out.println(calendar.get(Calendar.YEAR));
        System.out.println(calendar.get(Calendar.WEEK_OF_YEAR));
    }

    private static void regularDateExample() {
        Date date = new Date();

        // 04.04.2024 18:16
        DateFormat dateFormat = new SimpleDateFormat("EEE d.MMM HH:mm:ss:SSS");

        System.out.println(dateFormat.format(date));
    }
}
