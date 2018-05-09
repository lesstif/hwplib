@ECHO ON

javac -cp target\hwplib-2018.05.04.jar sample\TestEditingHWPFile.java -d target -encoding UTF-8

java -cp target\hwplib-2018.05.04.jar;target TestEditingHWPFile
