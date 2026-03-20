package part2.part2_2;

/**
 * Задание 2.2 — Криптокошелёк (реализация PaymentMethod)
 *
 * Формат process: "Криптоплатёж (BTC): 10000.0 руб."
 * Комиссия: 1.5% от суммы.
 */
public record CryptoWallet(String address, String currency) implements PaymentMethod {

    /**
     * "Криптоплатёж (BTC): 10000.0 руб."
     *
     * Подсказка:
     * return "Криптоплатёж (" + currency + "): " + amount + " руб.";
     */
    @Override
    public String process(double amount) {
        return "Криптоплатёж (" + currency + "): " + amount + " руб.";
    }

    /**
     * Комиссия = 1.5% от суммы.
     *
     * Подсказка: return amount * 0.015;
     */
    @Override
    public double fee(double amount) {
        return amount * 0.015;
    }
}
