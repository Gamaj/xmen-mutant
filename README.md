# xmen-mutant

Descripcion:
Se desarrollo aplicacion en Spring-boot version 2.3.0.RELEASE Utilizando Java 11 y Maven, se utilizo una base de datos H2, y se cargo el servicio en AWS Elastic Beanstalk.

Si se desea ejecutar de manera local la aplicacion corre por el puerto 5000 el context path es /rest-service/mutant/

comandos
mvn clean install
mvn spring-boot:run



1. Nivel 2: 
Peticion POST - http://mutanttest-env.eba-bwzw7dsi.us-east-2.elasticbeanstalk.com/rest-service/mutant/
body Json:
Ej:
{
"dna":["ATGCGA","CAGTGC","TTGTCT","AGAATG","CACCTA","TCACTG"]
}

retorna 200 "Mutante" y 403 "No-Mutante"

2. Nivel 3:
Se uso base de datos H2

Para consultar las estaditicas se encuentra el servicio:
GET- http://mutanttest-env.eba-bwzw7dsi.us-east-2.elasticbeanstalk.com/rest-service/mutant/stats

Retorna un 200 con:
{
  "count_mutant_dna": 4,
  "count_human_dna": 2,
  "ratio": 2.0
}

Para borrar la base de datos y dejas estadisticas en 0 se encuentra el servicio

DELETE - http://mutanttest-env.eba-bwzw7dsi.us-east-2.elasticbeanstalk.com/rest-service/mutant/

Retorna 200 
