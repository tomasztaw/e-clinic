package pl.taw.controller.business;

import lombok.*;

import java.time.LocalTime;

@Data
@With
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WorkingHours {

    private DayOfWeek dayOfWeek;
    private LocalTime startTime;
    private LocalTime endTime;

    public enum DayOfWeek {
        MONDAY(1, "Poniedziałek"),
        TUESDAY(2, "Wtorek"),
        WEDNESDAY(3, "Środa"),
        THURSDAY(4, "Czwartek"),
        FRIDAY(5, "Piątek"),
        SATURDAY(6, "Sobota"),
        SUNDAY(7, "Niedziela");

        private final int number;
        private final String name;

        DayOfWeek(int number, String name) {
            this.number = number;
            this.name = name;
        }

        public int getNumber() {
            return number;
        }

        public String getName() {
            return name;
        }

        public static DayOfWeek fromInt(int numer) {
            for (DayOfWeek day : DayOfWeek.values()) {
                if (day.getNumber() == numer) {
                    return day;
                }
            }
            throw new IllegalArgumentException("Invalid day of the week number: " + numer);
        }
    }

}
