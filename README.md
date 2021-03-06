# Simulace semaforu

Vstupní branou do programu je třída `CrossroadSimulation`. Funguje jako state machine, která na základě příchozí
události `CrossroadSimulationEvent` změní stav simulace `CrossroadSimulationState`. Stav simulace má aktuální čas, stav
křižovatky, který je vyjádřen třídou `CrossroadState`. Ten si drží informaci o semaforech, které jsou zrovna červené,
list aut, která právě odjíždějí z křižovatky a mapu směru a fronty aut, která právě přijíždí na křižovatku.

Stavy jsou vytvářené generátorem `CrossroadSimulationEventCreator`, jenž obsahuje další generátory pro všechny události,
které mohou v simulaci nastat. Generátor se při vytváření nové události rozhoduje na základě aktuálního stavu simulace. Generování nové události probíhat 
na základě času simulace (Příjezd aut na křižovatku, změna semaforu), nebo na základě stavu křižovatky (žádné auto nečeká, může jet další. Změnil se stav semaforu, může jet jiný směr). 

Genrování událostí není implementováno, ale probíhalo by následovně.`CrossroadSimulationEventCreator`
nejdříve zjistí jaký generátor má nejmenší čas další události funkcí `nextEventAt()`a poté ho použije pro vytvoření nové
události.

Nově vytvořená událost je poté použita pro změnu stavu simulace a takto to běží pořád dokola dokud se nebjeví událost
`SimulationEnd`.
