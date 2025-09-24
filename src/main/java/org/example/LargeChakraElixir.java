package org.example;

public class LargeChakraElixir implements ChakraPotion {
    private final String name = "Grosses-Weisen-Chakra-Elixier";
    private final int chakra = 50;

    @Override
    public String getName() { return name; }

    @Override
    public int chakraPowerUp() { return chakra; }

    @Override
    public String toString() { return "- Trank: " + name; }
}


