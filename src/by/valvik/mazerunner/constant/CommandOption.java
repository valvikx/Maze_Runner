package by.valvik.mazerunner.constant;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static java.util.Optional.ofNullable;

public enum CommandOption {

    EXIT(0),
    NEW(1),
    LOAD(2),
    SAVE(3),
    DISPLAY(4),
    SOLVE(5),
    CHOOSE(6);

    private static final Map<Integer, CommandOption> MAPPING = new HashMap<>();

    private final int option;

    static {

        Arrays.stream(CommandOption.values())
              .forEach(co -> MAPPING.put(co.option, co));

    }

    CommandOption(int option) {

        this.option = option;

    }

    public int getCommandOption() {

        return option;

    }

    public static Optional<CommandOption> forCommandOption(Integer option) {

        return ofNullable(MAPPING.get(option));

    }

}
