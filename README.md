# TestRail Automation

> Дипломная работа — автоматизация тестирования TestRail на Java

Автоматизированные UI и API тесты для [TestRail](https://www.testrail.com),
написанные на Java с использованием Selenide, REST Assured и TestNG.

---

## Стек технологий

| Инструмент | Версия | Назначение |
|---|---|---|
| Java | 17 | Язык разработки |
| Selenide | 7.16.2 | UI-тестирование |
| TestNG | 7.12.0 | Тест-фреймворк |
| Allure | 2.24.0 | Отчётность |
| REST Assured | 5.3.1 | API-тестирование |
| Log4j2 | 2.26.0 | Логирование |
| Lombok | 1.18.46 | Кодогенерация |
| Owner | 1.0.12 | Конфигурация |

---

## Структура проекта

```
src/
├── main/java/
│   ├── dict/          # Константы и локаторы
│   ├── pages/         # Page Object Model
│   ├── steps/         # Step-объекты для Allure
│   └── utils/         # Вспомогательные утилиты
└── test/java/
    ├── config/        # TestConfig, SelenideConfig
    └── tests/
        ├── ui/        # UI-тесты (30 проверок)
        └── api/       # API-тесты (10 проверок)
```

---

## Покрытие тестами

### UI-тесты (30)

| Раздел | Тестов | Статус |
|---|---|---|
| Аутентификация | 5 | ✅ |
| Управление проектами | 5 | ✅ |
| Тест-кейсы | 8 | 🔄 |
| Test Suites | 4 | ⏳ |
| Test Runs | 5 | ⏳ |
| Отчёты | 3 | ⏳ |

### API-тесты (10)

| Раздел | Тестов | Статус |
|---|---|---|
| Аутентификация | 2 | ⏳ |
| Проекты | 2 | ⏳ |
| Тест-кейсы | 3 | ⏳ |
| Test Runs | 2 | ⏳ |
| Результаты | 1 | ⏳ |

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

# Только API тесты
mvn clean test -Dgroups=api

# Headless режим (CI/CD)
mvn clean test -Dheadless=true
```

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
- **Fluent API** — цепочки вызовов методов
- **Builder Pattern** — построение тестовых данных
- **Step Pattern** — `@Step` аннотации для Allure-отчётов
- **DataProvider** — параметризация негативных сценариев
