package lesson2.Classes;

public class Field {
    protected static char[][] field;
    public static int fieldSizeX;
    public static int fieldSizeY;

    /**
     * Инициализация игрового поля
     */
    public static void initialise(){
        field = new char[fieldSizeX][fieldSizeY];
        fillingField();
    }

    /**
     * Заполнение поля символами отсутствия хода в клетке
     */
    private static void fillingField(){
        for(int y = 0; y < fieldSizeX; y++){
            for(int x = 0; x < fieldSizeY; x++){
                field[y][x] = GameProcess.SIGN_EMPTY;
            }
        }
    }

    /**
     * Метод печати поля
     */
    static void printField(){
        System.out.print('*');
        for(int i = 1; i <= fieldSizeY * 2; i++){
            if(i % 2 != 0) System.out.print('-');
            else System.out.print(i / 2);
        }
        System.out.println();
        for(int y = 0; y < fieldSizeY; y++){
            System.out.print(y+1);
            for(int x = 0; x < fieldSizeX; x++) {
                System.out.print('-');
                System.out.print(field[y][x]);
            }
            System.out.println();
        }
    }

}
