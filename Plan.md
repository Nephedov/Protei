<h1>План тестирования</h1>
<div>
    <ol>
        <li>Проведение исследовательского тестирования.</li>
        <li>
            <details><summary>Определение границ и функционала.</summary>
                <ul>
                    <li>Страница авторизации:
                        <ul>
                            <li>Поле ввода "E-Mail".</li>
                            <li>Поле ввода "Пароль".</li>
                            <li>Кнопка "Вход".</li>
                            <li>Всплывающее тревожное сообщение.</li>
                        </ul>
                    </li>
                    <li>Страница ввода данных:
                        <ul>
                            <li>Поле ввода "E-Mail".</li>
                            <li>Поле ввода "Имя".</li>
                            <li>Поле выбора опции "Пол".</li>
                            <li>Чекбокс "Вариант 1.1"</li>
                            <li>Чекбокс "Вариант 1.1"</li>
                            <li> Группа радиокнопок:
                                <ul>
                                    <li>"Вариант 2.1"</li>
                                    <li>"Вариант 2.2"</li>
                                    <li>"Вариант 2.3"</li>
                                </ul>
                            </li>
                            <li>Кнопка "Добавить".</li>
                            <li>Модальное окно "Данные добавлены".</li>
                            <li>Всплывающее тревожное сообщение.</li>
                            <li>Таблица:
                                <ul>
                                    <li>Заголовки столбцов.</li>
                                    <li>Значения столбцов.</li>
                                </ul>
                            </li>
                        </ul>
                    </li>
                </ul>
            </details>
        </li>
        <li>Составление и реализация <a href="https://docs.google.com/spreadsheets/d/1q8xd6sDxd11XxgBhMsG4IvHMhhLvpTTBOh8AXMCUXPk/edit?usp=drive_link">чек-листа</a>.</li>
        <li>Заведение <a href="">баг-репорта</a>.</li>
        <li>
            <details><summary>Настройка окружения для автоматизации.</summary>
                <ul>
                    <li>Установка IDE - IntelliJ IDEA Community Edition 2022.2.1.</li>
                    <li>Установка JDK - openjdk version "11.0.21".</li>
                    <li>Установка GIT - version 2.37.2.windows.2</li>
                    <li>Установка Allure - version 2.23.0.</li>
                    <li>Определение JAVA_HOME на установленную JDK.</li>
                    <li>Создание и настройка <a href="./build.gradle">Gradle проекта</a> с зависимостями:
                        <ul>
                            <li>testng:7.6.1</li>
                            <li>webdrivermanager:5.8.0</li>
                            <li>selenium-java:4.20.0</li>
                            <li>allure-testng:2.19.0</li>
                            <li>javafaker:1.0.2</li>
                        </ul>
                    </li>
                </ul>
            </details>
        </li>
        <li>Составление и реализация <a href="./src/test/java/tests/">автотестов</a>.</li>
        <li>Сбор <a href="./allure-results.zip">отчетов</a> о результатах прогона автотестов.</li>
        <li>Составление <a href="./Report.md">отчёта</a> о проведении автоматизированного тестирования.</li>
    </ol>
</div>