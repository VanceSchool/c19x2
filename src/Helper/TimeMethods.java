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
import static java.time.ZoneOffset.UTC;
import java.time.ZonedDateTime;




/**
 *
 *@author sean thompson <stho292@wgu.edu>
 */
public class TimeMethods {


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
        ldtZone = ZonedDateTime.of(ldt, ZoneId.systemDefault());
        ZonedDateTime utcZoneTime = ldtZone.withZoneSameInstant(ZoneOffset.UTC);
        
        return utcZoneTime;
    }
    
        public static LocalDateTime changeToUST(LocalDateTime lD){
        //Set LocalDateTime to ZonedDateTime of current Zone
        ZonedDateTime nowzdt = lD.atZone(ZoneId.of(ZoneId.systemDefault().toString()));
        //Convert ZonedDateTime to EST Date Time
        ZonedDateTime ustzdt = nowzdt.withZoneSameInstant(ZoneId.of("UTC"));
        //Convert ZonedDateTime of EST to LocalDateTime
        LocalDateTime ustldt = ustzdt.toLocalDateTime();
        return ustldt;   
    }
        
    public static LocalDateTime changeToEst(LocalDate ld, LocalTime lt){

        //Convert local Date and Local time values to LocalDateTime
        LocalDateTime ldt;
        ldt = LocalDateTime.of(ld, lt);
        //Set LocalDateTime to ZonedDateTime of current Zone
        ZonedDateTime nowzdt = ldt.atZone(ZoneId.of(ZoneId.systemDefault().toString()));
        //Convert ZonedDateTime to EST Date Time
        ZonedDateTime estzdt = nowzdt.withZoneSameInstant(ZoneId.of("America/New_York"));
        //Convert ZonedDateTime of EST to LocalDateTime
        LocalDateTime estldt = estzdt.toLocalDateTime();
        return estldt;   
    }
    
    public static Timestamp changeToTimeStamp(LocalDateTime ldt){
        Timestamp ts;
        ts = Timestamp.valueOf(ldt);
        return ts;
    }
    

    
    public static LocalDateTime localTimePlus15(LocalDateTime someTime){
       LocalDateTime plusFifteen = someTime.plusMinutes(15);
       return plusFifteen;
    }
    
        public static LocalDateTime localTimeMinusFifteen(LocalDateTime someTime){
       LocalDateTime minusFifteen = someTime.minusMinutes(15);
       return minusFifteen;
    }
}
