Documentacion para la Evaluacion de la Aplicación:

0. Requerimientos:
Tener instalado JDK, NODEJS y algún software de gestión de bases de datos por ejemplo Xampp.

JDK:
![Image text](https://github.com/KevVanHawks7/img/blob/main/img0.png)

NODEJS:
![Image text](https://github.com/KevVanHawks7/img/blob/main/img1.png)

Xammp:
![Image text](https://github.com/KevVanHawks7/img/blob/main/img3.png)

Se instalará Angular CLI para poder hacer uso de Angular:

![Image text](https://github.com/KevVanHawks7/img/blob/main/img5.png)


1.	Lenguaje y Versiones utilizadas:
Java versión: 17
Lenguaje: Java
Spring Boot: 2.7.0

2.	Como ejecutar el programa:
Para la ejecucion de este programa clonamos el repositorio del proyecto o descargamos el repositorio en formato ZIP.
Luego hacemos uso de algun IDE como lo es intelliJ IDEA Ultimate o Eclipse para abrir el proyecto.

3.	SQL:
En el archivo application.properties localizado en la carpeta Api/src/main se hizo uso del 'spring.jpa.hibernate.ddl-auto=create' para la creacion de las tablas en la base de datos, sin embargo se puede hacer uso del 'spring.jpa.hibernate.ddl-auto=none' si no queremos crear las tablas porque ya las tenemos creadas.
![Image text](https://github.com/KevVanHawks7/img/blob/main/img6.png)

Posteriormente ejecutamos el archivo 'ApiApplication', para ejercutar la Api Rest desarrollada en Spring Boot.

![Image text](https://github.com/KevVanHawks7/img/blob/main/img4.png)



4.	Ejecución de Angular:
Para ejecutar Angular se hace uso del comando 'ng serve', por lo cual procedemos abrir una terminal y colocamos el comando para ejecutarlo.
![Image text](https://github.com/KevVanHawks7/img/blob/main/img7.png)


5. Una vez se esté ejecutando Angular y Spring Boot, ingresamos a nuestro navegador de preferencia y abrimos 'http://localhost:4200/', el cual nos mostrará la ejecución de la aplicación.
![Image text](https://github.com/KevVanHawks7/img/blob/main/Aplicacion.png)