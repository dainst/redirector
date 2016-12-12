package org.dainst.redirector;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author Daniel de Oliveira
 */
class RedirectsReader {

    static Function<String, Optional<String>> validLine = (line) -> {
        if (line.split(",").length == 2)
            return Optional.of(line.replaceAll("\\s+", ""));
        else
            return Optional.empty();
    };

    static Map<String, String> read(String path) throws Exception {

        Map<String, String> m;
        try (
            FileReader fr = new FileReader(path);
            BufferedReader i = new BufferedReader(fr);
                ) {

            m = i.lines().map(validLine)
                .filter(s -> s.isPresent())
                .collect(
                    Collectors.toMap(
                            p -> p.get().split(",")[0],
                            p -> p.get().split(",")[1])
                );
        }
        return m;
    }
}
