theme: /City
    
    state: Departure
        a: Назовите город вылета
        
        state: Get
            q: * $City *
            script: 
                # log("\n parseTree.City: \n" + toPrettyString($parseTree.City));
                $session.departureCity  = $parseTree._City ;
                
            a: Ваш город отправления: {{ $session.departureCity.name }}.
            go!: /City/Arrival
            
            
    state: Arrival
        a: назовите город прилета
        
        state: Get
            q: * $City *
            script:
                $session.arrivalCity = $parseTree._City;
            a: ваш город прибытия: {{ $session.arrivalCity.name }}
            
    state: LocalNoMatch
            q: * || fromState = /City
            a: простите я вас не понял
            go!: {{ $session.lastState }}