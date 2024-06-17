package com.andersenlab;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    private static final String PATH = "lesson5/src/main/resources/tickets.txt";

    public static void main(String[] args) {
        Mapper mapper = new Mapper();
        Validator validator = new Validator();
        List<String> stringTickets = getFileInput(PATH);
        List<BusTicket> tickets = mapper.mapStringListToTicketList(stringTickets);
        List<BusTicket> validTickets = validator.validatePriceExisting(
                validator.validateStartDateExisting(tickets));
        int numberOfStartDateViolation =
                tickets.size() - validator.validateStartDateExisting(tickets).size();
        int numberOfPriceViolation =
                tickets.size() - validator.validatePriceExisting(tickets).size();

        System.out.printf(" Total = {%s}\n Valid = {%s}\n ", tickets.size(), validTickets.size());
        System.out.println("Most popular violation = " +
                (numberOfPriceViolation < numberOfStartDateViolation ? "start date" : "price"));

    }

    private static List<String> getFileInput(String path) {
        List<String> list = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = br.readLine()) != null) {
                list.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }
}



