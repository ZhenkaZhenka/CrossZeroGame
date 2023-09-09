package lesson2.Classes;

import java.util.Random;
import java.util.Scanner;

public class GameProcess {
    public static final char SIGN_HUMAN = 'X';
    public static final char SIGN_AI = 'O';
    public static final char SIGN_EMPTY = '*';
    private static int lastX;
    private static int lastY;
    public static int amountForWin;

    /**
     * Метод хода игрока
     */
    protected static void turnPlayer(){
        boolean mark = true;
        int x, y;
        while(mark){
            try{
                x = getInteger("Введите координату X")-1;
                y = getInteger("Введите координату Y")-1;
                if (Field.field[y][x] != SIGN_EMPTY) throw new RuntimeException();
                Field.field[y][x] = SIGN_HUMAN;
                lastX = x;
                lastY = y;
                mark = false;
            } catch(IndexOutOfBoundsException e){
                System.out.println("Такого поля нет, возможно вы ошиблись. Попробуйте еще раз.");
            } catch(RuntimeException e){
                System.out.println("Это поле занято, выберите другое.");
            }
        }
    }

    /**
     * Метод хода ИИ
     */
    protected static void turnAI(){
        Boolean mark = true;
        Random r = new Random();
        int x, y;
        while(mark){
            x = r.nextInt(Field.fieldSizeX);
            y = r.nextInt(Field.fieldSizeY);
            if(Field.field[y][x] == SIGN_EMPTY){
                Field.field[y][x] = SIGN_AI;
                lastX = x;
                lastY = y;
                mark = false;
            }
        }
    }

    /**
     * Метод получения целого числа из консоли
     * @param message - информация для пользователя перед вводом
     * @return - целое число из консоли
     */
    private static int getInteger(String message){
        System.out.println(message);
        Scanner in = new Scanner(System.in);
        while(true) {
            try {
                int number = in.nextInt();
                return number;
            } catch(Exception e){
                System.out.println("Вы ввели не число, попробуйте еще");
            }
        }
    }
    private static void initialiseGame(){
        Field.fieldSizeX = getInteger("Введите размерность поля по оси Х: ");
        Field.fieldSizeY = getInteger("Введите размерность поля по оси Y: ");
        amountForWin = getInteger("Введите размер комбинации символа в ряд для победы: ");
        Field.initialise();
    }

    public static void proccess(){
        initialiseGame();
        while(true){
            Field.printField();
            turnPlayer();
            if(CheckWin.checkingWin(lastX,lastY,amountForWin,SIGN_HUMAN)){
                System.out.println("Человек победил");
                return;
            }
            Field.printField();
            turnAI();
            if(CheckWin.checkingWin(lastX,lastY,amountForWin,SIGN_AI)){
                System.out.println("Компьютер победил");
                return;
            }
            if(CheckWin.checkingDraw()){
                System.out.println("Ничья!");
                return;
            }
        }
    }
}
