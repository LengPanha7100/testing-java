import org.nocrala.tools.texttablefmt.BorderStyle;
import org.nocrala.tools.texttablefmt.CellStyle;
import org.nocrala.tools.texttablefmt.ShownBorders;
import org.nocrala.tools.texttablefmt.Table;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {
    public static final String RED = "\u001B[31m";   // Red color
    public static final String GREEN = "\u001B[32m"; // Green color
    public static final String RESET = "\u001B[0m";  // Reset color
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Movie> movies = new ArrayList<>(); // list to store movie
        AtomicInteger count = new AtomicInteger(1); //auto-increment Id
        int hallNumber = 1;

        System.out.println("---------- Setting up Cinema --------");
        System.out.print("Enter number of Hall in Cinema: ");
        int numHall = scanner.nextInt();
        System.out.print("Enter number of seat in each Hall: ");
        int numSeats = scanner.nextInt();
        int[][] arr = new int[numHall][numSeats];
        int seat = 0;

        do{
            System.out.println("----------  Movie Management System  --------");
            System.out.println("1- Insert Movie");
            System.out.println("2- Check & Booking Movie");
            System.out.println("3- Check Ticket");
            System.out.println("4- Reset Hall");
            System.out.println("5- Exit");
            System.out.println("---------------------------------------------");
            System.out.print("-> Choose option (1-5):");
            int numMovies = scanner.nextInt();
            switch (numMovies){
                case 1:
                    System.out.println("---------- Insert information of movie ----------\n");
                    System.out.print("-> Enter Movie Name:");
                    String movieName = scanner.next();
                    System.out.print("-> Enter Movie Type:");
                    String movieType = scanner.next();
                    System.out.print("-> Enter Duration (min):");
                    int movieTime = scanner.nextInt();

                    int movieId = count.getAndIncrement();
                    movies.add(new Movie(movieId,movieName,movieType,movieTime,hallNumber,numSeats));
                    System.out.println(movieName + " " + movieType + " " + " will show in hall #" + hallNumber);
                    scanner.nextLine();
                    while (true) {
                        System.out.print("Do you want to continue? (Y/N): ");
                        String input = scanner.nextLine().trim().toUpperCase();
                        if (input.equals("N")) {
                            System.out.println("Exiting the program...");
                            scanner.close();
                            System.exit(0);
                        } else if (input.equals("Y")) {
                            System.out.println("Continuing...");
                            break;
                        } else {
                            System.out.println("Invalid input! Please enter Y or N.\n");
                        }
                    }
                    break;
                case 2:
                    CellStyle numberStyle = new CellStyle(CellStyle.HorizontalAlign.center);
                    Table table = new Table(5 , BorderStyle.UNICODE_BOX , ShownBorders.ALL);
                    System.out.println("---------- Display All Movie----------");
                    if(movies.isEmpty()){
                        System.out.println("No Movie Found!");
                    }else {
                        int available = 0;
                        int unavailable = 0;
                        System.out.println("ID" + "\t\t\tMovie " + "\t\t\t\t\tType" + "\t\t\t\t\tDuration" + "\t\tHall" + "\t\tSeat" + "\t\tAvailable" + "\t\tUnavailable");
                        for (Movie movie : movies) {
                            int hallIndex = movie.getId() -1;
                            for(int i=0 ;i<arr[hallIndex].length;i++){
                                if(arr[hallIndex][i] == 0){
                                    available++;
                                }else {
                                    unavailable++;
                                }
                            }
                            System.out.println(movie.getId() +"\t\t\t"+ movie.getName()+"\t\t\t"+movie.getType()+"\t\t\t\t\t"+movie.getDuration()+"\t\t\t\t" + movie.getHall()+"\t\t\t"+movie.getSeat() +"\t\t\t\t" + available +"\t\t\t\t" +unavailable+"\t\t\t");
                        }
                        System.out.print("-> Enter movie’s Id to detail :");
                        int id = scanner.nextInt();
                        System.out.println("-------------------- Screen Hall #"+hallNumber+"--------------------");
                        for (int i=0 ; i<arr[id-1].length ; i++) {
                            if(arr[id-1][i] == 0){
                                System.out.print(GREEN+"(+)"+(i+1)+RESET+"\t\t");
                                available++;
                            }else {
                                System.out.print(RED+"(-)"+(i+1)+RESET+"\t\t");
                                unavailable++;
                            }
                            if((i+1)%5 == 0){
                                System.out.println();
                            }
                        }
                        System.out.print("\n\n-> Choose seat that you want to booking(e.g:1,2,3,4):");
                        seat = scanner.nextInt();
                        scanner.nextLine();
                        while (true) {
                            System.out.print(" => Do you want to book Seat number "+seat+"? (Y/N):");
                            String inputSeat = scanner.nextLine().trim().toUpperCase();
                            if (inputSeat.equals("N")) {
                                System.out.println("Exiting the program...");
                                scanner.close();
                                System.exit(0);
                            } else if (inputSeat.equals("Y")) {
                                if(arr[id - 1][seat - 1] == 1){
                                    System.out.println("\t\tSeat number " + seat + " is already booked! Please choose another seat.");
                                }else {
                                    arr[id - 1][seat - 1] = 1;
                                    System.out.println("\t\tSeat number "+seat+" was booked successfully!");
                                }
                                break;
                            } else {
                                System.out.println("Invalid input! Please enter Y or N.\n");
                            }
                        }

                            // Re-display the updated seating availability after booking
                            System.out.println("\nUpdated seating availability:");
                            available = 0;
                            unavailable = 0;
                            for (int i = 0; i < arr[id - 1].length; i++) {
                                if (arr[id - 1][i] == 0) {
                                    System.out.print(GREEN + "(+)" + (i + 1) + RESET + "\t\t");
                                    available++;
                                } else {
                                    System.out.print(RED + "(-)" + (i + 1) + RESET + "\t\t");
                                    unavailable++;
                                }
                                if ((i + 1) % 5 == 0) {
                                    System.out.println();
                                }
                            }

                    }
                    break;
                case 3:
                    System.out.println("==================================");
                    System.out.println("Your ticket has been booked!");
                    System.out.println("==================================");
                    System.out.println("Hall: #"+hallNumber+"");
                    for (Movie movie : movies) {
                        System.out.println("\t\t\t "+movie.getName()+"");
                        System.out.println(seat);
                    }
                    System.out.println("-----------------------------");
                    break;
                case 4:
                    System.out.print("  => All Hall was reset with all seats available? (y/n): ");
                    scanner.nextLine();
                    while (true) {
                        String input = scanner.nextLine().trim().toUpperCase(); // Read input inside the loop

                        if (input.equals("N")) {
                            System.out.println("Exiting the program...");
                            scanner.close();
                            System.exit(0);
                        } else if (input.equals("Y")) {
                            for (int i = 0; i < arr.length; i++) {
                                for (int j = 0; j < arr[i].length; j++) {
                                    arr[i][j] = 0;  // Reset seat to available
                                }
                            }
                            System.out.println("\t\t All halls were reset successfully!");
                            break;  // Exit loop after reset
                        } else {
                            System.out.print("Invalid input! Please enter Y or N: ");
                        }
                    }
                    break;
                case 5:
                    System.out.println("Good bye!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid!");

            }

        }while (true);
    }
}
