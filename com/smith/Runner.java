import java.util.Scanner;

public class Runner {

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    System.out.print("Enter the reference sequence: ");
    String reference = scanner.nextLine();

    System.out.print("Enter the query sequence: ");
    String query = scanner.nextLine();

    System.out.print("Enter the match score: ");
    int matchScore = scanner.nextInt();

    System.out.print("Enter the mismatch score: ");
    int mismatchScore = scanner.nextInt();

    System.out.print("Enter the gap score: ");
    int gapScore = scanner.nextInt();

    scanner.close();

    int score = SmithWaterman.smithWaterman(
      reference,
      query,
      matchScore,
      mismatchScore,
      gapScore
    );
    System.out.println("Max Score: " + score);
  }
}
