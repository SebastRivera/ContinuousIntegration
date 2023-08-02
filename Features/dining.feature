#Author: your.email@your.domain.com
#Keywords Summary :
#Feature: List of scenarios.
#Scenario: Business rule through list of steps with arguments.
#Given: Some precondition step
#When: Some key actions
#Then: To observe outcomes or validation
#And,But: To enumerate more Given,When,Then steps
#Scenario Outline: List of steps for data-driven as an Examples and <placeholder>
#Examples: Container for s table
#Background: List of steps run before each of the scenarios
#""" (Doc Strings)
#| (Data Tables)
#@ (Tags/Labels):To group Scenarios
#<> (placeholder)
#""
## (Comments)
#Sample Feature Definition Template
Feature: Comprar comida

Scenario: Comprar una comida disponible
  Given el menú contiene la comida "Bolon" con un precio de $5.00 y está disponible
  When el cliente selecciona "Bolon" y elige la cantidad de 3
  Then la compra es exitosa
  And el total a pagar es $15.00

Scenario: Comprar una comida no disponible
  Given el menú contiene la comida "Churrasco" con un precio de $8.00 pero no está disponible
  When el cliente intenta seleccionar "Churrasco"
  Then la compra falla
  And se muestra un mensaje de error que dice "La comida seleccionada no está disponible"

Scenario: Aplicar descuento por cantidad
  Given el menú contiene la comida "Chicken Parm" con un precio de $18.50
  And el cliente ha seleccionado 7 "Chicken Parm"
  When el cliente confirma el pedido
  Then la compra es exitosa
  And se aplica un descuento de 10%
  And el total a pagar es $124.65

Scenario: Aplicar descuento por oferta especial
  Given el menú contiene la comida "Parrillada Premium" con un precio de $30.00
  And el cliente ha seleccionado 3 "Parrillada Premium"
  When el cliente confirma el pedido
  Then la compra es exitosa
  And se aplica un descuento de $10.00
  And el total a pagar es $80.00
