/*
A0259416X
Lee Yi Lin
Lab Group 3 
TA: Ramapriyan
*/ 

import java.io.*;
import java.util.*;
public class traveltheskies {

    public static class Flight {
        int day;
        int capacity;
        int starting;
        int ending; 

        public Flight(int starting, int ending, int day, int capacity) {
            this.starting = starting;
            this.ending = ending;
            this.capacity = capacity;
            this.day = day;
        }
    }

    public static class Customer {
        int day;
        int size;
        int airport;

        public Customer(int airport, int day, int size) {
            this.day = day;
            this.size = size;
            this.airport = airport;
        }
    }

    public static class Airport {
        int numCustomers;
        int lagCustomers;

        public Airport() {
            this.numCustomers = 0;
            this.lagCustomers = 0;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int k = Integer.parseInt(st.nextToken()); // number of airports 
        int n = Integer.parseInt(st.nextToken()); // number of days of the flight departure window
        int m = Integer.parseInt(st.nextToken()); // number of flights in the window 

        Airport[] MapAirport = new Airport[k+1]; // airport number and airport object
        for (int i = 1; i < k+1; i++) {
            MapAirport[i] = new Airport();
        }
        
        PriorityQueue<Flight> lst = new PriorityQueue<>(new Comparator<Flight>() { // maintain flight queue based on days 
            public int compare(Flight f1, Flight f2) {
                return f1.day > f2.day? 1 : -1;
            }
        });

        PriorityQueue<Customer> customers = new PriorityQueue<>(new Comparator<Customer>() { // maintain customer queue based on days
            public int compare(Customer c1, Customer c2) {
                return c1.day > c2.day? 1 : -1;
            }
        });

        while (m-- > 0) {
            StringTokenizer tik = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(tik.nextToken()); // flight starting location 
            int v = Integer.parseInt(tik.nextToken()); // flight ending destination 
            int d = Integer.parseInt(tik.nextToken()); // the day the flight flies in the window 
            int z = Integer.parseInt(tik.nextToken()); // capacity of the flight

            lst.add(new Flight(u, v, d, z));
        }

        int kn = k*n;
        while (kn-- > 0) {
            StringTokenizer sub = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(sub.nextToken()); // airport
            int b = Integer.parseInt(sub.nextToken()); // day 
            int c = Integer.parseInt(sub.nextToken()); // number of customers wanting to travel 

            customers.add(new Customer(a, b, c));
        }

        boolean successful = true; 
        for (int currDay = 1; currDay < n+1 ; currDay++) {
            // enter airport 
            while (customers.size() > 0) {
                if (customers.peek().day <= currDay) {
                    Customer cust = customers.poll();
                    int airportNum = cust.airport;
                    MapAirport[airportNum].numCustomers += cust.size; // maintains current state of the airport based on days 
                } else {
                    break;
                }
            }
            // load and unload flights 
            while (lst.size() > 0) {
                if (lst.peek().day <= currDay) {
                    Flight fl = lst.poll();
                    // load flights from starting 
                    Airport currAirport = MapAirport[fl.starting];
                    if (fl.capacity > currAirport.numCustomers) {
                        successful = false;
                        break;
                    } else {
                        currAirport.numCustomers -= fl.capacity;
                        // unload flights at ending 
                        Airport destAirport = MapAirport[fl.ending];
                        destAirport.lagCustomers += fl.capacity;
                    } 
                } else {
                    break;
                } 
            }
            // optimise
                if (!successful) {
                    break;
                }

            // add customers for next day 
            for (int i = 1; i < k+1; i++) {
                Airport tempAirport = MapAirport[i];
                tempAirport.numCustomers += tempAirport.lagCustomers;
                tempAirport.lagCustomers = 0;
            }
        }

        // goal: if fillled capacity ? optimal : sub optimal 
        if (successful) {
            System.out.println("optimal");
        } else {
            System.out.println("suboptimal");
        }
    }
}
