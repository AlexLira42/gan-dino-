theme: /Service
    
    state: SuggestHelp
        a: я могу помочь купить билет на самолет
        buttons:
            "Да" 
            "Нет" 
            
        state: Accept
            q: * (да/дава*/хорошо) *
            a: Отлично
            if: $client.phone
                go!: /Phone/Confirm
            else:
                go!: /Phone/Ask
        
        state:Reject
            q: *(нет/не*)*
            a: Больше я ничего не умею больше
            go!: /Service/SuggestHelp