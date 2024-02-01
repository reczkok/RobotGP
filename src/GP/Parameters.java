package GP;

public record Parameters() {
    static float fitnessThreshold = 0.01f;
    static int populationSize = 1000;
    static int maxGenerations = 100;
    static int maxInitialDepth = 6;
    static int minInitialNodes = 50;

    static int maxEvaluationIterations = 10000;

    static double crossoverProbability = 0.95;

    static int tournamentSize = 3;
}
