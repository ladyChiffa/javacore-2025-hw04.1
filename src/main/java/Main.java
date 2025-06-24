import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String input;
        System.out.print("Введите мин. длину пароля: ");
        input = scanner.nextLine(); // "5"
        int minLength = Integer.parseInt(input);

        System.out.print("Введите макс. допустимое количество повторений символа подряд: ");
        input = scanner.nextLine(); // "3"
        int maxRepeats = Integer.parseInt(input);

        PasswordChecker p = new PasswordChecker();
        p.setMinLength(minLength);
        p.setMaxRepeats(maxRepeats);
        boolean passwordFits;

        while (true) {
            System.out.print("Введите пароль или end: ");
            input = scanner.nextLine(); // "passswordddd или end"
            if (input.equals("end")) {
                break;
            }

            passwordFits = p.verify(input);
            if (passwordFits) {
                System.out.println("Подходит!");
            }
            else {
                System.out.println("Не подходит!");
            }

        }
        System.out.println();
        System.out.println("Программа завершена");

    }
}
