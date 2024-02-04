package org.example.in;

import org.example.entity.audit.Audit;
import org.example.entity.meter.MeterWater;
import org.example.entity.meter.UserMeter;
import org.example.model.repository.AuditRepository;
import org.example.model.repository.MeterWaterRepository;
import org.example.model.service.UserLogin;
import org.example.model.service.UserRegistration;
import org.example.entity.user.AdminPanel;
import org.example.entity.user.Role;
import org.example.entity.user.User;
import org.example.view.View;

import java.io.BufferedReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
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
                    if (!UserRegistration.registration(bufferedReader.readLine(), bufferedReader.readLine(), bufferedReader.readLine(), bufferedReader.readLine())) {
                        View.errorRegistration();
                    }
                    else {
                        View.successRegistration();
                    }
                    break;
                }
                case 2: {
                    View.enterLoginAndPassword();
                    String login = bufferedReader.readLine();
                    String password = bufferedReader.readLine();
                    User user = UserLogin.login(login, password);
                    if (user == null) {
                        View.errorLogin();
                        break;
                    }
                    View.hello(user.getFirstName(), user.getLastName());
                    Audit audit = new Audit("Вошёл в приложение", user.getId());
                    AuditRepository.addAudit(audit);
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
                        System.out.println(UserMeter.getLastMeterWater(user));
                        Audit audit = new Audit("Посмотрел актульные показания", user.getId());
                        AuditRepository.addAudit(audit);
                    } catch (NoSuchElementException e) {
                        View.noMeter();
                    }
                    break;
                }
                case 2: {
                    View.addNewMeterWater();
                    MeterWater meterWater = new MeterWater(Integer.parseInt(bufferedReader.readLine()), Integer.parseInt(bufferedReader.readLine()), LocalDate.now());
                    if (!UserMeter.handOverMeterWater(user, meterWater)) {
                        View.errorTransferMeter();
                    } else {
                        View.successTransferMeter();
                        Audit audit = new Audit("Добавил показания", user.getId());
                        AuditRepository.addAudit(audit);
                    }
                    break;
                }
                case 3: {
                    List<MeterWater> meterWaters = MeterWaterRepository.getAllMeterWaterUser(user);
                    if (meterWaters.isEmpty()) {
                        View.noMeter();
                    } else {
                        System.out.println(meterWaters);
                        Audit audit = new Audit("Посмотрел историю показаний", user.getId());
                        AuditRepository.addAudit(audit);
                    }
                    break;
                }
                case 4: {
                    View.monthOfMeter();
                    if (UserMeter.getSpecificPeriodMeterWater(Integer.parseInt(bufferedReader.readLine()), user) == null) {
                        View.noMeterInMonth();
                    } else {
                        System.out.println(UserMeter.getSpecificPeriodMeterWater(Integer.parseInt(bufferedReader.readLine()),user));
                        Audit audit = new Audit("Посмотрел показания за определенный месяц", user.getId());
                        AuditRepository.addAudit(audit);
                    }
                    break;
                }
                case 5: {
                    View.codeAdmin();
                    String code = bufferedReader.readLine();
                    if (code.equals(AdminPanel.getCode())) {

                        Audit audit = new Audit("Cтал администратором", user.getId());
                        AuditRepository.addAudit(audit);
                        adminMenu(user, bufferedReader);
                    }
                    break;
                }
                case 6:
                    flag = false;
                    Audit audit = new Audit("Вышел", user.getId());
                    AuditRepository.addAudit(audit);
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
                case 2:{
                    View.audit(AuditRepository.getAudit());
                    break;
                }
                case 3: {
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
                    List<MeterWater> meterWaters = MeterWaterRepository.getAllMeterWaterUser(user);
                    if (meterWaters.isEmpty()) {
                        View.noMeter();
                    } else {
                        System.out.println(meterWaters);
                    }
                    break;
                }
                case 2:
                    flag = false;
                    break;
            }
        }
    }
}
