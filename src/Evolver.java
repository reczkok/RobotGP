import java.util.ArrayList;
import java.util.List;

public class Evolver {
    List<Program> programs;

    public Evolver() {
        this.programs = new ArrayList<>();
        this.generateInitialPopulation();
    }

    private void generateInitialPopulation() {
        for(int i = 0; i < Parameters.populationSize; i++) {
        }
    }
}
