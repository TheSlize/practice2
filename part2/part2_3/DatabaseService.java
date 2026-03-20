package part2.part2_3;

/**
 * Задание 2.3 — Сервис базы данных (реализация Loggable)
 *
 * Реализует единственный обязательный метод getComponentName().
 * Все default-методы (log, logError) доступны автоматически.
 */
public class DatabaseService implements Loggable {

    /**
     * Подсказка: return "DatabaseService";
     */
    @Override
    public String getComponentName() {
        return "DatabaseService";
    }

    /**
     * Подключается к базе данных и логирует процесс.
     *
     * Алгоритм:
     *   1. log("Подключение к " + url);
     *   2. log("Подключение установлено");
     */
    public void connect(String url) {
        log("Подключение к " + url);
        log("Подключение установлено");
    }
}
