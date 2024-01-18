package GP;

public record Parameters() {
    static float fitnessThreshold = 0.01f;
    static int populationSize = 1000;
    static int maxGenerations = 100;
    static int maxInitialDepth = 5;
    static int minInitialNodes = 100;

    static int maxEvaluationIterations = 10000;

    static double crossoverProbability = 0.9;

    static int tournamentSize = 3;
}
