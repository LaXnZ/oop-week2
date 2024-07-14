import java.util.Date;
import java.util.List;
import java.util.ArrayList;

// Abstract Transportation class
abstract class Transportation {
    protected Date startDateTime;
    protected Date endDateTime;
    protected String departurePlace;
    protected String arrivalPlace;
    protected double fare;
    protected double mileage;

    public Transportation(Date startDateTime, Date endDateTime, String departurePlace, String arrivalPlace, double fare, double mileage) {
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
        this.departurePlace = departurePlace;
        this.arrivalPlace = arrivalPlace;
        this.fare = fare;
        this.mileage = mileage;
    }

    public abstract double getCost();
}

// Specific transportation classes
class Train extends Transportation {
    private String trainNumber;

    public Train(Date startDateTime, Date endDateTime, String departurePlace, String arrivalPlace, double fare, double mileage, String trainNumber) {
        super(startDateTime, endDateTime, departurePlace, arrivalPlace, fare, mileage);
        this.trainNumber = trainNumber;
    }

    @Override
    public double getCost() {
        return fare;
    }
}

class SelfDriving extends Transportation {
    private String vehicleType;
    private double fuelCost;

    public SelfDriving(Date startDateTime, Date endDateTime, String departurePlace, String arrivalPlace, double fare, double mileage, String vehicleType, double fuelCost) {
        super(startDateTime, endDateTime, departurePlace, arrivalPlace, fare, mileage);
        this.vehicleType = vehicleType;
        this.fuelCost = fuelCost;
    }

    @Override
    public double getCost() {
        return fare + fuelCost;
    }
}

class Flight extends Transportation {
    private String flightNumber;

    public Flight(Date startDateTime, Date endDateTime, String departurePlace, String arrivalPlace, double fare, double mileage, String flightNumber) {
        super(startDateTime, endDateTime, departurePlace, arrivalPlace, fare, mileage);
        this.flightNumber = flightNumber;
    }

    @Override
    public double getCost() {
        return fare;
    }
}

class Taxi extends Transportation {
    private String taxiNumber;

    public Taxi(Date startDateTime, Date endDateTime, String departurePlace, String arrivalPlace, double fare, double mileage, String taxiNumber) {
        super(startDateTime, endDateTime, departurePlace, arrivalPlace, fare, mileage);
        this.taxiNumber = taxiNumber;
    }

    @Override
    public double getCost() {
        return fare;
    }
}

// Staff class
class Staff {
    private String name;
    private String companyId;

    public Staff(String name, String companyId) {
        this.name = name;
        this.companyId = companyId;
    }

    @Override
    public String toString() {
        return name + " (" + companyId + ")";
    }
}

// Accommodation class
class Accommodation {
    private Date startDate;
    private Date endDate;
    private String place;
    private double cost;

    public Accommodation(Date startDate, Date endDate, String place, double cost) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.place = place;
        this.cost = cost;
    }

    public double getCost() {
        return cost;
    }
}

// Meal class
class Meal {
    private Date date;
    private String mealType;
    private double cost;

    public Meal(Date date, String mealType, double cost) {
        this.date = date;
        this.mealType = mealType;
        this.cost = cost;
    }

    public double getCost() {
        return cost;
    }
}

// BusinessTrip class
class BusinessTrip {
    private String tripID;
    private String purpose;
    private Date startDate;
    private Date endDate;
    private List<Staff> staff;
    private List<Transportation> transportations;
    private List<Accommodation> accommodations;
    private List<Meal> meals;

    public BusinessTrip(String tripID, String purpose, Date startDate, Date endDate) {
        this.tripID = tripID;
        this.purpose = purpose;
        this.startDate = startDate;
        this.endDate = endDate;
        this.staff = new ArrayList<>();
        this.transportations = new ArrayList<>();
        this.accommodations = new ArrayList<>();
        this.meals = new ArrayList<>();
    }

    public void addStaff(Staff staff) {
        this.staff.add(staff);
    }

    public void addTransportation(Transportation transportation) {
        this.transportations.add(transportation);
    }

    public void addAccommodation(Accommodation accommodation) {
        this.accommodations.add(accommodation);
    }

    public void addMeal(Meal meal) {
        this.meals.add(meal);
    }

    public double getTotalTransportationCost() {
        double total = 0;
        for (Transportation transportation : transportations) {
            total += transportation.getCost();
        }
        return total;
    }

    public double getTotalOtherSpending() {
        double total = 0;
        for (Accommodation accommodation : accommodations) {
            total += accommodation.getCost();
        }
        for (Meal meal : meals) {
            total += meal.getCost();
        }
        return total;
    }

    public double getTotalCost() {
        return getTotalTransportationCost() + getTotalOtherSpending();
    }

    public void printReport() {
        System.out.println("TripID: " + tripID);
        System.out.println("Purpose: " + purpose);
        System.out.print("Staff Involved: ");
        for (Staff s : staff) {
            System.out.print(s + " ");
        }
        System.out.println();
        System.out.println("Start Date: " + startDate);
        System.out.println("End Date: " + endDate);
        System.out.println("Transportation (Sum): " + getTotalTransportationCost());
        System.out.println("Other Spending (Total): " + getTotalOtherSpending());
        System.out.println("Total Cost: " + getTotalCost());
        System.out.println();
    }
}

// Main class
public class Main {
    public static void main(String[] args) {
        // Create a business trip
        BusinessTrip trip1 = new BusinessTrip("001", "Meeting", new Date(2021 - 1900, 0, 1), new Date(2021 - 1900, 0, 2)); // Dates are year-1900, month-1, day

        // Add staff
        trip1.addStaff(new Staff("Alice", "001"));
        trip1.addStaff(new Staff("Bob", "002"));

        // Add transportation
        trip1.addTransportation(new Train(new Date(2021 - 1900, 0, 1, 8, 0), new Date(2021 - 1900, 0, 1, 12, 0), "CityA", "CityB", 50.0, 300, "T123"));
        trip1.addTransportation(new Taxi(new Date(2021 - 1900, 0, 1, 13, 0), new Date(2021 - 1900, 0, 1, 13, 30), "CityB", "Hotel", 20.0, 10, "TX001"));

        // Add accommodation
        trip1.addAccommodation(new Accommodation(new Date(2021 - 1900, 0, 1), new Date(2021 - 1900, 0, 2), "HotelA", 100.0));

        // Add meals
        trip1.addMeal(new Meal(new Date(2021 - 1900, 0, 1), "Lunch", 15.0));
        trip1.addMeal(new Meal(new Date(2021 - 1900, 0, 1), "Dinner", 25.0));
        trip1.addMeal(new Meal(new Date(2021 - 1900, 0, 2), "Breakfast", 10.0));

        // Print the report
        trip1.printReport();

        // Total cost for a period (example)
        System.out.println("Total Cost for a Period (Start Date, End Date): " + trip1.getTotalCost());
    }
}
