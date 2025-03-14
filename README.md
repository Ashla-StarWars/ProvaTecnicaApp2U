L'objectiu del projecte és desenvolupar una aplicació mòbil des de zero en una de les plataformes
(Android / iOS) a partir dels punts que es detallen a continuació:

0 – Consideracions prèvies
- L'app ha de ser per a mòbil (no cal tablet). El layout s'ha d'adaptar a les diferents mides i només
ha de ser vertical.
- El llenguatge de programació ha de ser Kotlin/Java (Android) o Swift/Objective-C (iOS).
- Es valoraran bones pràctiques de programació: comentaris, no existència de warnings, etc...
- Es poden fer servir llibreries externes.
- 
1 – Obtenció de dades
El primer punt és obtenir les dades que s'han de mostrar a la pantalla, per fer-ho és necessari cridar el
següent web service:
- GET URL: https://inphototest.app2u.es
- Basic authentication
o usuari: test@gmail.com
o password: ******
o Pots hardcodejar els valors a la petició.
- Altres headers:
o Accept: application/json

2 – Llistat
Un cop obtingudes les dades, el següent pas és mostrar-les per pantalla, per fer-ho et deixem el disseny
que has d'aconseguir muntar i que pots veure a la imatge 1.

3 – Persistència
Una de les coses que acostumem a fer a APP2U és descarregar les dades, guardar-les en local i després
mostrar-les. El que has de fer ara és això, persistir les dades en una base de dades local un cop
descarregades i abans de mostrar-les per pantalla. Es valorarà l’ús de Realm (Android) / Coredata (iOS).

4 – Detall
El següent punt és mostrar el detall d'una de les dades descarregades, el disseny el pots veure a la
imatge 2.

5 – Like offline
Fins ara només hem mostrat dades, ara cal fer una funcionalitat per modificar dades del sistema (tot i
que no afectarà a l’estat del servidor). Cal fer funcionar els botons de Like que hi ha tant al llistat com al
detall. A més, aquesta funcionalitat ha de ser offline. Això vol dir que:
- Si en el moment de fer like hi ha connexió a internet s’envia la petició al servidor (moquejar, no
s’envia realment al servidor, però ho ha de semblar)
- Si en el moment de fer like no hi ha connexió a internet, l’app guarda el like. En el moment que
l’app recupera la connexió l’envia al servidor (moquejar)

Molta sort!
