package tictactoe;

import java.util.Scanner;

public class TicTacToe {

    public static void main(String[] args) {
        int y = 0;
        do {
            Scanner sc = new Scanner(System.in);
            System.out.println("Pasirink zaidimo lentos dydi. Ivesk skaiciu nuo 3 iki 9");
            String s = sc.nextLine();
            try {
                int x = Integer.parseInt(s);
                y = x;
            } catch (NumberFormatException nfe) {
                System.out.println("Ivesk s k a i c i u  nuuu");
            }
        } while (y < 3 || y > 9);

        String[][] zaidimoLenta = new String[y][y];
        for (int i = 0; i < zaidimoLenta.length; i++) {
            for (int j = 0; j < zaidimoLenta[i].length; j++) {
                zaidimoLenta[i][j] = " - ";
            }
        }
        System.out.println("Nupieseme lenta " + y + " x " + y + " dydzio. Tu busi iksas, ok? Pradekime zaidima xD");
        printLenta(zaidimoLenta);
        ejimai(zaidimoLenta);
    }

    public static void ejimai(String[][] zaidimoLenta) {
        int x = 0;
        int y = 0;
        do {
            do {
                do {
                    Scanner xPrompt = new Scanner(System.in);
                    System.out.println("Pasirink X koordinate. Ivesk skaiciu, ne didesni uz " + zaidimoLenta.length);
                    String s = xPrompt.nextLine();
                    try {
                        int xKoord = Integer.parseInt(s);
                        x = xKoord - 1;
                    } catch (NumberFormatException nfe) {
                        System.out.println("Ivesk  s k a i c i u  nuuu");
                    }
                } while (x < 0 || x >= zaidimoLenta.length);

                do {
                    Scanner yPrompt = new Scanner(System.in);
                    System.out.println("Pasirink Y koordinate. Ivesk skaiciu, ne didesni uz " + zaidimoLenta.length);
                    String s = yPrompt.nextLine();
                    try {
                        int yKoord = Integer.parseInt(s);
                        y = yKoord - 1;
                    } catch (NumberFormatException nfe) {
                        System.out.println("Ivesk  s k a i c i u  nuuu");
                    }
                } while (y < 0 || y >= zaidimoLenta.length);

            } while (!zaidimoLenta[x][y].equals(" - "));

            zaidimoLenta[x][y] = " X ";
            printLenta(zaidimoLenta);
            zaidimoLenta = kompiuterioEile(zaidimoLenta);

        } while (!arNiekasNelaimejo(zaidimoLenta));

    }

    public static boolean arNiekasNelaimejo(String[][] zaidimoLenta) {
        String rezultatai = ziurimRezultatus(zaidimoLenta);
        if (rezultatai.equals(" X ")) {
            System.out.println("Laimejai");
            return true;
        } else if (rezultatai.equals(" O ")) {
            System.out.println("Laimejo kompas");
            return true;
        } else {
            for (int i = 0; i < zaidimoLenta.length; i++) {
                for (int j = 0; j < zaidimoLenta[i].length; j++) {
                    if (zaidimoLenta[i][j].equals(" - ")) {
                        System.out.println("Teskim zaidima");
                        return false;
                    }
                    System.out.println("Lenta uzsipilde. Lygiosios");
                    return true;
                }
            }
        }
        return false;
    }

    public static String[][] kompiuterioEile(String[][] zaidimoLenta) {
        System.out.println("Kompiuterio ejimas: ");
        int x = 0;
        int y = 0;
        do {
            x = (int) (Math.random() * zaidimoLenta.length);
            y = (int) (Math.random() * zaidimoLenta.length);
        } while (!zaidimoLenta[x][y].equals(" - "));
        zaidimoLenta[x][y] = " O ";
        printLenta(zaidimoLenta);
        return zaidimoLenta;
    }

    public static String ziurimRezultatus(String[][] zaidimoLenta) {
        for (int i = 0; i < zaidimoLenta.length - 1; i++) {
            for (int j = 0; j < zaidimoLenta[i].length; j++) {
                if (!zaidimoLenta[i][j].equals("")) {
                    //kaimynas toje pacioje eileje, is desines, visiems, tik ne dviems paskutiniams J
                    if (j < zaidimoLenta[i].length - 3 && zaidimoLenta[i][j + 1].equals(zaidimoLenta[i][j])
                            && zaidimoLenta[i][j + 2].equals(zaidimoLenta[i][j])) {
                        return zaidimoLenta[i][j];
                    }
                } else if (j < zaidimoLenta[i].length - 3 && zaidimoLenta[i + 1][j + 1].equals(zaidimoLenta[i][j]) && zaidimoLenta[i + 1 + 1][j + 1 + 1].equals(zaidimoLenta[i][j])) {

                    return zaidimoLenta[i][j];
                } else if (i < zaidimoLenta[i].length - 3 && zaidimoLenta[i + 1][j].equals(zaidimoLenta[i][j]) && zaidimoLenta[i + 1 + 1][j].equals(zaidimoLenta[i][j])) {

                    return zaidimoLenta[i][j];
                } //pirmieji elementai mes klaida:
                else if (j > 1 && zaidimoLenta[i + 1][j - 1].equals(zaidimoLenta[i][j]) && zaidimoLenta[i + 1 + 1][j - 1 - 1].equals(zaidimoLenta[i][j])) {

                    return zaidimoLenta[i][j];
                }
            }
        }
        return "";
    }

    public static void printLenta(String[][] zaidimoLenta) {
        for (int i = 0; i < zaidimoLenta.length; i++) {
            for (int j = 0; j < zaidimoLenta[i].length; j++) {
                System.out.print(zaidimoLenta[i][j]);
            }
            System.out.println();
        }
    }
}
