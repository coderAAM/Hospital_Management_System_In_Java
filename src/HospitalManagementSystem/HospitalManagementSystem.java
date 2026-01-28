package HospitalManagementSystem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class HospitalManagementSystem {
    private static  final String url = "";
    private static  final String username = "root";
    private static  final  String password = "Ahm@";

    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }
        Scanner scanner = new Scanner(System.in);
        try{
            Connection connection = DriverManager.getConnection(url,username,password);
            Patient patient = new Patient(connection, scanner);
            Doctor doctor = new Doctor(connection);

            Scanner sc = new Scanner(System.in);

            while (true) {
                System.out.println("\nHOSPITAL MANAGEMENT SYSTEM");
                System.out.println("1. ADD PATIENT");
                System.out.println("2. VIEW PATIENT");
                System.out.println("3. VIEW DOCTOR");
                System.out.println("4. BOOK APPOINTMENT");
                System.out.println("5. EXIT");
                System.out.print("Choose option: ");

                int choice = sc.nextInt();

                switch (choice) {
                    case 1:
                        System.out.println("Add Patient selected");
                        // addPatient();
                        patient.addPatient();
                        break;

                    case 2:
                        System.out.println("View Patient selected");
                        // viewPatient();
                        patient.viewPatient();
                        break;

                    case 3:
                        System.out.println("View Doctor selected");
                        // viewDoctor();
                        doctor.viewDoctor();
                        break;

                    case 4:
                        System.out.println("Book Appointment selected");
                        // bookAppointment();


                        break;

                    case 5:
                        System.out.println("Exiting Hospital Management System");
                        return; // program exit

                    default:
                        System.out.println("Invalid option! Try again.");
                }
            }


        }catch (SQLException e){
            e.printStackTrace();
        }


    }

    // book appointment
    public static void bookAppointment(
            Patient patient,
            Doctor doctor,
            Connection connection,
            Scanner scanner) {

        System.out.print("Enter Patient ID: ");
        int patientId = scanner.nextInt();

        System.out.print("Enter Doctor ID: ");
        int doctorId = scanner.nextInt();
        scanner.nextLine(); // buffer clear

        System.out.print("Enter Appointment Date (dd-mm-yyyy): ");
        String date = scanner.nextLine();

        // check patient & doctor exist
        if (patient.getPatientById(patientId, connection)
                && doctor.getDoctorId(doctorId, connection)) {

            // check doctor availability
            if (checkDoctorAvailability(doctorId, date, connection)) {
                System.out.println("Appointment booked successfully!");
            } else {
                System.out.println("Doctor not available on this date");
            }

        } else {
            System.out.println("Patient or Doctor does not exist");
        }
    }
    public static boolean checkDoctorAvailability(
            int doctorId,
            String date,
            Connection connection) {

        // Abhi simple logic (later DB query lagegi)
        return true; // assume available
    }


}

