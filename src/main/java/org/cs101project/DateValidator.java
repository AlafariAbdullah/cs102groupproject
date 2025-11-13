/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.cs101project;

import java.util.*;
import java.time.*;
import java.time.format.*;

public class DateValidator {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter
            .ofPattern("dd/MM/uuuu") // "uuuu" handles ISO years properly
            .withResolverStyle(ResolverStyle.STRICT); // rejects 32/01/2025, etc.

    public static boolean isValidDate(String dateStr) {
        try {
            LocalDate.parse(dateStr.trim(), FORMATTER);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }
}
