### Summary

Микросервис представляет собой некий "фронтенд", где пользователь нажимает на UI кнопку "например PUT" и запрос с кнопки отправляется на контроллер этого микросервиса. В Edo-rest хранятся всякие frontend шаблоны. Далее запрос с контроллера передается в Feign controller другого микросервиса под названием "edo-service".

### Основные функции
- Отправка запросов с UI кнопки в Edo-service