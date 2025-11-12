/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.cs101project;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;

public class AwardsByDateComparator implements Comparator<Award> {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    
    @Override
    public int compare(Award a1, Award a2) {
        
        LocalDate date1 = LocalDate.parse(a1.getDate(), FORMATTER);
        LocalDate date2 = LocalDate.parse(a2.getDate(), FORMATTER);
        return date1.compareTo(date2);
    }
    
}
