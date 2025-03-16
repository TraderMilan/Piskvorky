package org.example;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        char[][] plocha = new char[3][3];
        Scanner scanner = new Scanner(System.in);

        int count = 0;
        ArrayList<Integer> choices = new ArrayList<>();
        System.out.println("Vyptajte v hre piskvorky");

        while (count != 9) {

            if (count % 2 == 0) {
                System.out.println("Hrac X: vyber pole od 1-9");
            } else {
                System.out.println("Hrac O: vyber pole od 1-9");
            }

            printPlocha(plocha);
            int choice;
            // Kontrola vstupu
            while (true) {
                try {
                    choice = scanner.nextInt();

                    if (choice > 9 || choice < 1) {
                        throw new IllegalArgumentException("Cislo musi byt od 1 do 9");
                    }
                    break;
                } catch (InputMismatchException e) {
                    System.out.println("Zadaj iba cislo od 1 do 9");

                    scanner.nextLine();
                } catch (IllegalArgumentException e){
                    System.out.println(e.getMessage());
                }
                printPlocha(plocha);
            }




            choice--;

            if (choices.contains(choice)) {
                System.out.println("Pole uz je obsadene, zadaj ine pole");
                scanner.nextLine();
                continue;
            }

            choices.add(choice);

            if (count % 2 != 0) {
                plocha[choice / 3][choice % 3] = 'O';
            } else {
                plocha[choice / 3][choice % 3] = 'X';
            }


            if (checkWinner(plocha)) {
                printPlocha(plocha);
                if (count % 2 == 0) {
                    System.out.println("Vitaz je player X !");
                    break;
                }
                System.out.println("Vitaz je player O !");
            } else if (count == 8){
                System.out.println("Je to remiza!");
            }

            count++;
        }
    }


    public static boolean checkWinner(char[][] plocha) {
        for (int i = 0; i < plocha.length; i++) {
            if (plocha[i][0] == plocha[i][1] && plocha[i][1] == plocha[i][2] && plocha[i][0] != 0 ||
                    plocha[0][i] == plocha[1][i] && plocha[1][i] == plocha[2][i] && plocha[0][i] != 0) {
                return true;
            }
        }
        if (plocha[0][0] == plocha[1][1] && plocha[1][1] == plocha[2][2] && plocha[0][0] != 0) {
            return true;
        }
        if (plocha[0][2] == plocha[1][1] && plocha[1][1] == plocha[2][0] && plocha[0][2] != 0) {
            return true;
        }
        return false;
    }

    public static void printPlocha(char[][] plocha) {
        for (int i = 0; i < plocha.length; i++) {
            for (int j = 0; j < plocha.length; j++) {
                if (plocha[i][j] != 0) {
                    if (j < 2) {
                        System.out.print(plocha[i][j] + " | ");
                    } else {
                        System.out.print(plocha[i][j]);
                    }

                } else {
                    if (j < 2) {
                        System.out.print("  | ");
                    }

                }

            }
            if (i < 2) {
                System.out.println("\n---------");
            } else {
                System.out.println();
            }

        }
    }
}