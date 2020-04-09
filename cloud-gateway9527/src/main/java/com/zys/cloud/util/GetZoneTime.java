package com.zys.cloud.util;

import java.time.ZonedDateTime;

public class GetZoneTime {
    public static void main(String[] args) {
        getTime();
    }

    public static void  getTime(){
        ZonedDateTime zbj = ZonedDateTime.now();
        System.out.println(zbj);
    }
}
