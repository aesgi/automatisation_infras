# Instructions pour déployer l'application

- `sbt package` dans le terminal à la racine du projet<br />
=> build du projet et création d'un ".jar" dans target/scala-2.11/
  

- le renommer en "circulation_groupe20.jar"


- copie du jar sur le edge : <br />
   `scp -i Downloads/iabd circulation_groupe20.jar root@{IP_DU_EDGE}:tmp/`


- connexion au edge : <br />
  `ssh -i Downloads/iabd root@{IP_DU_EDGE}`


- `cd tmp/`


- suppression d'un ancien jar dans hdfs :<br /> 
`hdfs dfs -rm /jars/circulation_groupe20.jar`
  

- ajout de notre nouevau jar :<br />
  `hdfs dfs -put circulation_groupe20.jar /jars`


- lancer le job spark : <br />
  `spark-submit --master yarn --deploy-mode cluster --class Circulation hdfs:///jars/circulation_groupe20.jar`