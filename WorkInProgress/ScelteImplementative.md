## Alcune scelte implementative e idee varie
* Un videogioco appartiene a una sola categoria (enum)
* Filtro per categoria
* Ricerca per nome (barra di ricerca)
* Un admin (superuser) che puo' aggiungere i vari gestori attraverso un'interfaccia dedicata

**Attori**:
- admin
- gestore prodotti
- gestore ordini
- gestore recensioni
- cliente

**Videogioco**
- ID
- Nome
- Prezzo
- Quantità
- PEGI (Enum: tre, sette, dodici, sedici, diciotto)
- AnnoProduzione
- Disponibile
- Produttore? (Riferimento a entita' software house?)

**Formato Password**
- Lunghezza minima: 8 caratteri
- Lunghezza massima: 12
- Almeno una maiuscola
- Almeno un numero

**Doxygen**
Usare doxygen per generare docs ed eventualmente effettuare verifiche modello UML.