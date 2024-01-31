package org.example.view;

import org.example.entity.user.User;

import java.util.List;

/**
 * Класс отвечающий за вывод пользователю информации.
 */
public class View {

    public static void greeting() {
        System.out.println("Добро пожаловать в наше приложение:\n" +
                "1. Зарегистрироваться\n" +
                "2. Войти\n" +
                "3. Выйти из программы\n");
    }

    public static void enterLoginAndPassword() {
        System.out.println("Введите свой логин и пароль\n");
    }

    public static void errorLogin() {
        System.out.println("Данного пользователя не существует, либо вы ввели неправильный логин и пароль\n");
    }

    public static void hello(String firstName, String lastName) {
        System.out.println(
                "Здравствуйте " + firstName + " " + lastName + "\n");
    }

    public static void menu() {
        System.out.println(
                "1. Посмотреть актуальные показания\n" +
                        "2. Сдать новые показания\n" +
                        "3. Посмотреть историю показаний\n" +
                        "4. Посмотреть показания за определенный месяц\n" +
                        "5. Стать администратором\n" +
                        "6. Выйти\n");
    }

    public static void createNewUser() {
        System.out.println("Введите:\n" +
                "1. Имя пользователя\n" +
                "2. Фамилию пользователя\n" +
                "3. Логин пользователя\n" +
                "4. Пароль\n");

    }

    public static void errorRegistration() {
        System.out.println("Данный пользователь уже зарегистрирован\n");
    }

    public static void successRegistration() {
        System.out.println("Поздравляю вы успешно зарегистрированы\n");
    }

    public static void addNewMeterWater() {
        System.out.println("Введите:\n" +
                "1. Показания холодной воды\n" +
                "2. Показания горячей воды\n");

    }

    public static void errorTransferMeter() {
        System.out.println("Показания не могут быть меньше предыдущих, либо не прошёл месяц со сдачи прошлых\n");
    }

    public static void successTransferMeter() {
        System.out.println("Показания успешно сданы\n");
    }

    public static void monthOfMeter() {
        System.out.println("Введите число соответствующего месяца:\n");
    }

    public static void noMeter() {
        System.out.println("Вы ещё не передавали показания\n");
    }

    public static void noMeterInMonth() {
        System.out.println("Вы не передавали показания в данном месяце\n");
    }

    public static void codeAdmin() {
        System.out.println("Введите код администратора\n");
    }

    public static void adminMenu() {
        System.out.println(
                "1. Показать показания пользователей\n" +
                        "2. Посмотреть аудит пользователей\n" +
                        "3. Выйти из режима администратора\n");
    }

    public static void displayingUsers(List<User> users) {
        for (int i = 0; i < users.size(); i++) {
            System.out.println(i + 1 + "." + users.get(i).toString());
        }
        System.out.println("Введите номер пользователя: ");
    }

    public static void menuWithoutAdmin() {
        System.out.println(
                "1. Посмотреть историю показаний\n" +
                        "2. Выйти");
    }

    public static void defaultStr() {
        System.out.println("Вы видимо опечатались");
    }

    public static void audit(List<String> strings){
        for(int i = 0; i<strings.size(); i++){
            System.out.println(i+1 + ". " + strings.get(i));
        }
        System.out.println();
    }


}
