package com.example.vacantes.util;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

public class Utileria {
    public static String guardarArchivo(MultipartFile multiPart, String ruta) {
        // Obtenemos el nombre original del archivo.
        String nombreOriginal = multiPart.getOriginalFilename();
        nombreOriginal.replaceAll(" ", "-"); // quita espacios del nombre del archivo(img)
        String nombreFinal = randomAlphaNumeric((8)) + nombreOriginal;
        try {
            // Formamos el nombre del archivo para guardarlo en el disco duro.
            File imageFile = new File(ruta + nombreFinal);
            System.out.println("Archivo: " + imageFile.getAbsolutePath());
            //Guardamos fisicamente el archivo en HD.
            multiPart.transferTo(imageFile);
            return nombreFinal;
        } catch (IOException e) {
            System.out.println("Error " + e.getMessage());
            return null;
        }
    }
        //Genera una cadena aleatoria de longitud N, evita nombre duplicados
    public static String randomAlphaNumeric(int conunt) {
        String CARACTERES = "ABCDEFGHIJKLMNOPQRSTUVWXYZ" + "0123456789";
        StringBuilder builder = new StringBuilder();
        while (conunt-- != 0) {
            int character = (int) (Math.random() * CARACTERES.length());
            builder.append(CARACTERES.charAt(character));
        }
        return builder.toString();
    }
}

