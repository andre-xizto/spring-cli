package dev.buskopan.checker;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class JavaVersionChecker {

    public static List<String> getJavaVersions() {
        try {
            ProcessBuilder processBuilder = new ProcessBuilder();
            processBuilder.command("java","--version");
            Process process = processBuilder.start();

            BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream()));

            String line;
            List<String> versions = new ArrayList<>();

            while ((line = br.readLine()) != null) {
                String version = extractVersion(line);
                if (version != null) {
                    versions.add(version);
                }
            }

            versions.sort(Comparator.naturalOrder());
            return versions;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static String extractVersion(String line) {
        String value = Arrays
                .stream(Arrays
                        .stream(line.split(" "))
                        .toList()
                        .get(1)
                        .split("\\."))
                .toList()
                .getFirst();

        if (Character.isDigit(value.charAt(0))) {
            return value;
        }
        return null;
    }

}
