package homework03;

import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        System.out.println("Введите данные в произвольном порядке, разделенные пробелом\n" +
                "1. Фамилия Имя Отчество\n" +
                "2. Дата рождения в формате dd.mm.yyyy\n" +
                "3. Номер телефона - 10 цифр\n" +
                "4. Пол - символ латиницей f или m");

        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        String[] lines = input.split(" ");

        try {
            countString(lines);
            StringBuilder userData = new StringBuilder();
            userData.append(nameUser(lines));
            userData.append(dateBirthUser(lines));
            userData.append(phoneUser(lines));
            userData.append(genderUser(lines));
            try (FileWriter fw = new FileWriter(
                    "src/" + nameUser(lines).split(" ")[0] + ".txt", true)) {
                fw.write(userData + "\n");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (IllegalArgumentException | NullPointerException | NameUserException | GenderUserException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }

    }

    public static void countString(String[] lines) {
        if (lines == null) {
            throw new NullPointerException("null");
        }
        if (lines.length != 6) {
            throw new IllegalArgumentException("Количество параметров не совпадает");
        }
    }

    public static String nameUser(String[] lines) {
        if (lines == null) {
            throw new NullPointerException("null");
        }
        StringBuilder result = new StringBuilder();
        int count = 0;
        for (String line : lines) {
            if (line.matches("[а-яА-Я]{4,15}")) {
                count++;
                result.append(line).append(" ");
            } else if (count == 1 || count == 2) {
                throw new NameUserException("ФИО не по порядку");
            }
        }
        if (count != 3) {
            throw new NameUserException("ФИО состоит более чем из 3 слов");
        }
        return result.toString();
    }

    public static String dateBirthUser(String[] lines) {
        if (lines == null) {
            throw new NullPointerException("null");
        }
        StringBuilder result = new StringBuilder();
        int count = 0;
        for (String line : lines) {
            if (isDate(line)) {
                count++;
                result.append(line).append(" ");
            }
        }
        if (count != 1) {
            throw new IllegalArgumentException("Не верный формат даты");
        }
        return result.toString();
    }

    public static boolean isDate(String line) {
        DateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        sdf.setLenient(false);
        try {
            sdf.parse(line);
        } catch (ParseException e) {
            return false;
        }
        return true;
    }

    public static String phoneUser(String[] lines) {
        if (lines == null) {
            throw new NullPointerException("null");
        }
        StringBuilder result = new StringBuilder();
        int count = 0;
        for (String line : lines) {
            if (isPhone(line)) {
                count++;
                result.append(line).append(" ");
            }
        }
        if (count != 1) {
            throw new IllegalArgumentException("Не верный формат телефона");
        }
        return result.toString();
    }

    public static boolean isPhone(String line) {
        return line.matches("[0-9]{10}");
    }

    public static String genderUser(String[] lines) {
        if (lines == null) {
            throw new NullPointerException("null");
        }
        StringBuilder result = new StringBuilder();
        int count = 0;
        for (String line : lines) {
            if (isGender(line)) {
                count++;
                result.append(line).append(" ");
            }
        }
        if (count != 1) {
            throw new GenderUserException("Не верный параметр пола");
        }
        return result.toString();
    }

    public static boolean isGender(String line) {
        String genderM = "m";
        String genderF = "f";
        return line.equals(genderM) || line.equals(genderF);
    }
}
