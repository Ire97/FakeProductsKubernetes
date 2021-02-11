# FakeProductsKubernetes

Progetto aggiuntivo creato in sostituzione al microservizio assegnato a al gruppo con ID 9, i quali si sono ritirati senza preavviso in data 05/02/2021.

# Author

1) Irene Baldacchino (1000012344)

# Specifiche

Una prima premessa riguarda la strutturazione generale del microservizio. 
Questo è stato implementato in maniera minimale al fine di generarare il messaggio sul topic ORDERS con chiave ORDER_VALIDATION.
Mediante una decisione comune, abbiamo deciso di utilizzare questo approccio in quanto ciò ha permesso di non bloccare il flusso di informazioni tra un microservizio ed un altro.
Quindi il microservizio si occuperà soltanto di essere un consumatore sul topic ORDERS riguardo i messaggi con chiave ORDER_COMPLETED e successivamente generare il messaggio sempre sul topic ORDER con chiave ORDER_VALIDATION.
Al fine di rendere il tutto più dinamico, è stata aggiunta una variabile d'ambiente chiamata STATUS al fine di poter cambiare in maniera più rapida il suo valore, così da definire diversi scenari d'uso per gli altri microservizi.
