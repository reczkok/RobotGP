package Interpreter.Language;

import java.util.ArrayList;
import java.util.List;

public class ProgramOutput {
    List<List<ExpressionResult>> outputs;
    List<List<ExpressionResult>> inputs;

    public ProgramOutput(List<List<ExpressionResult>> outputs, List<List<ExpressionResult>> inputs) {
        this.outputs = new ArrayList<>();
        this.inputs = new ArrayList<>();

        for (int i = 0; i < outputs.size(); i++) {
            this.outputs.add(new ArrayList<>());
            for (int j = 0; j < outputs.get(i).size(); j++) {
                this.outputs.get(i).add(outputs.get(i).get(j));
            }
        }

        for (int i = 0; i < inputs.size(); i++) {
            this.inputs.add(new ArrayList<>());
            for (int j = 0; j < inputs.get(i).size(); j++) {
                this.inputs.get(i).add(inputs.get(i).get(j));
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Inputs -> Outputs\n");
        for (int i = 0; i < outputs.size(); i++) {
            for (int j = 0; j < inputs.get(i).size(); j++) {
                sb.append(inputs.get(i).get(j).toString());
                if (j < inputs.get(i).size() - 1) {
                    sb.append(", ");
                }
            }
            sb.append(" -> ");
            for (int j = 0; j < outputs.get(i).size(); j++) {
                sb.append(outputs.get(i).get(j).toString());
                if (j < outputs.get(i).size() - 1) {
                    sb.append(", ");
                }
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}
