import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("---------- Setting up Cinema --------");
        System.out.print("Enter number of Hall in Cinema: ");
        int numHall = scanner.nextInt();
        System.out.print("Enter number of seat in each Hall: ");
        int numSeats = scanner.nextInt();

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

                    System.out.println(movieName + " " + movieType + " " + " will show in hall #1");
                    scanner.nextLine();
                    System.out.print("Do you want to continue? (Y/N):");
                    String input = scanner.nextLine().trim().toLowerCase();
                    if(input.equals("N")){
                        System.out.println("Exiting the program...");
                        System.exit(0);
                    }else if(input.equals("Y")){
                        System.out.println("Continue...");
                    }else {
                        System.out.print("Invalid input! Please enter Y or N.");
                    }
                    break;
                case 2:
                    System.out.println("Movie Management System12");
                    break;
                case 3:

            }

        }while (true);
    }
}
