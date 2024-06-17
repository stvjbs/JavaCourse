package com.andersenlab;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

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


}
