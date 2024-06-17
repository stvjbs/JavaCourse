package com.andersenlab;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Mapper {
    public List<BusTicket> mapStringListToTicketList(List<String> stringList) {
        List<BusTicket> ticketList = new ArrayList<BusTicket>();
        stringList.forEach(x-> ticketList.add(mapStringToTicket(x)));
        return ticketList;
    }

    private BusTicket mapStringToTicket(String string) {
        try {
            return new ObjectMapper().readValue(string, BusTicket.class);
        }
        catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        throw new RuntimeException();
    }

    public static LocalDate mapStringToDate(String string) {
            String[] date = string.split("-");
            return LocalDate.of(Integer.parseInt(date[0]), Integer.parseInt(date[1]), Integer.parseInt(date[2]));
    }

    public static int mapStringToInt(String string) {
        return Integer.parseInt(string);
    }

}
