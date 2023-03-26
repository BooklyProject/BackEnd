# BackEnd

- *Importare* i due Jar di nome *javax.mail* e *activation-1.1.1* all'interno del progetto IntelliJ per poter eseguire il codice.

- Nel caso in cui ci sia bisogno di controllare il Database, in questo progetto viene utilizzato un database condiviso hostato su internet attraverso il servizio
*ElephantSQL*.

La seguente connessione è già impostata all'interno del codice, non è necessario importare alcun file dump.
Inoltre, il servizio *ElephantSQL* consente l'utilizzo di al più 5 connessioni. Può quindi accadere che il programma dia errori del tipo: *too many connections*.
In questo caso, potete contattarci e provvederemo a ristabilire la connessione con il Database.
