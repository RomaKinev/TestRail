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
| Gson                  | 2.14.0 | (De)сериализация JSON в API-тестах |

---

## Структура проекта

```
src/
├── main/java/
│   ├── api/
│   │   └── models/       # DTO запросов/ответов API (projects, cases, runs, results, sections, attachments)
│   └── ui/
│       ├── dict/         # Константы и тексты (Elements)
│       ├── dto/          # Модели данных + фабрики (Project, Suite, TestCase, ...)
│       ├── pages/        # Page Object Model
│       └── steps/        # Step-объекты (оркестрация сценариев)
└── test/java/
    ├── api_adapters/     # Адаптеры REST Assured (запросы к API)
    ├── config/           # TestConfig, SelenideConfig
    ├── listeners/        # TestListener (логи + скриншот), RetryAnalyzer
    └── tests/
        ├── ui/           # UI-тесты
        └── api/          # API-тесты
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
| Тест-кейсы | 7/8   | ✅ *(копирование — нет функционала; custom field покрыт)* |
| Test Suites | 4/4   | ✅ |
| Test Runs | 5/5   | ✅ |
| Отчёты | 3/3   | ✅ *(экспорт — Excel; сравнение ранов — отчёт Comparison for Cases)* |

#### API-тесты

| Раздел | Тесты | Статус |
|---|-------|---|
| Аутентификация | 2     | ✅ |
| Проекты | 2     | ✅ |
| Тест-кейсы | 3     | ✅ |
| Test Runs | 2     | ✅ |
| Результаты | 1     | ✅ |

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
baseUrl=https://your_instance.testrail.io
email=your@email.com
password=yourpassword
```

> `config.properties` в git не коммитится (в `.gitignore`).

### 2. Запуск тестов

```bash
# Все тесты
mvn clean test

# Только UI тесты
mvn clean test -Dgroups=ui

# Только API тесты
mvn clean test -Dgroups=api

# Только smoke (ключевой happy-path каждого раздела)
mvn clean test -Dgroups=smoke

# Headless режим (CI/CD)
mvn clean test -Dheadless=true
```

> Группы: `ui` — все UI-тесты, `api` — все API-тесты, `smoke` — ключевые сценарии
> (`loginTest`, `createProjectTest`, `testCaseCreationTest`, `createSuiteTest`, `createRunTest`).

### 3. Allure отчёт

```bash
# Сгенерировать и открыть в браузере
mvn allure:serve

# Только сгенерировать
mvn allure:report
```

---

## CI/CD

GitHub Actions (`.github/workflows/gitHubActions.yaml`):
- запускает API-тесты (`mvn clean test -Dgroups=api`) на push/PR в `master`, по расписанию и вручную;
- публикует Allure-отчёт на GitHub Pages (ветка `gh-pages`) с накоплением истории (Trend).

---

## Паттерны

- **Page Object Model** — каждая страница в отдельном классе
- **Fluent interface** — цепочки вызовов методов
- **Builder Pattern** — построение тестовых данных
- **Step-объекты** — оркестрация сценариев поверх страниц (Login, Project, TestCase, TestSuite, TestRun, Milestone, ...); `@Step` — на методах страниц для Allure
- **API Adapter** — статические адаптеры REST Assured поверх общей спецификации (`BaseAdapter`)
- **Изоляция данных API** — каждый API-класс создаёт свой проект через API и удаляет его в `@AfterClass`
- **DataProvider** — параметризация негативных сценариев
- **JSON (de)serialization** — обработка JSON через Gson в API-тестах
- **TestNG Listener** — `TestListener` (`ITestListener`): логирование жизненного цикла тестов + скриншот при падении
- **Retry** — `RetryAnalyzer` (`IRetryAnalyzer`) + `IAnnotationTransformer`: авто-перезапуск упавших тестов (до 2 повторов), навешивается на все `@Test` без правки тестов
- **Allure-метаданные** — `@Owner`, `@Feature`, `@Severity`, `@Description` на UI-тестах
