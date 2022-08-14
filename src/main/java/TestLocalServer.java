//import javax.inject.Inject;
//import java.time.Duration;
//import java.time.LocalDate;
//import java.time.LocalDateTime;
//import java.time.format.DateTimeFormatter;
//import java.util.HashMap;
//
//public class TestLocalServer {
//    final static DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
//    static HashMap<LocalDate,HashMap<String, LocalDateTime>> checkInCheckOutTable = new HashMap<>();
//    static HashMap<LocalDate, HashMap<String,Integer>> report  = new HashMap<>();
//
//
//    public static void main(String[] args) {
//       checkIn("EMP1",LocalDateTime.parse("2020-02-02 08:00",dateTimeFormatter));
//       checkIn("EMP2",LocalDateTime.parse("2020-02-02 09:40",dateTimeFormatter));
//       checkIn("EMP3",LocalDateTime.parse("2020-02-03 08:30",dateTimeFormatter));
////       checkIn("EMP3",LocalDateTime.parse("2020-02-02 08:30",dateTimeFormatter));
////        checkOut("EMP2",LocalDateTime.parse("2020-02-02 09:00",dateTimeFormatter));
//
//        LocalDateTime t1 = checkInCheckOutTable.get(LocalDate.parse("2020-02-02")).get("EMP1");
//        LocalDateTime t2 = checkInCheckOutTable.get(LocalDate.parse("2020-02-02")).get("EMP2");
//
//        Duration dur = Duration.between(t1,t2);
//        dur.toMinutes();
//
//    }
//
//    public static void checkIn(String employeeId, LocalDateTime checkInTime){
//        validateCheckIn(employeeId,checkInTime.toLocalDate());
//        HashMap<String,LocalDateTime> currentDateMap = checkInCheckOutTable.get(checkInTime.toLocalDate());
//        if(currentDateMap == null){
//            currentDateMap = new HashMap<>();
//        }
//        currentDateMap.put(employeeId,checkInTime);
//        checkInCheckOutTable.put(checkInTime.toLocalDate(),currentDateMap);
//    }
//
//    public static void checkOut(String employeeId,LocalDateTime checkOutTime){
//        validateCheckOut(employeeId,checkOutTime);
//        checkInCheckOutTable.remove(employeeId);
//        LocalDate reportDate = checkOutTime.toLocalDate();
//        HashMap<String,Integer> employeeWorkingDuration = new HashMap<>();
//        employeeWorkingDuration.put(employeeId,10);
//        report.put(reportDate,employeeWorkingDuration);
//
//    }
//
//    private static void validateCheckIn(String employeeId,LocalDate checkInDate){
//        if(checkInCheckOutTable.containsKey(checkInDate) && checkInCheckOutTable.get(checkInDate).containsKey(employeeId))
//            throw new IllegalStateException("Employee has checked-in already");
//    }
//
//    private static void validateCheckOut(String employeeId, LocalDateTime checkOutTIme){
//        if(!checkInCheckOutTable.containsKey(employeeId))
//            throw new IllegalStateException("Employee not checked in");
//
////        if(checkOutTIme.isBefore(checkInCheckOutTable.get(employeeId)))
////            throw new IllegalStateException("Checkout time has to be after check in time");
//
//
//    }
//}
//
