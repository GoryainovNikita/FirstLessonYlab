package org.example.in;

import org.example.model.meter.MeterWater;
import org.example.model.service.Login;
import org.example.model.service.Registration;
import org.example.model.user.AdminPanel;
import org.example.model.user.Role;
import org.example.model.user.User;
import org.example.view.View;

import java.io.BufferedReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.NoSuchElementException;

/**
 * Контроллер, который обрабатывает ввод пользователя и вызывает определенные методы, исходя из этого ввода
 */
public class Controller {

    /**
     * Основной метод контроллера, с которого начинается работа программы.
     * Варианты ввода с консоли :
     * 1. - Регистрация пользователя
     * 2. - Вход пользователя
     * 3. - Выход из приложения
     * @param bufferedReader
     * @throws IOException
     */
    public void start(BufferedReader bufferedReader) throws IOException {

        boolean flag = true;
        while (flag) {
            View.greeting();
            int cons = Integer.parseInt(bufferedReader.readLine());
            switch (cons) {
                case 1: {
                    View.createNewUser();
                    if (!Registration.registration(bufferedReader.readLine(), bufferedReader.readLine(), bufferedReader.readLine(), bufferedReader.readLine())) {
                        View.errorRegistration();
                    }
                    View.successRegistration();
                    break;
                }
                case 2: {
                    View.enterLoginAndPassword();
                    String login = bufferedReader.readLine();
                    String password = bufferedReader.readLine();
                    User user = Login.login(login, password);
                    if (user == null) {
                        View.errorLogin();
                        break;
                    }
                    View.hello(user.getFirstName(), user.getLastName());
                    user.getAudit().add("Пользователь вошёл в приложение");
                    mainMenu(user, bufferedReader);
                    break;
                }
                case 3: {
                    flag = false;
                    break;
                }
                default:
                    View.defaultStr();
            }
        }
    }

    /**
     * Второй метод контроллера, обслуживающий пользователя, прошедшего регистрацию.
     * @param user
     * @param bufferedReader
     * @throws IOException
     */
    public void mainMenu(User user, BufferedReader bufferedReader) throws IOException {
        boolean flag = true;
        while (flag) {
            View.menu();
            int choice = Integer.parseInt(bufferedReader.readLine());
            switch (choice) {
                case 1: {
                    try {
                        System.out.println(user.getUserMeter().getLastMeterWater());
                        user.getAudit().add("Пользователь посмотрел актульные показания");
                    } catch (NoSuchElementException e) {
                        View.noMeter();
                    }

                    break;
                }
                case 2: {
                    View.addNewMeterWater();
                    MeterWater meterWater = new MeterWater(Integer.parseInt(bufferedReader.readLine()), Integer.parseInt(bufferedReader.readLine()), LocalDate.now());
                    if (!user.getUserMeter().handOverMeterWater(meterWater)) {
                        View.errorTransferMeter();
                    } else {
                        View.successTransferMeter();
                        user.getAudit().add("Пользователь добавил показания");
                    }
                    break;
                }
                case 3: {
                    if (user.getUserMeter().getMeterList().isEmpty()) {
                        View.noMeter();
                    } else {
                        System.out.println(user.getUserMeter().getMeterList());
                        user.getAudit().add("Пользователь посмотрел историю показаний");
                    }
                    break;
                }
                case 4: {
                    View.monthOfMeter();
                    if (user.getUserMeter().getSpecificPeriodMeterWater(Integer.parseInt(bufferedReader.readLine())) == null) {
                        View.noMeterInMonth();
                    } else {
                        System.out.println(user.getUserMeter().getSpecificPeriodMeterWater(Integer.parseInt(bufferedReader.readLine())));
                        user.getAudit().add("Пользователь посмотрел показания за определенный месяц");
                    }
                    break;
                }
                case 5: {
                    View.codeAdmin();
                    String code = bufferedReader.readLine();
                    if (code.equals(AdminPanel.getCode())) {
                        user.getAudit().add("Пользователь стал администратором");
                        adminMenu(user, bufferedReader);
                    }
                    break;
                }
                case 6:
                    flag = false;
                    user.getAudit().add("Пользователь вышел");
                    break;
                default:
                    View.defaultStr();
            }
        }
    }

    /**
     * Метод контроллера, отвечающий за вывод меню в режиме администрирования. Данное меню доступно после того, как пользователь решает стать
     * администратором и вводит пароль администратора.
     * @param user
     * @param bufferedReader
     * @throws IOException
     */

    public void adminMenu(User user, BufferedReader bufferedReader) throws IOException {
        user.setRole(Role.ADMIN);
        boolean flag = true;
        while (flag) {
            View.adminMenu();
            int console = Integer.parseInt(bufferedReader.readLine());
            switch (console) {
                case 1: {
                    View.displayingUsers(AdminPanel.getUsers());
                    int cons = Integer.parseInt(bufferedReader.readLine());
                    adminUserMenu(AdminPanel.getUserById(cons), bufferedReader);
                    break;
                }
                case 2: {
                    flag = false;
                    user.setRole(Role.USER);
                    break;
                }
                default:
                    View.defaultStr();
            }
        }
    }

    /**
     * Вспомогательный метод в меню администратора.
     * @param user
     * @param bufferedReader
     * @throws IOException
     */
    public void adminUserMenu(User user, BufferedReader bufferedReader) throws IOException {
        boolean flag = true;
        while (flag) {
            View.menuWithoutAdmin();
            int console = Integer.parseInt(bufferedReader.readLine());
            switch (console){
                case 1: {
                    if (user.getUserMeter().getMeterList().isEmpty()) {
                        View.noMeter();
                    } else {
                        System.out.println(user.getUserMeter().getMeterList());
                    }
                    break;
                }
                case 2: {
                    View.audit(user.getAudit());
                    break;
                }
                case 3:
                    flag = false;
                    break;
            }
        }
    }
}
