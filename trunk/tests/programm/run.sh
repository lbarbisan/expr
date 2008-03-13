
java -jar ../../lib/boitel_barbisan.jar NombrePremier -x86 -o
gcc a.s -o primeNumberExp
javac NombrePremier.java
gcc NombrePremier.c -o primeNumberC
echo Java Execution
time java NombrePremier
echo C Execution
time ./primeNumberC
echo Exp Execution
time ./primeNumberExp


