import java.util.Scanner;
public class Main {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        System.out.println(calc(input));
    }

    public static String calc(String input) throws Exception {
        int sNumber, fNumber;
        String output;
        char oTerm = ' ';
        boolean isRoman;
        char[] searchOp = new char[9];
        for (int i = 0; i < input.length(); i++) {
            searchOp[i] = input.charAt(i);
            if (searchOp[i] == '+') {
                oTerm = '+';
            }
            if (searchOp[i] == '-') {
                oTerm = '-';
            }
            if (searchOp[i] == '*') {
                oTerm = '*';
            }
            if (searchOp[i] == '/') {
                oTerm = '/';
            }
        }
        String[] operators = input.split("[+-/*]");
        if (operators.length !=2) throw new Exception("т.к. формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");
        if (Roman.isRoman(operators[0]) && Roman.isRoman(operators[1])) {
            fNumber = Roman.convertToArabic(operators[0]);
            sNumber = Roman.convertToArabic(operators[1]);
            isRoman = true;
        }
        else if (!Roman.isRoman(operators[0]) && !Roman.isRoman(operators[1])) {
            fNumber = Integer.parseInt(operators[0].trim());
            sNumber = Integer.parseInt(operators[1].trim());
            isRoman = false;
        }
        else {
            throw new Exception("т.к. используются одновременно разные системы счисления");
        }
        if(fNumber > 10 || sNumber > 10 || sNumber == 0 || fNumber ==0) {
            throw new Exception("Калькулятор должен принимать на вход числа от 1 до 10 включительно, не более.");
        }
        int arabicResult = calculation(fNumber,sNumber,oTerm);
        if(isRoman) {
            if(arabicResult <= 0) {
                throw new Exception("т.к. в римской системе нет отрицательных чисел");
            }
            output = Roman.convertToRoman(arabicResult);
        }else{
            output = String.valueOf(arabicResult);
        }
        return output;
    }
    static int calculation(int num1, int num2, char oTerm){
        if(oTerm == '+') return num1 + num2;
        else if(oTerm == '-') return num1 - num2;
        else if(oTerm == '*') return num1 * num2;
        else return num1/num2;
    }
}
class Roman {
    static String[] romanArray = new String[]{"O", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X", "XI", "XII", "XIII", "XIV", "XV", "XVI", "XVII", "XVIII", "XIX", "XX",
            "XXI", "XXII", "XXIII", "XXIV", "XXV", "XXVI", "XXVII", "XXVIII", "XXIX", "XXX", "XXXI", "XXXII", "XXXIII", "XXXIV", "XXXV", "XXXVI", "XXXVII", "XXXVIII", "XXXIX", "XL",
            "XLI", "XLII", "XLIII", "XLIV", "XLV", "XLVI", "XLVII", "XLVIII", "XLIX", "L", "LI", "LII", "LIII", "LIV", "LV", "LVI", "LVII", "LVIII", "LIX", "LX",
            "LXI", "LXII", "LXIII", "LXIV", "LXV", "LXVI", "LXVII", "LXVIII", "LXIX", "LXX",
            "LXXI", "LXXII", "LXXIII", "LXXIV", "LXXV", "LXXVI", "LXXVII", "LXXVIII", "LXXIX", "LXXX",
            "LXXXI", "LXXXII", "LXXXIII", "LXXXIV", "LXXXV", "LXXXVI", "LXXXVII", "LXXXVIII", "LXXXIX", "XC",
            "XCI", "XCII", "XCIII", "XCIV", "XCV", "XCVI", "XCVII", "XCVIII", "XCIX", "C"};
    public static boolean isRoman(String rom) {
        for (int i = 0; i < romanArray.length; i++){
            if(rom.equals(romanArray[i])){
                return true;
            }
        }
        return false;
    }
    public static int convertToArabic(String roman) {
        for(int i = 0; i < romanArray.length; i++){
            if (roman.equals(romanArray[i])){
                return i;
            }
        }
        return -1;
    }
    public static String convertToRoman(int arabicResult) { return romanArray[arabicResult];}
}
