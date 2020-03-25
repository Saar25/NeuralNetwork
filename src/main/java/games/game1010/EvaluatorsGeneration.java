package games.game1010;

import games.GenerationSettings;

import java.util.ArrayList;
import java.util.List;

public class EvaluatorsGeneration {

    private final List<BoardEvaluator> evaluators = new ArrayList<>();
    private final GenerationSettings generationSettings;

    public EvaluatorsGeneration(GenerationSettings generationSettings) {
        this.generationSettings = generationSettings;
    }
}
