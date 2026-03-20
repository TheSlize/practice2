package part1.part1_2.company.core;

/**
 * Задание 1.2 — Исправленный Employee с правильной инкапсуляцией
 *
 * Тема: инкапсуляция — один из четырёх принципов ООП.
 *
 * Ключевая теория:
 *   - Инкапсуляция = скрытие внутреннего состояния от внешнего кода.
 *   - Все поля — private.
 *   - Доступ через геттеры (чтение) и сеттеры (изменение).
 *   - Приватные методы скрывают детали; публичный метод-«фасад» вызывает их.
 *
 * Задача:
 *   1. Все поля — private (уже сделано)
 *   2. Геттеры для name, age, salary (но НЕ для password)
 *   3. promote() — public
 *   4. printSummary() — public
 *   5. validatePassword() остаётся private;
 *      добавьте public authenticate(input), который вызывает validatePassword().
 */
public class EmployeeFixed {

    private String name;
    private int age;
    private double salary;
    private String password;

    /**
     * Подсказка: this.name = name; this.age = age; ...
     */
    public EmployeeFixed(String name, int age, double salary, String password) {
        // ▼ ВАШ КОД ЗДЕСЬ ▼

        // ▲ КОНЕЦ ВАШЕГО КОДА ▲
    }

    /** Верните name */
    public String getName() {
        // ▼ ВАШ КОД ЗДЕСЬ ▼
        return name;
        // ▲ КОНЕЦ ВАШЕГО КОДА ▲
    }

    /** Верните age */
    public int getAge() {
        return age;
    }

    /** Верните salary */
    public double getSalary() {
        return salary;
    }

    // Геттер для password НЕ создаём — пароль не должен покидать объект.

    /**
     * Повышает зарплату.
     * Подсказка: this.salary += raise;
     */
    public void promote(double raise) {
        this.salary += raise;
    }

    /**
     * Выводит сводку.
     * Формат: "Иван, 30 лет, 80000.0 руб."
     */
    public void printSummary() {
        System.out.println(getName() + ", " + getAge() + " лет, " + getSalary() + " руб.");
    }

    private boolean validatePassword(String input) {
        return password.equals(input);
    }

    /**
     * Публичный фасад для приватного validatePassword().
     * Подсказка: return validatePassword(input);
     */
    public boolean authenticate(String input) {
        return validatePassword(input);
    }
}
