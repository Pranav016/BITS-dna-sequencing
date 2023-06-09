public class SmithWaterman {

  public static int smithWaterman(
    String reference,
    String query,
    int matchScore,
    int mismatchScore,
    int gapScore
  ) {
    int n = reference.length();
    int m = query.length();

    int[][] dp = new int[n + 1][m + 1];

    int maxScore = 0;
    int maxI = 0;
    int maxJ = 0;

    for (int i = 1; i <= n; i++) {
      for (int j = 1; j <= m; j++) {
        int match =
          dp[i - 1][j - 1] +
          (
            reference.charAt(i - 1) == query.charAt(j - 1)
              ? matchScore
              : mismatchScore
          );
        int delete = dp[i - 1][j] + gapScore;
        int insert = dp[i][j - 1] + gapScore;

        dp[i][j] = Math.max(0, Math.max(match, Math.max(delete, insert)));

        if (dp[i][j] > maxScore) {
          maxScore = dp[i][j];
          maxI = i;
          maxJ = j;
        }
      }
    }

    // Backtracking to find the alignment
    StringBuilder alignmentReference = new StringBuilder();
    StringBuilder alignmentQuery = new StringBuilder();

    int i = maxI;
    int j = maxJ;

    while (i > 0 && j > 0 && dp[i][j] > 0) {
      if (
        dp[i][j] ==
        dp[i - 1][j - 1] +
        (
          reference.charAt(i - 1) == query.charAt(j - 1)
            ? matchScore
            : mismatchScore
        )
      ) {
        alignmentReference.insert(0, reference.charAt(i - 1));
        alignmentQuery.insert(0, query.charAt(j - 1));
        i--;
        j--;
      } else if (dp[i][j] == dp[i - 1][j] + gapScore) {
        alignmentReference.insert(0, reference.charAt(i - 1));
        alignmentQuery.insert(0, '-');
        i--;
      } else {
        alignmentReference.insert(0, '-');
        alignmentQuery.insert(0, query.charAt(j - 1));
        j--;
      }
    }

    System.out.println("Alignment Reference: " + alignmentReference.toString());
    System.out.println("Alignment Query: " + alignmentQuery.toString());

    return maxScore;
  }
}
