Проект Appium автотестов с Cucumber фреймворком, для Android устройств.

Требования для запуска

Предварительно должны быть установлены:

Java 1.8

Maven 3.6 и выше

Appium 1.13.0 и выше

Android SDK последней версии

Для всего должны быть прописаны системные переменные, в том числе для adb (Android Debug Bridge)

Должен быть запущен Appium сервер и работать на своём стандартном порту 0.0.0.0:4723

К компьютеру через USB должно быть подключено, android устройство с версией операционной системы 6.0 и выше.

На устройстве должны быть получены права разработчика, должна быть включена отладка по USB,

на телефоне необходимо дать разрешение компьютеру на подключение к этому устройству

Блокировка экрана должна быть отключена, экран телефона должен находиться во включенном состоянии.

Команда adb devices в командной строке должна возвращать список устройств которые подключены к компьютеру в формате:

PS C:\Users\user> adb devices

List of devices attached

b7a6dex1 device

Должен отображаться udid подключённого устройства (b7f6dec1) и слово device.

(Если вместо слова device отображается слово 'unauthorized', то устройство подключено не верно, нужно отключить отладку по USB,

подключить устройство заново и дать разрешение компьютеру на подключение к устройству)

7)Компьютер к которому подключено устройство должен иметь хороший выход в интернет, так как maven при старте тестов будет

скачивать все необходимые зависимости в локальный репозиторий.

Запуск тестов:

Чтобы запустить тесты необходимо скачать проект с github, зайти в командную строку, в папку где находится файл POM.xml

И вызвать команду :

mvn clean test -Dudid=b7a6dex1 allure:serve

где b7a6dex1 это идентификатор устройства полученный ранее командой adb devices

Далее для возможности выполнения тестов, Appium в автоматическом режиме должен будет установить 3 небольших приложения на телефон:

Appium Settings

io.appium.uiautomator2.server

io.appium.uiautomator2.server.test

При установке каждого из них нужно дать разрешение на установку, и нажать на телефоне кнопку "Установить".

Также если это потребуется для тестов, нужно для всех этих приложений, дать права на доступ к телефону.

(Доступ к памяти, доступ на включение выключение WiFi итд, больше всего прав нужно для Appium Settings).

Их достаточно установить один раз, в дальнейшем можно добавить capabilities:

capabilities.setCapability("skipDeviceInitialization", true);

capabilities.setCapability("skipServerInstallation", true);

Что позволит игнорировать их установку каждый раз. Будут использоваться те приложения которые уже установлены на телефон.

После этого на телефоне запустятся и выполнятся тесты, потом автоматически поднимется локальный web-server с отчётом о выполнении тестов (Allure) .

К каждому шагу тестов будет прикреплён скриншот этого шага, с экрана мобильного устройства.

Также если тест завершится с ошибкой, будет добавлен скриншот экрана на котором произошла ошибка.

По умолчанию видеозапись выполнения тестов отключена, так как она поддерживается не на всех устройствах, чтобы её включить необходимо в команду запуска добавить строку:

-Dvideo=true

Полная команда:

mvn clean test -Dudid=b7a6dex1 -Dvideo=true allure:serve

Тогда после выполнения тестов к каждому тесту будет прикреплена видеозапись выполнения теста.

По умолчанию запустятся все тесты проекта, так как в RunnerTest указан тег @all 

Если необходимо запускать какие-то отдельные группы тестов по тегу то необходимо передать в строку запуска параметр:
 
 -Dcucumber.options="--tags @pass" 
 
 Общая команда:
 
 mvn clean test -Dudid=b7a6dex1 -Dvideo=true -Dcucumber.options="--tags @pass" allure:serve