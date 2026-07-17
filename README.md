# TestRail Automation

> Дипломная работа — автоматизация тестирования TestRail на Java

Автоматизированные UI и API тесты для [TestRail](https://www.testrail.com),
написанные на Java с использованием Selenide, REST Assured и TestNG.

---

## Стек технологий

| Инструмент            | Версия | Назначение |
|-----------------------|--|---|
| Java                  | 17 | Язык разработки |
| Selenide              | 7.16.2 | UI-тестирование |
| TestNG                | 7.12.0 | Тест-фреймворк |
| Allure                | 2.24.0 | Отчётность |
| Log4j2                | 2.26.0 | Логирование |
| Lombok                | 1.18.46 | Кодогенерация |
| Owner                 | 1.0.12 | Конфигурация |
| JavaFaker             | 1.0.2 | Генерация тестовых данных |
| REST Assured          | 6.0.0 | API-тестирование |
| Gson                  | 2.14.0 | API-тестирование |
| Json Schema Validator | 6.0.0 | API-тестирование |

---

## Структура проекта

```
src/
├── main/java/
│   └── api/             # Архитектура для API тестов
│        ├── adapters/   # Адаптеры с созданием запросов
│        └── models/     # Модели с соответствующими модулями для API-тестов каждого типа
│   ├── dict/            # Константы и тексты (Elements)
│   ├── dto/             # Модели данных + фабрики (Project, TestCase)
│   ├── pages/           # Page Object Model
│   └── steps/           # Step-объекты для Allure (Login, Project)
└── test/java/
    ├── config/          # TestConfig, SelenideConfig
    ├── listeners/       # TestListener (логи + retry), RetryAnalyzer
    └── tests/
        ├── ui/          # UI-тесты
        └── api/         # API-тесты
```

> TestNG-listener подключён через ServiceLoader:
> `src/test/resources/META-INF/services/org.testng.ITestNGListener`.

---

## Покрытие тестами

### Роман

#### UI-тесты

| Раздел | Тесты | Статус |
|---|-------|---|
| Аутентификация | 5/5   | ✅ |
| Управление проектами | 3/5   | ✅ *(пагинация/фильтр — нет функционала)* |
| Тест-кейсы | 7/8   | ✅ *(копирование исключено)* |
| Test Suites | 4/4   | ✅ |
| Test Runs | 5/5   | ✅ |
| Отчёты | 3/3   | ✅ *(экспорт — Excel; сравнение ранов — отчёт Comparison for Cases)* |

#### API-тесты (запланированы)

| Раздел | Тесты | Статус |
|---|-------|---|
| Аутентификация | 2     | ⏳ |
| Проекты | 2     | ⏳ |
| Тест-кейсы | 3     | ⏳ |
| Test Runs | 2     | ⏳ |
| Результаты | 1     | ⏳ |
###

### Павел

#### UI-тесты

| Раздел | Тесты | Статус |
|---|-------|---|
| Milestones | 8/8   | ✅ |
| User Management | 3/3   | ✅ |
| Group Management | 3/3   | ✅ |
| Role Management | 3/3   | ✅ |
| Settings | 3/3   | ✅ |

#### API-тесты

| Раздел | Тесты | Статус |
|---|-------|--|
| Section Management | 4     | ✅ |
| Attachments | 6     | ✅ |

---

## Быстрый старт

### 1. Настройка конфигурации

Скопировать шаблон и заполнить данные:

```bash
cp src/test/resources/config.properties.example \
   src/test/resources/config.properties
```

```properties
baseUrl=https://tms34.testrail.io
email=your@email.com
password=yourpassword
```

### 2. Запуск тестов

```bash
# Все тесты
mvn clean test

# Только UI тесты
mvn clean test -Dgroups=ui

# Только smoke (ключевой happy-path каждого раздела)
mvn clean test -Dgroups=smoke

# Headless режим (CI/CD)
mvn clean test -Dheadless=true
```

> Группы: `ui` — все UI-тесты, `smoke` — ключевые сценарии
> (`loginTest`, `createProjectTest`, `testCaseCreationTest`, `createSuiteTest`, `createRunTest`).

### 3. Allure отчёт

```bash
# Сгенерировать и открыть в браузере
mvn allure:serve

# Только сгенерировать
mvn allure:report
```

---

## Паттерны

- **Page Object Model** — каждая страница в отдельном классе
- **Fluent interface** — цепочки вызовов методов
- **Builder Pattern** — построение тестовых данных
- **Step Pattern** — `@Step` аннотации для Allure-отчётов
- **DataProvider** — параметризация негативных сценариев
- **JSON serialization and deserialization** - обработка Json-объектов для автоматизации API
- **TestNG Listener** — `TestListener` (`ITestListener`): логирование жизненного цикла тестов + скриншот при падении
- **Retry** — `RetryAnalyzer` (`IRetryAnalyzer`) + `IAnnotationTransformer`: авто-перезапуск упавших тестов (до 2 повторов), навешивается на все `@Test` без правки тестов
- **Allure-метаданные** — `@Owner`, `@Feature`, `@Severity`, `@Description` на UI-тестах
