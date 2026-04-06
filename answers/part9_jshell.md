# Часть 9 — Эксперименты в jshell

## Как запустить jshell

Откройте терминал IntelliJ (View → Tool Windows → Terminal) и введите:
```
jshell
```
Для выхода: `/exit`

---

## Задание 9.1: Sealed-классы

### Команды (скопируйте и вставьте в jshell)

```
sealed interface Shape permits Circle, Square {}
record Circle(double r) implements Shape {}
record Square(double side) implements Shape {}
Shape s = new Circle(5)
s instanceof Circle c ? "Круг r=" + c.r() : "Не круг"
```

### Фактический вывод:

```
jshell> sealed interface Shape permits Circle, Square {}
|  created interface Shape

jshell> record Circle(double r) implements Shape {}
|  created record Circle

jshell> record Square(double side) implements Shape {}
|  created record Square

jshell> Shape s = new Circle(5)
s ==> Circle[r=5.0]

jshell> s instanceof Circle c ? "Круг r=" + c.r() : "Не круг"
$5 ==> "Круг r=5.0"
```

### Вопрос: Что произойдёт при попытке создать `record Triangle(double a) implements Shape {}`?

**Ваш ответ:**
Если попытаться создать `record Triangle(double a) implements Shape {}`, компилятор (и JShell) выдаст ошибку: 
class is not allowed to extend sealed class: Shape (не разрешено наследоваться от запечатанного класса).
Интерфейс `Shape` объявлен как sealed, и в секции permits жестко прописано, что реализовывать его 
могут только `Circle` и `Square`. Класса `Triangle` в этом списке нет, поэтому наследование запрещено 
на этапе компиляции.


---

## Задание 9.2: Цепочка лямбд

### Команды

```
import java.util.function.*
Function<String, String> trim = String::trim
Function<String, String> upper = String::toUpperCase
Function<String, String> exclaim = s -> s + "!"
var pipeline1 = trim.andThen(upper).andThen(exclaim)
var pipeline2 = exclaim.compose(upper).compose(trim)
pipeline1.apply("  hello world  ")
pipeline2.apply("  hello world  ")
```

### Фактический вывод:

```
jshell> import java.util.function.*

jshell> Function<String, String> trim = String::trim
trim ==> $Lambda$...

jshell> Function<String, String> upper = String::toUpperCase
upper ==> $Lambda$...

jshell> Function<String, String> exclaim = s -> s + "!"
exclaim ==> $Lambda$...

jshell> var pipeline1 = trim.andThen(upper).andThen(exclaim)
pipeline1 ==> java.util.function.Function$$Lambda$...

jshell> var pipeline2 = exclaim.compose(upper).compose(trim)
pipeline2 ==> java.util.function.Function$$Lambda$...

jshell> pipeline1.apply("  hello world  ")
$7 ==> "HELLO WORLD!"

jshell> pipeline2.apply("  hello world  ")
$8 ==> "HELLO WORLD!"
```

### Вопрос: Дают ли `andThen()` и `compose()` одинаковый результат? В каком случае результаты будут различаться?

**Ваш ответ:**
В данном конкретном примере они дают одинаковый результат, так как порядок вызова функций в коде специально отзеркален.
- `trim.andThen(upper)` означает: сначала выполни `trim`, затем `upper`. Порядок: слева направо.
- `exclaim.compose(upper)` означает: сначала выполни `upper`, а его результат передай в `exclaim` (композиция функций). Порядок: справа налево (изнутри наружу).
  Обе цепочки из примера в итоге выполняются в строгом порядке: `trim` -> `toUpperCase` -> `exclaim`.

Результаты будут различаться, если мы запишем функции в одном и том же порядке для обоих методов.
Например: `f1.andThen(f2)` выполнит сначала `f1`, потом `f2`.
А `f1.compose(f2)` выполнит сначала `f2`, а потом `f1`.
Если порядок важен (как в математике: умножить на 2, потом прибавить 3 — это не то же самое, что прибавить 3, а потом умножить на 2), то результаты `andThen` и `compose` с одними и теми же аргументами будут абсолютно разными.



---

## Задание 9.3: Сравнение EnumSet и HashSet

### Команды

```
enum Color { RED, GREEN, BLUE, YELLOW, CYAN, MAGENTA, WHITE, BLACK }
var enumSet = java.util.EnumSet.of(Color.RED, Color.GREEN, Color.BLUE)
var hashSet = new java.util.HashSet<>(java.util.Set.of(Color.RED, Color.GREEN, Color.BLUE))
enumSet.contains(Color.RED)
hashSet.contains(Color.RED)
enumSet.getClass().getSimpleName()
hashSet.getClass().getSimpleName()
```

### Фактический вывод:

```
jshell> enum Color { RED, GREEN, BLUE, YELLOW, CYAN, MAGENTA, WHITE, BLACK }
|  created enum Color

jshell> var enumSet = java.util.EnumSet.of(Color.RED, Color.GREEN, Color.BLUE)
enumSet ==> [RED, GREEN, BLUE]

jshell> var hashSet = new java.util.HashSet<>(java.util.Set.of(Color.RED, Color.GREEN, Color.BLUE))
hashSet ==> [RED, BLUE, GREEN]

jshell> enumSet.contains(Color.RED)
$4 ==> true

jshell> hashSet.contains(Color.RED)
$5 ==> true

jshell> enumSet.getClass().getSimpleName()
$6 ==> "RegularEnumSet"

jshell> hashSet.getClass().getSimpleName()
$7 ==> "HashSet"
```

### Вопрос: Почему внутренний класс EnumSet называется `RegularEnumSet`? Что произойдёт, если enum будет иметь больше 64 констант?

**Ваш ответ:**
Класс называется `RegularEnumSet`, потому что `EnumSet` — это абстрактный класс, который для максимальной производительности выбирает реализацию под капотом в зависимости от размера перечисления (enum).
В `RegularEnumSet` для хранения элементов множества используется всего одна переменная примитивного типа `long` (которая состоит из 64 бит). Каждый бит работает как флаг (0 или 1) и обозначает присутствие конкретной константы в множестве. Так как в `Color` 8 констант, они легко умещаются в 64 бита.

Что произойдёт, если enum будет иметь больше 64 констант? 
Одного `long` (64 бита) уже не хватит для хранения всех флагов. Фабричный метод `EnumSet` определит это на этапе создания и вместо `RegularEnumSet` вернёт экземпляр другого внутреннего скрытого класса — `JumboEnumSet`.
В `JumboEnumSet` под капотом используется уже массив `long[]`, что позволяет хранить перечисления любого размера больше 64 констант. При этом для разработчика интерфейс использования никак не изменится.

