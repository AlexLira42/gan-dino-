theme: /Discount
    state: Inform
        script:
            # var nowDate = $jsapi.dateForZone("Europe/Moscow", "dd.MM")
            # var answerText = "Сегодня (" + nowDate + ") у нас действует крутая скидка!";
            # var discount = "Купите билеты сегодня, чтобы получить скидку в размере 10%!";
           
            
            var nowDayofWeek =  $jsapi.dateForZone("Europe/Moscow", "EEEE")
            var discount = discountInfo[nowDayofWeek];
            
            if (discount) {
                var nowDate = $jsapi.dateForZone("Europe/Moscow", "dd.MM")
                var answerText = "Сегодня (" + nowDate + ") у нас действует крутая скидка!";
                $reactions.answer(answerText);
                $reactions.answer(discount);
            }
            
        go!: /City/Departure