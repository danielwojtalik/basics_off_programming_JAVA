package com.app.main;

import com.app.service.MenuService;

public class App {
    public static void main(String[] args) {

        new MenuService().mainMenu();

       /* Scanner sc = new Scanner(System.in);

        boolean isNotOk = true;
        while (isNotOk) {
            try {
                System.out.println("Podaj nazwie modelu");
                String model = sc.nextLine();
                System.out.println("Podaj kolor");
                String color = sc.nextLine();
                isNotOk = false;
            } catch (MyException e) {
                e.printStackTrace();
            }
        }*/

    }
}
