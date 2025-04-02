import org.nocrala.tools.texttablefmt.BorderStyle;
import org.nocrala.tools.texttablefmt.CellStyle;
import org.nocrala.tools.texttablefmt.ShownBorders;
import org.nocrala.tools.texttablefmt.Table;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;
public class Main {
    public static final String RED = "\u001B[31m";   // Red color
    public static final String GREEN = "\u001B[32m"; // Green color
    public static final String RESET = "\u001B[0m";  // Reset color
    public static final String PINK = "\u001B[35m"; // Pink/Magenta color
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
            System.out.println("\n----------  Movie Management System  --------");
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
                    CellStyle style = new CellStyle(CellStyle.HorizontalAlign.center);
                    Table table = new Table(5, BorderStyle.UNICODE_BOX,ShownBorders.ALL);
                    table.setColumnWidth(0, 15, 20);
                    table.setColumnWidth(1, 15, 20);
                    table.setColumnWidth(2, 15, 20);
                    table.setColumnWidth(3, 15, 20);
                    table.setColumnWidth(4, 15, 20);
                    System.out.println("---------- Display All Movie----------");
                    if(movies.isEmpty()){
                        System.out.println("No Movie Found!");
                    }else {
                        Table table1 = new Table(8, BorderStyle.UNICODE_BOX, ShownBorders.ALL);
                        table1.setColumnWidth(0, 10, 15);
                        table1.setColumnWidth(1, 25, 25);
                        table1.setColumnWidth(2, 25, 25);
                        table1.setColumnWidth(3, 10, 15);
                        table1.setColumnWidth(4, 10, 15);
                        table1.setColumnWidth(5, 10, 15);
                        table1.setColumnWidth(6, 10, 15);
                        table1.setColumnWidth(7, 10, 15);
                        // Add headers (outside the loop)
                        table1.addCell(GREEN+"ID", style);
                        table1.addCell(GREEN+"Movie Name", style);
                        table1.addCell(GREEN+"Type", style);
                        table1.addCell(GREEN+"Duration", style);
                        table1.addCell(GREEN+"Hall", style);
                        table1.addCell(GREEN+"Seat", style);
                        table1.addCell(GREEN+"Available", style);
                        table1.addCell(GREEN+"Unavailable", style);
                        int available = 0;
                        int unavailable = 0;
                        for (Movie movie : movies) {
                            int hallIndex = movie.getId() -1;
                            for(int i=0 ;i<arr[hallIndex].length;i++){
                                if(arr[hallIndex][i] == 0){
                                    available++;
                                }else {
                                    unavailable++;
                                }
                            }
                            table1.addCell(PINK+String.valueOf(movie.getId()),style);
                            table1.addCell(PINK+movie.getName(),style);
                            table1.addCell(PINK+movie.getType(),style);
                            table1.addCell(PINK+String.valueOf(movie.getDuration()),style);
                            table1.addCell(PINK+String.valueOf(movie.getHall()),style);
                            table1.addCell(PINK+String.valueOf(movie.getSeat()),style);
                            table1.addCell(PINK+String.valueOf(available),style);
                            table1.addCell(PINK+String.valueOf(unavailable),style);
                        }
                        System.out.println(table1.render());

                        System.out.print("-> Enter movie’s Id to detail :");
                        int id = scanner.nextInt();
                        System.out.println("-------------------- Screen Hall #"+hallNumber+"--------------------");
                        for (int i=0 ; i<arr[id-1].length ; i++) {
                            if(arr[id-1][i] == 0){
                                table.addCell(GREEN+"(+)"+(i+1)+RESET,style);
                                available++;
                            }else {
                                table.addCell(RED+"(-)"+(i+1)+RESET , style);
                                unavailable++;
                            }
                            if((i+1)%5 == 0){
                                System.out.println();
                            }
                        }
                        System.out.print(table.render());
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

                            System.out.println("Updated seating availability:");
                            table = new Table(5, BorderStyle.UNICODE_BOX, ShownBorders.ALL);
                            table.setColumnWidth(0, 15, 20);
                            table.setColumnWidth(1, 15, 20);
                            table.setColumnWidth(2, 15, 20);
                            table.setColumnWidth(3, 15, 20);
                            table.setColumnWidth(4, 15, 20);
                            available = 0;
                            unavailable = 0;
                            for (int i = 0; i < arr[id - 1].length; i++) {
                                if (arr[id - 1][i] == 0) {
                                    table.addCell(GREEN + "(+)" + (i + 1) + RESET ,style);
                                    available++;
                                } else {
                                    table.addCell(RED + "(-)" + (i + 1) + RESET ,style);
                                    unavailable++;
                                }
                                if ((i + 1) % 5 == 0) {
                                    System.out.println();
                                }
                            }
                        System.out.println(table.render());

                    }
                    break;
                case 3:
                    System.out.println("\n\n==================================");
                    System.out.println("Your ticket has been booked!");
                    System.out.println("==================================\n");
                    System.out.println("Hall: #"+hallNumber+"");
                    for (Movie movie : movies) {
                        System.out.println("\t\t\t "+movie.getName()+"");
                        System.out.println(seat);
                    }
                    System.out.println("\n-----------------------------");
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
                            break;
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
