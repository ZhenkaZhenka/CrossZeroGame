package lesson2.Classes;

public class CheckWin {
    protected static boolean checkingDraw(){
        for(int x = 0; x < Field.fieldSizeX; x++){
            for (int y = 0; y < Field.fieldSizeY; y ++){
                if(Field.field[y][x] == GameProcess.SIGN_EMPTY){
                    return false;
                }
            }
        }
        return true;
    }
    protected static boolean checkingWin(int x, int y, int amount, char c){
        return checkHorizontal(x,y,0, amount,c) || checkVertical(x,y,0, amount,c)
                || checkDLDRU(x,y,0, amount,c) || checkDLURD(x,y,0, amount,c);
    }

    /**
     * Подсчет одинаковых символов по-вертикали
     * @param x - Координата Х последнего хода
     * @param y - Координата У последнего хода
     * @param counter - Счетчик совпадения знака сходившего игрока
     * @param c - Символ сходившего игрока
     * @return - Количество совпадений знака сходившего игрока
     */
    private static boolean checkVertical(int x, int y, int counter, int amount, char c){
        counter = stepUp(x,y,counter,c) + stepDown(x,y+1,counter,c);
        //counter = stepDown(x,y+1,counter,c);
        if (counter == amount){
            return true;
        }
        else{
            return false;
        }
    }
    /**
     * Подсчет одинаковых символов по-горизонтали
     * @param x - Координата Х последнего хода
     * @param y - Координата У последнего хода
     * @param counter - Счетчик совпадения знака сходившего игрока
     * @param c - Символ сходившего игрока
     * @return - Количество совпадений знака сходившего игрока
     */
    private static boolean checkHorizontal(int x, int y, int counter, int amount, char c){
        counter = stepLeft(x,y,counter,c);
        counter = stepRight(x+1,y,counter,c);
        if (counter == amount){
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * DLDRU - Diagonal from left down to right up
     * Подсчет одинаковых символов по-диагонали слева снизу вправо вверх
     * @param x - Координата Х последнего хода
     * @param y - Координата У последнего хода
     * @param counter - Счетчик совпадения знака сходившего игрока
     * @param c - Символ сходившего игрока
     * @return - Количество совпадений знака сходившего игрока
     */
    private static boolean checkDLDRU(int x, int y, int counter, int amount, char c){
        counter = stepDLD(x,y,counter,c);
        counter = stepDRU(x+1,y-1,counter,c);
        if (counter == amount) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * DLURD - Diagonal from left up to right down
     * Подсчет одинаковых символов по-диагонали слева снизу вправо вверх
     * @param x - Координата Х последнего хода
     * @param y - Координата У последнего хода
     * @param counter - Счетчик совпадения знака сходившего игрока
     * @param c - Символ сходившего игрока
     * @return - Количество совпадений знака сходившего игрока
     */
    private static boolean checkDLURD(int x, int y, int counter, int amount, char c){
        counter = stepDLU(x,y,counter,c);
        counter = stepDRD(x+1,y+1,counter,c);
        if (counter == amount){
            return true;
        }
        else {
            return false;
        }
    }
    /**
     * Метод подсчета одинаковых символов с шагов вверх от введенной координаты
     * @param x - Координата Х последнего хода
     * @param y - Координата У последнего хода
     * @param counter - Счетчик совпадения знака сходившего игрока
     * @param c - Символ сходившего игрока
     * @return - Количество совпадений знака сходившего игрока
     */
    private static int stepUp(int x, int y, int counter, char c){
        if(y >= 0 & Field.field[y][x] == c){
            counter++;
            if(y > 0){
                return stepUp(x, y-1, counter, c);
            }
        }
        return counter;
    }

    /**
     * Метод подсчета одинаковых символов с шагов вниз от введенной координаты
     * @param x - Координата Х последнего хода
     * @param y - Координата У последнего хода
     * @param counter - Счетчик совпадения знака сходившего игрока
     * @param c - Символ сходившего игрока
     * @return - Количество совпадений знака сходившего игрока
     */
    private static int stepDown(int x, int y, int counter, char c){
        if(y < Field.fieldSizeY && Field.field[y][x] == c){
            counter++;
            if(y+1 < Field.fieldSizeY){
                return stepDown(x, y+1, counter, c);
            }
        }
        return counter;
    }

    /**
     * DRD - Diagonal Right Down
     * Метод подсчета одинаковых символов с шагов вниз вправо по-диагонали от введенной координаты
     * @param x - Координата Х последнего хода
     * @param y - Координата У последнего хода
     * @param counter - Счетчик совпадения знака сходившего игрока
     * @param c - Символ сходившего игрока
     * @return - Количество совпадений знака сходившего игрока
     */
    private static int stepDRD(int x, int y, int counter, char c){
        if(y <= Field.fieldSizeY-1 && x <= Field.fieldSizeX-1 && Field.field[y][x] == c){
            counter++;
            if(y < Field.fieldSizeY && x < Field.fieldSizeX) {
                return stepDRD(x + 1, y + 1, counter, c);
            }
        }
        return counter;
    }
    /**
     * DRU - Diagonal Right Up
     * Метод подсчета одинаковых символов с шагов вверх вправо по-диагонали от введенной координаты
     * @param x - Координата Х последнего хода
     * @param y - Координата У последнего хода
     * @param counter - Счетчик совпадения знака сходившего игрока
     * @param c - Символ сходившего игрока
     * @return - Количество совпадений знака сходившего игрока
     */
    private static int stepDRU(int x, int y, int counter, char c){
        if(y >= 0 && x <= Field.fieldSizeX-1 && Field.field[y][x] == c){
            counter++;
            if(y > 0 && x < Field.fieldSizeX) {
                return stepDRU(x + 1, y - 1, counter, c);
            }
        }
        return counter;
    }
    /**
     * DLU - Diagonal Left Up
     * Метод подсчета одинаковых символов с шагов вверх влево по-диагонали от введенной координаты
     * @param x - Координата Х последнего хода
     * @param y - Координата У последнего хода
     * @param counter - Счетчик совпадения знака сходившего игрока
     * @param c - Символ сходившего игрока
     * @return - Количество совпадений знака сходившего игрока
     */
    private static int stepDLU(int x, int y, int counter, char c){
        if(y >= 0 && x >= 0 && Field.field[y][x] == c){
            counter++;
            if(y > 0 && x > 0) {
                return stepDLU(x - 1, y - 1, counter, c);
            }
        }
        return counter;
    }
    /**
     * DLD - Diagonal Left Down
     * Метод подсчета одинаковых символов с шагов вниз влево по-диагонали от введенной координаты
     * @param x - Координата Х последнего хода
     * @param y - Координата У последнего хода
     * @param counter - Счетчик совпадения знака сходившего игрока
     * @param c - Символ сходившего игрока
     * @return - Количество совпадений знака сходившего игрока
     */
    private static int stepDLD(int x, int y, int counter, char c){
        if(y < Field.fieldSizeY && x >= 0 && Field.field[y][x] == c){
            counter++;
            if(y < Field.fieldSizeY && x > 0) {
                return stepDLD(x - 1, y + 1, counter, c);
            }
        }
        return counter;
    }
    /**
     * Метод подсчета одинаковых символов с шагов влево от введенной координаты
     * @param x - Координата Х последнего хода
     * @param y - Координата У последнего хода
     * @param counter - Счетчик совпадения знака сходившего игрока
     * @param c - Символ сходившего игрока
     * @return - Количество совпадений знака сходившего игрока
     */
    private static int stepLeft(int x, int y, int counter, char c){
        if(x >= 0 && Field.field[y][x] == c){
            counter++;
            if(x > 0) {
                return stepLeft(x - 1, y, counter, c);
            }
        }
        return counter;
    }
    /**
     * Метод подсчета одинаковых символов с шагов вправо от введенной координаты
     * @param x - Координата Х последнего хода
     * @param y - Координата У последнего хода
     * @param counter - Счетчик совпадения знака сходившего игрока
     * @param c - Символ сходившего игрока
     * @return - Количество совпадений знака сходившего игрока
     */
    private static int stepRight(int x, int y, int counter, char c){
        if(x < Field.fieldSizeX && Field.field[y][x] == c){
            counter++;
            if(x < Field.fieldSizeX) {
                return stepRight(x + 1, y, counter, c);
            }
        }
        return counter;
    }
}
