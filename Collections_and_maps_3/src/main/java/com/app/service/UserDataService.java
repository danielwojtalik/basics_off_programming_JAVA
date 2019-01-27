package com.app.service;

import java.util.InputMismatchException;
import java.util.Scanner;

public class UserDataService {
    private final static Scanner sc = new Scanner(System.in);

    public int getInt(String message) {
        System.out.println(message);
        try {
            return Integer.parseInt(sc.nextLine());
        } catch (InputMismatchException e) {
            System.err.println("CHOSE ONE OPTION FROM 1-6");
            return Integer.parseInt(sc.nextLine());
        }catch (NumberFormatException e){
            System.err.println("CHOSE ONE OPTION FROM 1-6");
            return Integer.parseInt(sc.nextLine());
        }

    }

    public void close() {
        sc.close();
    }
}
