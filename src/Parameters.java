public record Parameters() {
    static int populationSize = 100000;
    static int maxGenerations = 50;
    static int maxInitialDepth = 4;
    static int minInitialNodes = 20;
    static int maxDepth = 17;
    static int maxNodes = 1000000;

    static double crossoverProbability = 0.9;
    static double reproductionProbability = 0.1;
    static double mutationProbability = 0.1;

    static int tournamentSize = 2;
}
