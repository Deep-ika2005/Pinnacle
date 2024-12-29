package task2;
import java.util.*;
class Flight {
    private String flightId;
    private String source;
    private String destination;
    private String departureTime;
    private String arrivalTime;
    private Map<String, Boolean> seats; 

    public Flight(String flightId, String source, String destination, String departureTime, String arrivalTime, int seatCount) {
        this.flightId = flightId;
        this.source = source;
        this.destination = destination;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.seats = new HashMap<>();
        for (int i = 1; i <= seatCount; i++) {
            seats.put("S" + i, true);
        }
    }

    public String getFlightId() {
        return flightId;
    }

    public String getSource() {
        return source;
    }

    public String getDestination() {
        return destination;
    }

    public boolean isSeatAvailable(String seatNumber) {
        return seats.getOrDefault(seatNumber, false);
    }

    public boolean bookSeat(String seatNumber) {
        if (isSeatAvailable(seatNumber)) {
            seats.put(seatNumber, false);
            return true;
        }
        return false;
    }

    public void displayFlightDetails() {
        System.out.println("Flight ID: " + flightId + ", From: " + source + ", To: " + destination +
                ", Departure: " + departureTime + ", Arrival: " + arrivalTime);
    }

    public void displayAvailableSeats() {
        System.out.print("Available Seats: ");
        for (Map.Entry<String, Boolean> seat : seats.entrySet()) {
            if (seat.getValue()) {
                System.out.print(seat.getKey() + " ");
            }
        }
        System.out.println();
    }
}
public class FlightBooking {

    private static List<Flight> flights = new ArrayList<>();

    public static void main(String[] args) {
        // Initialize some sample flights
        flights.add(new Flight("F001", "New York", "Los Angeles", "10:00 AM", "1:00 PM", 5));
        flights.add(new Flight("F002", "New York", "Chicago", "2:00 PM", "4:00 PM", 5));

        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            
            System.out.println("\n=== Flight Booking System ===");
            System.out.println("1. View Flights");
            System.out.println("2. Search Flights");
            System.out.println("3. Book a Flight");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); 

            switch (choice) {
                case 1: 
                    System.out.println("\nAvailable Flights:");
                    for (Flight flight : flights) {
                        flight.displayFlightDetails();
                    }
                    break;

                case 2: 
                    System.out.print("\nEnter source: ");
                    String source = scanner.nextLine();
                    System.out.print("Enter destination: ");
                    String destination = scanner.nextLine();
                    List<Flight> searchResults = searchFlights(source, destination);
                    if (searchResults.isEmpty()) {
                        System.out.println("No flights found for the given route.");
                    } else {
                        System.out.println("\nMatching Flights:");
                        for (Flight flight : searchResults) {
                            flight.displayFlightDetails();
                            flight.displayAvailableSeats();
                        }
                    }
                    break;

                case 3: 
                    System.out.print("\nEnter Flight ID to book: ");
                    String flightId = scanner.nextLine();
                    Flight flightToBook = findFlightById(flightId);
                    if (flightToBook == null) {
                        System.out.println("Invalid Flight ID.");
                        break;
                    }
                    flightToBook.displayAvailableSeats();
                    System.out.print("Choose a seat: ");
                    String seatNumber = scanner.nextLine();
                    if (!flightToBook.isSeatAvailable(seatNumber)) {
                        System.out.println("Seat is not available.");
                        break;
                    }
                    System.out.print("Enter payment details: ");
                    String paymentDetails = scanner.nextLine();
                    if (processPayment(paymentDetails, 150.0)) {
                        flightToBook.bookSeat(seatNumber);
                        System.out.println("Booking confirmed! Seat " + seatNumber + " is reserved.");
                    } else {
                        System.out.println("Payment failed. Booking unsuccessful.");
                    }
                    break;

                case 4: // Exit
                    System.out.println("Exiting the system. Goodbye!");
                    running = false;
                    break;

                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
        scanner.close();
    }

    private static List<Flight> searchFlights(String source, String destination) {
        List<Flight> result = new ArrayList<>();
        for (Flight flight : flights) {
            if (flight.getSource().equalsIgnoreCase(source) && flight.getDestination().equalsIgnoreCase(destination)) {
                result.add(flight);
            }
        }
        return result;
    }
    private static Flight findFlightById(String flightId) {
        for (Flight flight : flights) {
            if (flight.getFlightId().equalsIgnoreCase(flightId)) {
                return flight;
            }
        }
        return null;
    }
    private static boolean processPayment(String paymentDetails, double amount) {
        System.out.println("Processing payment of $" + amount + " using details: " + paymentDetails);
        return true; 
    }
}
