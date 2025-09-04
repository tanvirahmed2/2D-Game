package game;

public class ScoreCalc {

    // Constants for score calculation factors
    private static final int CHIP_FACTOR = 10;     // Points per chip
    private static final int TIME_FACTOR = 5;      // Points per remaining second
    private static final int BASE_SCORE = 1000;    // Base score

    /**
     * Calculates the player's score based on chip count and time remaining.
     *
     * @param chipCount     The number of chips collected by the player.
     * @param timeRemaining The time remaining in seconds.
     * @return The calculated score.
     */
    public static int calc(int chipCount, int timeRemaining) {
        // Calculate score based on factors
        int chipScore = chipCount * CHIP_FACTOR;
        int timeScore = timeRemaining * TIME_FACTOR;

        // Combine factors and add base score
        int totalScore = chipScore + timeScore + BASE_SCORE;

        // Ensure the score is non-negative
        return Math.max(0, totalScore);
    }
}