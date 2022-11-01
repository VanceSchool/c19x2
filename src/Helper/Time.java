/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Helper;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.sql.Timestamp;
import java.time.*;
import static java.time.LocalDateTime.now;
import java.time.ZonedDateTime;




/**
 *
 *@author sean thompson <stho292@wgu.edu>
 */
public class Time {


    public LocalDateTime dateTime;
    
    
    public static ZoneId getZoneId(){
     ZoneId zone = ZoneId.systemDefault();
     return zone;
    }
    public static LocalDateTime getNowLocalDateTime(){
        LocalDateTime ldtn = LocalDateTime.now();
        return ldtn;
    }
    public static LocalTime getNowLocaltime(){
        LocalTime ltn = LocalTime.now();
        return ltn;
    }
    public static LocalDate getNowLocalDate(){
        LocalDate ldn = LocalDate.now();
        return ldn;
    }
    public static LocalDateTime changeUpLocaleDateTime(LocalDate pDate, LocalTime pTime){
        LocalDateTime combineDateTime;
        combineDateTime = LocalDateTime.of(pDate, pTime);
        return combineDateTime;
    }
    public ZonedDateTime changeToUTC(LocalDateTime ldt){
        ZonedDateTime ldtZone;
        ldtZone = ZonedDateTime.of(dateTime, ZoneId.systemDefault());
        ZonedDateTime utcZoneTime = ldtZone.withZoneSameInstant(ZoneOffset.UTC);
        
        return utcZoneTime;
    }
    public static Timestamp changeToTimeStamp(LocalDateTime ldt){
        Timestamp ts;
        ts = Timestamp.valueOf(ldt);
        return ts;
    }
    public static LocalDateTime localTimePlus15(LocalDateTime someTime){
       LocalDateTime plusFifteen = someTime.plusMinutes(30);
       return plusFifteen;
    }
}
