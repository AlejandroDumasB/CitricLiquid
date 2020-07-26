<!-- 1.0.3-b1 -->
# 99.7% Citric Liquid

Base code for CC3002's *99.7% Citric Juice* Project.

The project consists in creating a (simplified) clone of the game **100% Orange Juice**
developed by [Orange_Juice](http://daidai.moo.jp) and distributed by 
[Fruitbat Factory](https://fruitbatfactory.com).

Al codigo base entregado, se le implementaron las clases de:

IUnit -> AbstractUnit -> Player, Enemy
Enemy -> Wild, Boss

IPanel -> AbstractPanel -> PanelBonus, PanelDrop, PanelHome, PanelNeutral, PanelBoss, PanelEncounter

GameController

TwoCrowdedSpot (esta ultima se encarga de ser el listener del Observer Pattern)
Esta clase se encarga de notificar cuando 2 jugadores se encuentran en un mismo panel.

Cada una con sus respectivos Tests. En estos se decidió utilizar una implementacion de herencia, en donde primero se definian las funciones encargadas de los tests en una interfaz, luego una clase de test abstracta definia el setUp() y se encargaba de testear las caracteristicas comunes de las subclases, y finalmente cada subclase particular se encargaba de testear sus metodos particulares.

Trate de corregir con las indicaciones que se me entregaron para la tarea1, permitiendo que WildUnit y BossUnit compartieran la clase Enemy. 

La Interfaz Grafica CitricLiquidFX se encuentra en el mismo directorio que contenia HelloWorldJavaFX.
La escena de la interfaz muestra un mapa simple, con cuatro jugadores (sus estadisticas en la zona derecha), y los botones de interaccion con el usuario se ubican en la parte superior. Los botones no funcionan en cualquier momento, y para indicar que botones se pueden oprimir se ubica en la ezquina superior derecha un mensaje que va cambiando dependiendo del estado del juego.
Existen 6 tipos de paneles distintos:
Amarillos - Paneles Neutrales
Verde - Paneles Home de los jugadores
Rojo - Paneles de encuentro con enemigos aleatorios (Los paneles con una corona son los Bosses)
Marron - Paneles Drop
Azul - Paneles Bonus
Para un juego mas fluido (y una implementacion mas sencilla), se lanza el dado al iniciar el movimiento de cada jugador, pero cuando se cae sobre una casilla especial, se lanza un dado internamente que activa el efecto de el panel. Lo observado en pantalla es el resultado de esa interaccion, viendose modificadas las estadisticas de los jugadores a la derecha.
la interaccion del usuario se limita entonces a lanzar el dado al inicio de cada turno, escoger por donde avanzar al llegar a una bifurcacion en el mapa, escoger si esquivar o defenderse de un ataque enemigo (tanto de otros jugadores como de personajes no jugables), escoger si combatir contra otro jugador si se cruzan en algun momento, decidir si detenerse cuando pasan por su panel Home y elegir cual será el proximo NormaGoal luego de alcanzar un nuevo nivel de Norma.
