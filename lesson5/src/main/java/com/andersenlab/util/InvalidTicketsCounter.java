package com.andersenlab.util;

import com.andersenlab.entity.BusTicket;

import java.lang.reflect.Method;
import java.util.List;

public class InvalidTicketsCounter {
    public static int countInvalidTickets(Validator obj, String methodName, List<BusTicket> inputList) {
        try {
            Method method = obj.getClass().getMethod(methodName, List.class);
            List<BusTicket> outputList = (List<BusTicket>) method.invoke(obj, inputList);
            return inputList.size() - outputList.size();
        } catch (Exception e) {
            throw new RuntimeException("Couldn't count invalid tickets!", e);
        }
    }
}

