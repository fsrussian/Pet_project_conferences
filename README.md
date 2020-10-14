#  Система Управления Конференциями на основе Spring MVC.    
Данное веб-приложение представляет собой сервис, который предоставляет возможность зарегистрированным пользователям: 
 - Посмотреть предстоящие конференции; 
 - Зарегистрировать свою конференцию (существует форма ввода: название, описание, дата конференции, изображение);
 
 
Система регистрации и аутентификации пользователей осуществляется с помощью Spring Security. При регистрации пользователя
на указанную электронную почту высылается проверочный код. 
Существует роли - обычный пользователь и администратор. 
Для пользователей категории "администратор" есть доступ к странице со всеми пользователями (администраторы могут изменять
роль пользователя в его профиле)

Работа с БД осуществляется с помощью Spring DATA;

В качестве шаблонизатора для html страниц используется Freemaker;

