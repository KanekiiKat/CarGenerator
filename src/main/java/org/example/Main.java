package org.example;

/*
5. Coches

Dado un listado de modelos de coche, se generará un listado agrupado para cada marca con sus modelos. Por ejemplo, si tenemos los siguientes modelos en un fichero llamado coches.txt:

Mazda 5

Seat Ateca

Citröen C1

Ferrari 458 Italia

Ford Focus

Seat Tarraco

Hyundai Tucson

Mazda CX-5

Honda Civic

Seat Ibiza

Hyundai Kona

Citröen C3 Aircross

Mazda 3

Ford Fiesta

Como resultado tendremos un fichero llamado marcas.txt con el siguiente contenido:

Citröen: C1, C3 Aircross

Ferrari: 458 Italia

Ford: Fiesta, Focus

Honda: Civic

Hyundai: Kona, Tucson

Mazda: 3, 5, CX-5

Seat: Ateca, Ibiza, Tarraco

 */

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {

        Scanner sc = new Scanner(System.in);

        System.out.print("Ingresa el nombre del archivo para sacar las marcas: ");
        String file = sc.nextLine();

        Path pathFile = Path.of(file);

        brandSearcher(pathFile);


    }

    static void brandSearcher(Path file) throws IOException {
        List<String> lines = Files.readAllLines(file);
        lines.removeIf(l -> l.trim().isEmpty());
        TreeMap<String, String> mapCars = new TreeMap<>();
        for(String line : lines) {
            String[] separated = line.split("\\s+", 2);

            if(mapCars.containsKey(separated[0])) {
                mapCars.put(separated[0], mapCars.get(separated[0]) + " " + separated[1]);
            } else if (!mapCars.containsKey(separated[0])) {
                mapCars.put(separated[0], separated[1]);
            }

        }
        StringBuilder carsBrandsString = new StringBuilder();

        for(Map.Entry<String, String> carsBrands : mapCars.entrySet()) {
            carsBrandsString.append(carsBrands.getKey() + " " + carsBrands.getValue() + "\n\n");
        }

        fileCreation(carsBrandsString.toString());

    }

    static void fileCreation(String contein) throws IOException {
        Path pathResource = Path.of("C:\\Users\\alex1\\Documents\\Programación\\CarGenerator\\src\\main\\java\\resources\\marcas.txt");
        if(Files.notExists(pathResource)) {
            Files.writeString(pathResource, contein);
            System.out.println("Archivo guardado exitosamente en " + pathResource);
        } else if (Files.exists(pathResource)) {
            Files.delete(pathResource);
            Files.writeString(pathResource, contein);
            System.out.println("Archivo remplazado exitosamente en " + pathResource);
        }
    }
}