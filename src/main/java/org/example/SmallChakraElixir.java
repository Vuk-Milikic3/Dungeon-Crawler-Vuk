package org.example;

public class SmallChakraElixir implements ChakraPotion {
    private final String name = "Kleines-Chakra-Elixier";
    private final int chakra = 20;

    @Override
    public String getName() { return name; }

    @Override
    public int chakraPowerUp() { return chakra; }

    @Override
    public String toString() { return "- Trank: " + name; }
}


