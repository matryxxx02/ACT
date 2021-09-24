# TPHexapawn 
réalisation du Tp hexapawn 

## Tests

- Test 1	Succès (0.108s)
- Test 2	Succès (0.108s)
- Test 3	Succès (0.18s)
- Test 4	Succès (0.252s)
- Test 5	Succès (0.224s)
- Test 6	Echec TIMED_OUT (Time limit exceeded)
- Test 7	Succès (3.856s)
- Test 8	Succès (0.144s)

## Questions :

* dans le cas où k > 0 OPT = -(max(Successeurs) +1)
* sinon OPT = -(maxNegatif(Successeurs)) +1

## Difficultés :
Nous avons implémenté une HashMap qui a pour clé l'état d'un plateau et en valeur sa valeur. Ainsi, nous n'avons pas besoin de continuer la récursion jusqu'à la fin de la branche. On récupère simplement la valeur. Cependant cette méthode ne nous permet pas de passer le test 6 Echec TIMED_OUT (Time limit exceeded).

## Structure : 
Le code source java de ce tp se trouve dans le repertoire src de ce dossier. 