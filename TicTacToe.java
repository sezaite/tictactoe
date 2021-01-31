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

        //sukuriama ivesto dydzio kvardarine lenta ir vietoj reiksmiu irasomi minusai
        String[][] zaidimoLenta = new String[y][y];
        for (int i = 0; i < zaidimoLenta.length; i++) {
            for (int j = 0; j < zaidimoLenta[i].length; j++) {
                zaidimoLenta[i][j] = " - ";
            }
        }
        System.out.println("Nupieseme lenta " + y + " x " + y + " dydzio. Tu busi iksas, ok? Pradek zaidima");
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
                    System.out.println("Pasirink X koordinate. Ivesk sveikaji skaiciu, ne didesni uz " + zaidimoLenta.length);
                    String s = xPrompt.nextLine();
                    try {
                        int xKoord = Integer.parseInt(s);
                        x = xKoord - 1;
                    } catch (NumberFormatException nfe) {
                        System.out.println("Ivesk  s k a i c i u  nuuu");
                    }
                } while (x < 0 || x > zaidimoLenta.length);

                do {
                    Scanner yPrompt = new Scanner(System.in);
                    System.out.println("Pasirink Y koordinate. Ivesk sveikaji skaiciu, ne didesni uz " + zaidimoLenta.length);
                    String s = yPrompt.nextLine();
                    try {
                        int yKoord = Integer.parseInt(s);
                        y = yKoord - 1;
                    } catch (NumberFormatException nfe) {
                        System.out.println("Ivesk  s k a i c i u  nuuu");
                    }
                } while (y < 0 || y > zaidimoLenta.length);

            } while (!(arLaisvasLangelis(x, y, zaidimoLenta)));
            zaidimoLenta[y][x] = " X ";
            printLenta(zaidimoLenta);
            if (!arNiekasNelaimejo(zaidimoLenta)) {
                zaidimoLenta = kompiuterioEile(zaidimoLenta);
            } else {
                break;
            }

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
        } else if (arUzsipildeLenta(zaidimoLenta)) {
            return true;
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
        } while (!zaidimoLenta[y][x].equals(" - "));
        zaidimoLenta[y][x] = " O ";
        printLenta(zaidimoLenta);
        return zaidimoLenta;
    }

    public static String ziurimRezultatus(String[][] zaidimoLenta) {
        for (int i = 0; i < zaidimoLenta.length; i++) {
            for (int j = 0; j < zaidimoLenta[i].length; j++) {
                if (!(zaidimoLenta[i][j].equals(" - "))) {
                    //netikrinti horizontaliuju pergaliu jei tikrinamasis per arti desiniojo krasto
                    if (j < zaidimoLenta[i].length - 2 && zaidimoLenta[i][j + 1].equals(zaidimoLenta[i][j])
                            && zaidimoLenta[i][j + 2].equals(zaidimoLenta[i][j])) {

                        return zaidimoLenta[i][j];
                        //netikrinti istrizuju i desine, jei tikrinamasis per arti desiniojo krasto
                    } else if (j < zaidimoLenta[i].length - 2 && i < zaidimoLenta[i].length - 2 && zaidimoLenta[i + 1][j + 1].equals(zaidimoLenta[i][j]) && zaidimoLenta[i + 1 + 1][j + 1 + 1].equals(zaidimoLenta[i][j])) {

                        return zaidimoLenta[i][j];
                        //netikrinti vertikaliuju kai tikrinamasis per arti apacios
                    } else if (i < zaidimoLenta[i].length - 2 && zaidimoLenta[i + 1][j].equals(zaidimoLenta[i][j]) && zaidimoLenta[i + 1 + 1][j].equals(zaidimoLenta[i][j])) {

                        return zaidimoLenta[i][j];
                    } //netikrinti istrizu i kaire kai tikrinamasis per arti kairiojo krasto
                    else if (j > 1 && i < zaidimoLenta.length - 2 && zaidimoLenta[i + 1][j - 1].equals(zaidimoLenta[i][j]) && zaidimoLenta[i + 1 + 1][j - 1 - 1].equals(zaidimoLenta[i][j])) {

                        return zaidimoLenta[i][j];
                    }
                }
            }
        }
        return " - ";
    }

    public static void printLenta(String[][] zaidimoLenta) {
        System.out.print("  x  ");
        for (int i = 0; i < zaidimoLenta.length; i++) {
            System.out.print(i + 1 + "  ");
        }
        System.out.println();
        System.out.println("y");
        for (int i = 0; i < zaidimoLenta.length; i++) {
            System.out.print(i + 1 + "   ");
            for (int j = 0; j < zaidimoLenta[i].length; j++) {
                System.out.print(zaidimoLenta[i][j]);
            }
            System.out.println();
        }
    }

    public static boolean arUzsipildeLenta(String[][] zaidimoLenta) {
        for (int i = 0; i < zaidimoLenta.length; i++) {
            for (int j = 0; j < zaidimoLenta[i].length; j++) {
                if (zaidimoLenta[i][j].equals(" - ")) {
                    System.out.println("Laimetoju nematau. Vaziuojam toliau");
                    return false;
                }
            }
        }
        System.out.println("Lenta uzsipilde. Lygiosios");
        return true;
    }

    public static boolean arLaisvasLangelis(int x, int y, String[][] zaidimoLenta) {
        if (!zaidimoLenta[y][x].equals(" - ")) {
            System.out.println("ups, sita pozicija jau uzimta");
            return false;
        }
        return true;
    }

}
