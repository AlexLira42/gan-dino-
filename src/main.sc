require: localpatterns.sc
require: themes/service.sc
require: themes/phone.sc
require: themes/discount.sc
require: themes/city.sc

require: dicts/discount.yaml
    var = discountInfo
    
require: city/cities-ru.csv
    module = sys.zb-common
    name = Cities
    var = $Cities

init:
    
    $global.$converters = {};
    
    $global.$converters.CityConverter = function($parseTree) {
        return $Cities[$parseTree.Cities[0].value].value;
    };
    
    bind ("postProcess", function ($context) {
        $context.session.lastState = $context.currentState;
    });
    
    # bind("onAnyError", function($context) {
    #     var answers = [
    #         "Что то пошло не так",
    #         "Произошла ошибка, Пожалуйста приходите завтра",
    #         "Все сломалось. Гномы уже пошли чинить"
    #     ];
    #     var randomAnswer =  answers[$reactions.random(answers.length)];
    #     $reactions.answer(randomAnswer);
    # });

theme: /
    
    state: Welcome
        q!: *start
        q!: $hi
        random:
            a: Привет
            a: Здравствуй
        a: Меня зовут {{ capitalize ($injector.botName) }}
        
        script:
            $response.replies = $response.replies || [];
            $response.replies.push({ 
                type: "image",
                imageUrl: "https://proprikol.ru/wp-content/uploads/2020/12/samolety-krasivye-kartinki-12.jpg",
                text: "Самолет",
            });
        go!: /Service/SuggestHelp
    
    state: NoMatch || noContext = true
        event!: noMatch
        a: Простите не понял вас
        go!: {{ $session.lastState }}
