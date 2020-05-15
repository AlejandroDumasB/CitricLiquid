<!-- 1.0.3-b1 -->
# 99.7% Citric Liquid

Base code for CC3002's *99.7% Citric Juice* Project.

The project consists in creating a (simplified) clone of the game **100% Orange Juice**
developed by [Orange_Juice](http://daidai.moo.jp) and distributed by 
[Fruitbat Factory](https://fruitbatfactory.com).

Al codigo base entregado, se le implementaron las clases de:
IUnit -> AbstractUnit -> Player, Wild, Boss
IPanel -> AbstractPanel -> PanelBonus, PanelDrop, PanelHome, PanelNeutral

Cada una con sus respectivos Tests. En estos se decidió utilizar una implementacion de herencia, en donde primero se definian las funciones encargadas de los tests en una interfaz, luego una clase de test abstracta definia el setUp() y se encargaba de testear las caracteristicas comunes de las subclases, y finalmente cada subclase particular se encargaba de testear sus metodos particulares.

Para poder ejecutar el programa y comprobar que lo implementado funciona de la forma esperada se deben Runear las clases IPanelTest e IUnitTest, que se encuentran en la seccion de test.
