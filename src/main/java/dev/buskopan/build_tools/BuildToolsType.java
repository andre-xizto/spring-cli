package dev.buskopan.build_tools;

public enum BuildToolsType {
    MAVEN("Maven"),
    GRADLE("Gradle");

    private final String display;

    BuildToolsType(String display) {
        this.display = display;
    }

    public String getDisplay() {
        return display;
    }
}
