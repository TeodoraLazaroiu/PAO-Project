# Student Management Systems

## ETAPA 1

**Tema proiectului**: implementarea unui sistem de gestiune al studentilor dintr-o facultate, vizualizarea datelor despre acestia cum ar fi: materiile si notele acestora, grupa si domeniul de studiu, liceul de unde provin si altele. Modelul va cuprinde următoarele clase: 

- **clasa Address**: clasa pentru reprezentarea unei adrese a liceului sau pentru locul nasterii unei persoane
- **clasa Date**: clasa pentru reprezentarea unei date calendaristice pentru data de nastere a unei persoane
- **clasa Person**: clasa din care mosteneste clasa Student
- **clasa Student**: clasa ce pastreaza datele despre studenti
- **clasa HighSchool**: clasa ce cuprinde informatii despre liceul absolvit de student
- **clasa Subject**: clasa pentru reprezentarea materiilor unei student
- **clasa Domain**: clasa pentru clasificarea studentilor din functie de domeniul de studiu
- **clasa Group**: clasa pentru repartizarea studentilor in formatiuni de studiu

![diagrama](diagrama.jpg)

Pe langa acestea programul mai cuprinde si clasele:
- **clasa Service**: cuprinde toate functiile utile pentru realizarea comenzilor din meniu
- **clasa Menu**: implementeaza un meniu interactiv cu desgin pattern de tip singleton
- **clasa Main**: instantiaza un meniu si ruleaza programul principal al aplicatiei

### *Meniul interactiv al aplicatiei:*
```[python]
-----------------------------------
Choose an action.
1: Add new subject
2: Print all subjects
3: Add new domain
4: Print all domains
5: Add new group
6: Print all groups
7: Add new high school
8: Print all high schools
9: Add new student
10: Print all students
11: Sort students by marks
12: Average grade of a student
0: Exit
-----------------------------------
```

## ETAPA 2

Adaugarea clasei **ReadWrite** pentru creare de fisiere CSV corespunzatoare claselor **Subject**, **Domain**, **Group** si **HighSchool** din care se citesc si in care se scriu date. Aceste fisiere sunt incarcate in program la inceputul acestuia iar datele introduse din meniul interactiv vor fi salvate permanent in aceste fisiere. Fisiere CSV au pe prima linie un header ce reprezinta metadate iar pe urmatoarele linii valori corespunzatoare obiectelor create.

### Exemplu fisier pentru clasa **Domain**:

```[python]
name,years
informatica,3
matematica,3
istorie,3
biologie,3
automatica,4
litere,4
```

### Exemplu de afisare pentru clasa **Domain**:

```[python]
-----------------------------------
4
Domain: informatica, Number of years: 3
Domain: matematica, Number of years: 3
Domain: istorie, Number of years: 3
Domain: biologie, Number of years: 3
Domain: automatica, Number of years: 4
Domain: litere, Number of years: 4
-----------------------------------
```
