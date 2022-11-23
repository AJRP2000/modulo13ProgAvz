Para correr la aplicacion hace falta correr el archivo "Main" la capa Cliente y el archivo "ServicioBackEndApplication" en el proyecto ServicioBackEnd.

El proyecto Capa Logica de Negocio es un proyecto deprecado, dado que no tiene java swing y por tanto no puede hacer las de servidor.

El unico usuario valido en la aplicacion es el usuario "usuario" con la contrasena "contrasena".

Al ejecutar la aplicacion, a la hora de realizar las compras, se ingresan los IdProducto seguidos por una coma. Ej 
0,1,2 Realizaria una compra con los productos cuyo id sean 0, 1 y 2 respectivamente.

El archivo testTableManager sirve para reiniciar la base de datos al estado 0: 4 productos cargados en el sistema, ninguna compra realizada y el 
usuario con 4000 puntos.