# Практическое занятие 2: Основные конструкции языка Java

**Курс:** Современные технологии программирования  
**Условие задания:** [aliebraheem-fun.github.io/Modern-Programming-Technologies/#/practice2](https://aliebraheem-fun.github.io/Modern-Programming-Technologies/#/practice2)  
**Исходный репозиторий:** [github.com/Mohanad0101/practice2](https://github.com/Mohanad0101/practice2)

---

## Требования (что установить перед началом)

| Программа | Версия | Ссылка для скачивания |
|-----------|--------|-----------------------|
| JDK | 21 или новее | [oracle.com/java/technologies/downloads](https://www.oracle.com/java/technologies/downloads/) |
| Git | любая актуальная | [git-scm.com/downloads](https://git-scm.com/downloads) |
| IntelliJ IDEA | Community Edition | [jetbrains.com/idea/download](https://www.jetbrains.com/idea/download/) |
| Аккаунт GitHub | — | [github.com](https://github.com) |

---

## Шаг 1. Форк репозитория

1. Откройте в браузере: **https://github.com/Mohanad0101/practice2**
2. В правом верхнем углу нажмите кнопку **Fork**.
3. Убедитесь, что в качестве владельца выбран ваш аккаунт.
4. Нажмите **Create fork**.
5. Дождитесь создания — вы окажетесь на странице `https://github.com/ВАШ_ЛОГИН/practice2`. В заголовке должно быть написано *forked from Mohanad0101/practice2*.

---

## Шаг 2. Настройка SSH-ключа (один раз на компьютер)

SSH позволяет IntelliJ IDEA отправлять ваш код на GitHub без ввода пароля при каждом push.

### 2.1. Откройте терминал

- **Windows:** откройте **Git Bash** (устанавливается вместе с Git).
- **macOS / Linux:** откройте **Терминал**.

### 2.2. Проверьте, есть ли уже ключ

```bash
ls -al ~/.ssh
```

Если вы видите файл `id_ed25519.pub` или `id_rsa.pub` — ключ уже существует. Переходите к шагу 2.4.

### 2.3. Создайте новый ключ

```bash
ssh-keygen -t ed25519 -C "ваш_email@example.com"
```

Нажмите **Enter** три раза (путь по умолчанию, пароль не нужен).

### 2.4. Скопируйте публичный ключ

```bash
# Git Bash:
cat ~/.ssh/id_ed25519.pub        # скопируйте весь вывод

```

### 2.5. Добавьте ключ в GitHub

1. Откройте: **https://github.com/settings/keys**
2. Нажмите **New SSH key**.
3. **Title:** введите имя компьютера (например, «Ноутбук университет»).
4. **Key type:** Authentication Key.
5. В поле **Key** вставьте скопированный ключ.
6. Нажмите **Add SSH key**.

### 2.6. Проверьте соединение

```bash
ssh -T git@github.com
```

Ожидаемый ответ: `Hi ВАШ_ЛОГИН! You've successfully authenticated, but GitHub does not provide shell access.`

---

## Шаг 3. Подключение GitHub-аккаунта в IntelliJ IDEA

### 3.1. Проверьте плагины

```
File → Settings → Plugins → вкладка Installed
```

Найдите **Git** и **GitHub** — оба должны быть активны (с галочкой).

### 3.2. Если плагины не установлены

```
File → Settings → Plugins → вкладка Marketplace
Введите «GitHub» → Install → перезапустите IntelliJ.
```

### 3.3. Добавьте аккаунт GitHub

```
File → Settings → Version Control → GitHub → кнопка «+»
→ «Log In via GitHub...»
→ Откроется браузер — войдите в свой аккаунт и разрешите доступ.
→ Вернитесь в IntelliJ — аккаунт появится в списке.
→ OK
```

---

## Шаг 4. Клонирование форка в IntelliJ IDEA

1. На стартовом экране IntelliJ нажмите **Get from VCS**.  
   *(Если проект уже открыт: File → New → Project from Version Control)*
2. В поле **URL** введите SSH-адрес вашего форка:
   ```
   git@github.com:ВАШ_ЛОГИН/practice2.git
   ```
   *Где взять: страница форка в браузере → кнопка **Code** → вкладка **SSH** → скопируйте адрес.*
3. В поле **Directory** выберите удобную папку на компьютере.
4. Нажмите **Clone**.
5. IntelliJ спросит «Trust and Open Project?» → нажмите **Trust Project**.

Проект автоматически откроется с настройками Java 21 (они сохранены в файле `practice2.iml`).

### Если JDK 21 не найден автоматически

```
File → Project Structure → Project
→ SDK: нажмите раскрывающийся список → Add SDK → JDK...
→ Укажите путь к установленному JDK 21.
→ Language level: выберите «21».
→ OK
```

---

## Шаг 5. Выполнение заданий

### Рабочий цикл для каждого задания

```
1. ОТКРОЙТЕ файл задания (левая панель Project → найдите .java-файл).

2. ПРОЧИТАЙТЕ заголовочный Javadoc — там теория и ожидаемый вывод.
   Не пропускайте — это ключ к правильной реализации.

3. РЕАЛИЗУЙТЕ методы, следуя подсказкам в Javadoc каждого метода.
   Ищите маркеры: // ▼ ВАШ КОД ЗДЕСЬ ▼ ... // ▲ КОНЕЦ ВАШЕГО КОДА ▲
   Замените placeholder (return 0, return "", return null) на правильный код.

4. ЗАПУСТИТЕ: нажмите ▶ рядом с методом main
   (или правая кнопка мыши → Run '<ИмяКласса>.main()').

5. СРАВНИТЕ вывод в панели «Run» с ожидаемым выводом в Javadoc.
   Если не совпадает — вернитесь к шагу 3.

6. ЗАПОЛНИТЕ ответы (если задание требует письменных ответов)
   в соответствующем файле answers/*.md

7. ЗАФИКСИРУЙТЕ прогресс (см. Шаг 6).
```

## Шаг 6. Сохранение прогресса (после каждого задания)

### Через IntelliJ IDEA (рекомендуется)

1. Нажмите **Ctrl+K** (Windows/Linux) или **Cmd+K** (macOS).
2. Отметьте галочкой изменённые файлы.
3. Напишите сообщение коммита, например:
   ```
   Задание 1.1: реализован класс BankAccount
   ```
4. Нажмите **Commit and Push...** (не просто «Commit»).
5. В диалоге Push нажмите **Push**.

### Проверка

Откройте `https://github.com/ВАШ_ЛОГИН/practice2` — новый коммит должен быть виден.


---

## Что сдавать

- Все файлы `.java` в папках `part1/`–`part8/` (включая подпапки `partN/partN_M/`) и `part1.part1_2.company/` — с реализованными методами
- `answers/task1_2_access_table.md` — заполненная таблица модификаторов
- `answers/task2_4_abstract_vs_interface.md` — заполненная таблица сравнения
- `answers/part9_jshell.md` — результаты экспериментов в jshell
- `answers/part10_questions.md` — ответы на контрольные вопросы
- Все изменения запушены в ваш форк на GitHub

**Преподаватель проверяет работу по адресу:** `https://github.com/ВАШ_ЛОГИН/practice2`


---

## Структура репозитория

```
practice2/
├── README.md
├── .gitignore
├── practice2.iml
├── docs/
│   ├── ЗАПУСК_КЛАССОВ.md           ← полные имена классов для Run
│   ├── ПРОВЕРКА_ПРЕПОДАВАТЕЛЮ.md  ← javac, upstream diff
│   └── slides/                   ← веб-презентация (ООП + идеи практики)
│       ├── index.html
│       └── README.md
├── .idea/
├── answers/
│   ├── task1_2_access_table.md
│   ├── task2_4_abstract_vs_interface.md
│   ├── part9_jshell.md
│   └── part10_questions.md
├── part1.part1_2.company/                    ← задание 1.2 (part1.part1_2.company.core / part1.part1_2.company.app)
├── part1/
│   ├── part1_1/                ← пакет part1.part1_1 — задание 1.1
│   └── part1_3/                ← пакет part1.part1_3 — задание 1.3
├── part2/
│   ├── part2_1/                ← пакет part2.part2_1 — задание 2.1
│   ├── part2_2/                ← пакет part2.part2_2 — задание 2.2
│   └── part2_3/                ← пакет part2.part2_3 — задание 2.3
├── part3/
│   ├── part3_1/                ← задание 3.1
│   └── part3_2/                ← задание 3.2
├── part4/
│   ├── part4_1/                ← задание 4.1
│   └── part4_2/                ← задание 4.2
├── part5/
│   ├── part5_1/                ← задание 5.1
│   └── part5_2/                ← задание 5.2
├── part6/
│   └── part6_1/                ← задание 6.1
├── part7/
│   ├── part7_1/                ← задание 7.1
│   ├── part7_2/                ← задание 7.2
│   └── part7_3/                ← задание 7.3
└── part8/
    └── part8_1/                ← задание 8.1
```
