Чтобы собрать проект через maven необходимо зайти в папку проекта: 
$ cd path_to_projects/FileSplitter
Далее нужно скомпилировать проект:
$ mvn compile
Следующий шаг производит компиляцию кода проекта, запуск тестов,
а в конце упаковывает в JAR-файл в target директории:
$ mvn package

Для запуска выполняем:
$ java -jar path_to_compiled_jar/Parser.jar
