package org.dainst.redirector;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Daniel de Oliveira
 */
class RedirectsReader {

    static Map<String, String> read(String path) throws Exception {

        try (
            FileReader fr = new FileReader(path);
            BufferedReader i = new BufferedReader(fr) ) {

            return i.lines().map((line) -> {
                if (line.split(",").length != 2) return null;
                return line.replaceAll("\\s+", "");
            })
            .filter(s -> s != null)
            .collect( Collectors.toMap(
                p -> p.split(",")[0],
                p -> p.split(",")[1])
            );
        }
    }
}
