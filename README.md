<h1>Автоматизация тестирования форм авторизации и анкеты</h1>
<h2>Воспроизведение</h2>
<details><summary>Окружение</summary>
    <ul>
        <li>Windows 10 22H2 amd64</li>
        <li>JAVA_HOME - Java 11.0.21</li>
        <li>IntelliJ IDEA Community Edition 2022.2.1</li>
        <li>Gradle 7.4</li>
        <li>TestNG</li>
        <li>Selenium</li>
        <li>Allure</li>
        <li>Google Chrome 126.0.6478.62 (64 бит)</li>
        <li>GIT 2.37.2</li>
    </ul>
</details>

Шаги воспроизведения:
1. Склонировать репозиторий <a href="https://github.com/Nephedov/Protei">https://github.com/Nephedov/Protei</a>.
2. Заменить значение переменной <a href="https://github.com/Nephedov/Protei/blob/044b00d6644fb9e13b195d0ec722160d507af8c1/src/test/java/actions/DriverConfig.java#L13">pathToFile</a>, класса <a href="./src/test/java/actions/DriverConfig.java">DriverConfig.java</a>, на свой абсолютный путь к файлу <a href="./qa-test.html">qa-test.html</a>.
```java
from this
    public String pathToFile = "file:///C:/Users/Home/ProteiTmp/qa-test.html";
```
```java
to this
    public String pathToFile = "file:///yourAbsolutePathTo/qa-test.html";
```
3. Открыть терминал в <a href="./">корневом репозитории</a> проекта. (на одном уровне с <a href="./build.gradle">build.gradle</a>).
4. Запустить тесты, выполнив команду в терминале cmd:
```
gradlew clean test
```
или в терминале IntelliJ IDEA:
```
./gradlew clean test
```
5. Запустить формирование allure отчетов, после прогона тестов, выполнив команду в терминале:
```
allure serve build/allure-results
```

<h2>Решения</h2>

<ul>
    <li><a href="./Plan.md">План тестирования</a>.</li>
    <li><a href="https://docs.google.com/spreadsheets/d/1q8xd6sDxd11XxgBhMsG4IvHMhhLvpTTBOh8AXMCUXPk/edit?gid=0#gid=0">Чек-лист</a>.</li>
    <li><a href="https://github.com/Nephedov/Protei/issues/1">Баг-репорт</a>.</li>
    <li><a href="./build.gradle">build.gradle</a> проекта.</li>
    <li><a href="./src/test/java/tests/AuthPageTest.java">AuthPageTest.java</a> автотесты формы авторизации.</li>
    <li><a href="./src/test/java/tests/InputsPageTest.java">InputsPageTest.java</a> автотесты формы анкеты.</li>
    <li><a href="./allure-results.zip">allure-results</a> архив с отчетом allure.</li>
    <li><a href="./Report.md">Отчет</a> о результатах автотестирования.</li>
</ul>

<h2>Объект тестирования</h2>
Документ <a href="./qa-test.html">qa-test.html</a>.

<h2>Что было сделано</h2>

<ul>
    <li>Составлен <a href="./Plan.md">план тестирования</a>.</li>
    <li>Составлен и реализован <a href="https://docs.google.com/spreadsheets/d/1q8xd6sDxd11XxgBhMsG4IvHMhhLvpTTBOh8AXMCUXPk/edit?gid=0#gid=0">чек-лист</a>.</li>
    <li>Заведен <a href="https://github.com/Nephedov/Protei/issues/1">баг-репорт</a>.</li>
    <li>Настроен <a href="./build.gradle">build.gradle</a> проекта.</li>
    <li>
        <details><summary>Создан репозиторий <a href="./src/test/java/pages/">pages</a> в котором реализованы классы:</summary>
            <ul>
                <li><a href="./src/test/java/pages/AuthPage.java">AuthPage.java</a> описывающий селекторы и методы взаимодействия со страницей авторизации.</li>
                <li><a href="">InputsPage.java</a> описывающий селекторы и методы взаимодействия со страницей анкеты.</li>
            </ul>
        </details>
    </li>
        <details><summary>Создан репозиторий <a href="./src/test/java/data/">data</a> в котором реализован класс:</summary>
            <ul>
                <li><a href="./src/test/java/data/DataGenerator.java">DataGenerator.java</a> описывающий методы получения тестовых данных.</li>
            </ul>
        </details>
    <li>
        <details><summary>Создан репозиторий <a href="./src/test/java/actions/">actions</a> в котором реализованы классы:</summary>
            <ul>
                <li><a href="./src/test/java/actions/DriverConfig.java">DriverConfig.java</a> описывающий метод с конфигурацией ChromeDriver.</li>
                <li><a href="./src/test/java/actions/CustomActions.java">CustomActions.java</a> описывающий вспомогательные методы взаимодействия с WebElement.</li>
            </ul>
        </details>
    </li>
    <li>
        <details><summary>Создан репозиторий <a href="./src/test/java/tests/">tests</a> в котором реализованы классы c автотестами:</summary>
            <ul>
                <li><a href="./src/test/java/tests/AuthPageTest.java">AuthPageTest.java</a> автотесты страницы с формой авторизации.</li>
                <li><a href="./src/test/java/tests/InputsPageTest.java">InputsPageTest.java</a> автотесты страницы с формой анкеты.</li>
            </ul>
        </details>
    </li>
    <li>Получен <a href="./allure-results.zip">allure отчет</a> с результатами прогона автотестов.</li>
    <li>Составлен <a href="./Report.md">отчет</a> о проведении автоматизированного тестирования.</li>
</ul>

<h2>Задание</h2>
Написать автотесты, с использованием Selenium, проверяющие работу формы авторизации и формы с анкетой.
