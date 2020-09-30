<#import "parts/common.ftl" as c>

<@c.page>

<div>
    <h1 align="center"> Conference description ${confDesc.conferenceName}</h1>
</div>

<div class="block">
    <div class="card my-3">
        <#if conference.fileName??>
        <center>
        <img src="/images/${conference.fileName}" width="400" height="400">
        <center/>
    </#if>
    <div class="m-2">
        <center>
            <p>Theme: ${conference.conferenceName}</p>
            <span>Lecture: ${conference.authorName}</span>
        </center>
    </div>
    <div class="card-footer text-muted">
        <center>
            <i>${conference.fullDescription}</i>
            <br/>
        </center>
    </div>
</div>

    <div class="layer1">
        <p>Registered listeners:</p>
        <tbody>
        <#list listeners as listener>
        <tr>
            <td>${listener.username}</td>
            <br/>
        </tr>
        </#list>
        </tbody>
    </div>
    <div>
        Система Управления Конференциями на основе Spring MVC.
        Существует система регистрации и аутентификация, осуществеленной через Spring Security. При регистрации на
        указанную в форме почту, отправляется проверочный код.
        Связь с базой данных осуществляется через Spring data.
        Существуют роли: "администратор" и "обычный Пользователь".
        На странице /main рарегестрированный пользователь может:
        1) зарегистрироваться на уже созданную конференцию,
        2) зайти на страницу где содержится описание выбранной конференции, а так же зарегистрированные на неё пользователи
        3) создать свою конференцию (существует форма ввода даты, названия, краткого описания, полного описания, изображение)
        У администратора есть доступ к странице со всеми пользователями, а так же
        возможность изменить роли и разослать уведомления на электронные почты.
        В качестве шаблонизатора для html страниц используется Freemaker.
    </div>
</@c.page>

