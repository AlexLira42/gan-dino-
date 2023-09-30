theme: /Phone
    state: Ask || modal = true 
        a: Напишите номер телефона в формате +7900000000.
        buttons: 
            "Отменить"
            
        state: Get
            q: $phone
            go!: /Phone/Confirm
            
        state: Wrong
            q: *
            a: формат телефона не похож на верный, напишите в формате +7900000000.
            go!: /Phone/Ask
            
    state: Confirm
        script:
            $temp.phone = $parseTree._phone || $client.phone;
        a: Проверьте, пожалуйста, корректность вашего номера - {{ $temp.phone }}.
        script:
            $session.probablyPhone = $temp.phone;
        buttons: 
            " Верный "
            " Нет"
    state: Yes
        q: (да/верн*)
        script:
            $client.phone = $session.probablyPhone;
            delete $session.probablyPhone;
        a: Хорошо
        go!: /Discount/Inform
        
    state: No
        q: (нет/ не [верно])
        go!: /Phone/Ask