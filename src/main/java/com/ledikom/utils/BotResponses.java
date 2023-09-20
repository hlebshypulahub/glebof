package com.ledikom.utils;

import com.ledikom.model.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public final class BotResponses {

    public static String startMessage() {
        return """
                Вас приветствует бот сети аптек *"Ледиком"*!

                Здесь у вас есть уникальная возможность получить максимум полезных и интересных функций, связанных с вашим здоровьем и комфортом. Давайте рассмотрим, что вы уже можете делать и какие потрясающие возможности скоро будут добавлены:

                """

                +
                botDescription()
                +

                """


                А пока что, чтобы отпраздновать ваше присоединение к *"Ледиком"*, активируйте *приветственный купон со скидкой 5%* на любые товары. Это ваш первый шаг к заботе о вашем здоровье с нами.


                _Не забывайте, что весь функционал доступен через меню бота. Добро пожаловать в будущее здоровья и комфорта с ботом "Ледиком"!_ 🌟""";
    }

    public static String botDescription() {
        return """
                *Основные функции:*

                1. 🩺 *Советник по здоровью*: _Наш бот готов помочь вам с вопросами о медицине и здоровье. Получите информацию и советы, не покидая чата._

                2. 🗒️ *Заметки для покупок*: _Создавайте список покупок и сохраняйте его прямо здесь. Удобно и быстро._

                3. 💰 *Купоны на скидки*: _Получайте эксклюзивные купоны (доступны только пользователям бота) для скидок на покупок в наших аптеках._

                4. 👫 *Подарки за приглашение друзей*: _Приглашайте своих друзей воспользоваться нашим ботом с помощью реферальной ссылки (доступна в меню) и получайте бонусы за каждого нового пользователя._

                5. 🎶 *Музыка для сна*: _Создайте идеальную атмосферу для сна с нашей музыкой._
                                
                6. 🏋️ *Утренняя зарядка*: _Начните день с энергии и активности, повторяя движения за тренером в трёх стилях на выбор._
                                
                7. 😴 *Вечерняя расслабляющая гимнастика*: _Быстрый способ расслабиться и снять напряжение перед сном._

                8. 🏥 *Информация о наших аптеках*: _Получите доступ к информации о местоположении и рабочем графике наших аптек._

                9. 🎉 *Акции и новости*: _Будьте в курсе актуальных акций и получайте новости в области здоровья._


                *Скоро добавим:*

                1. ⏰ *Напоминания по лекарствам*: _Настройте удобные напоминания о приеме лекарств._

                2. 🎁 *Карта клиента*: _Участвуйте в программе лояльности и получайте призы за свои покупки._
                """;
    }

    public static String couponAcceptMessage(final Coupon coupon, final boolean inAllPharmacies, final int durationInMinutes) {
        StringBuilder sb = new StringBuilder(coupon.getText() + "\n\n");

        appendPharmacies(sb, coupon.getPharmacies().stream().toList(), inAllPharmacies);
        sb.append("\n\n");

        if (coupon.getStartDate() != null && coupon.getEndDate() != null) {
            sb.append("*С ").append(coupon.getStartDate().format(DateTimeFormatter.ofPattern("dd.MM.yyyy"))).append(" по ").append(coupon.getEndDate().format(DateTimeFormatter.ofPattern("dd.MM.yyyy"))).append("*\n\n");
        }

        sb.append("*Купон действует в течение ").append(durationInMinutes).append(" минут. Активируйте его при кассе.*");

        sb.append("\n\n");

        sb.append("_*Вы можете использовать только один купон на одну покупку_");

        return sb.toString();
    }

    public static String referralMessage(final String refLink, final int referralCount, final Coupon coupon) {
        return "Ваша реферальная ссылка:\n\n\n" + "[" + refLink + "](" + refLink + ")" + "\n\n\n*Количество приглашенных вами пользователей:   "
                + referralCount + "*\n\n\nПоделитесь ссылкой с вашими контактами и получайте бонусы!\n\n"
                + "\uD83E\uDD47 за каждые 10 приглашенных: *" + coupon.getName() + "*";
    }

    public static String couponExpiredMessage() {
        return "Время вашего купона истекло ⌛";
    }

    public static String triggerReceiveNewsMessage(final User user) {
        return "Подписка на рассылку новостей и акций " + (user.getReceiveNews() ? "включена \uD83D\uDD14" : "отключена \uD83D\uDD15");
    }

    public static String listOfCouponsMessage() {
        return "\uD83D\uDCB8 Ваши купоны:";
    }

    public static String noActiveCouponsMessage() {
        return """
                У вас нет купонов \uD83D\uDC40

                Дождитесь новой рассылки акций в наших аптеках, а также приглашайте друзей, используя свою реферальную ссылку ⬇""";
    }

    public static String initialCouponText(final String couponTextWithBarcode, final long couponDurationInMinutes) {
        return "Времени осталось: *" + UtilityHelper.convertIntToTimeInt(couponDurationInMinutes) + ":00*" +
                "\n\n" +
                couponTextWithBarcode;
    }

    public static String updatedCouponText(final UserCouponRecord userCouponRecord, final long timeLeftInSeconds) {
        return "Времени осталось: *" + UtilityHelper.convertIntToTimeInt(timeLeftInSeconds / 60) + ":" + UtilityHelper.convertIntToTimeInt(timeLeftInSeconds % 60) +
                "*\n\n" +
                userCouponRecord.getText();
    }

    public static String noteAdded() {
        return "Заметка сохранена \uD83D\uDD16\n\n_*Чтобы просмотреть или редактировать, воспользуйтесь меню бота_";
    }

    public static String myNote(final String note) {
        return "Ваша заметка:\n\n" +
                "`" + note + "`";
    }

    public static String editNote() {
        return "_*Чтобы редактировать, скопируйте текст заметки выше (нажать на текст), вставьте в поле ввода, измените текст и отправьте сообщение._";
    }

    public static String addNote() {
        return "Чтобы добавить заметку, введите сообщение и отправьте ✏";
    }

    public static String musicMenu() {
        return "Выберите стиль музыки \uD83C\uDFBC";
    }

    public static String musicDurationMenu() {
        return """
                Выберите продолжительность

                _*Музыка остановится автоматически, телефон можно заблокировать и отложить_""";
    }

    public static String goodNight() {
        return "Сеть аптек \"Ледиком\" желает вам добрых снов \uD83D\uDE0C";
    }

    public static String chooseYourCity() {
        return "Выберите ваш город";
    }

    public static String cityAdded(final String cityName) {
        return "Ваш город - *" + City.valueOf(cityName).label + "*";
    }

    public static String newCoupon(final Coupon coupon) {
        return coupon.getNews() + "\n\n" + coupon.getText();
    }

    public static String couponIsNotActive() {
        return "Купон неактивен!";
    }

    public static String yourCityCanUpdate(final City city) {
        return "Ваш город" + (city == null ?
                " не указан.\n\nУкажите его, чтобы получать актуальные новости и акции только для вашего города!"
                :
                " - " + city.label + ".\n\nМожете изменить, выбрав в меню ниже.");
    }

    public static String cityAddedAndNewCouponsGot(final String cityName) {
        return "Ваш город - *" + City.valueOf(cityName).label + "*\n\nПроверьте и воспользуйтесь вашими новыми купонами!";
    }

    public static String promotionAccepted() {
        return "Спасибо, что согласились поучаствовать в нашей акции!\n\n*Поспешите - количество ограниченно!*";
    }

    public static String promotionText(final PromotionFromAdmin promotionFromAdmin, final boolean inAllPharmacies) {
        StringBuilder sb = new StringBuilder(promotionFromAdmin.getText()).append("\n\n");
        appendPharmacies(sb, promotionFromAdmin.getPharmacies(), inAllPharmacies);
        sb.append("\n");
        return sb.toString();
    }

    private static void appendPharmacies(final StringBuilder sb, final List<Pharmacy> pharmacies, final boolean inAllPharmacies) {
        if (inAllPharmacies) {
            sb.append("*Действует во всех аптках сети.*");
        } else {
            sb.append("*Действует в аптках:*\n");
            pharmacies.forEach(pharmacy -> {
                sb.append(pharmacy.getName()).append(" - ").append(pharmacy.getCity().label).append(", ").append(pharmacy.getAddress()).append("\n");
            });
        }
    }

    public static String addSpecialDate() {
        return "\uD83D\uDCC6 Вы можете указать вашу особенную дату, в этот день вы получите подарок от сети аптек \"Ледиком\"!\n\n*Введите и отправьте сообщение в следующем цифровом формате:\n\nдень.месяц*";
    }

    public static String yourSpecialDate(final LocalDateTime specialDate) {
        return "Ваша особенная дата: *" + specialDate.format(DateTimeFormatter.ofPattern("dd.MM")) + "*\n\n" + "\uD83C\uDF81 В этот день вас ждет подарок от сети аптек \"Ледиком\"!";
    }

    public static String specialDay() {
        return """
                ✨ В этот особенный день мы хотим пожелать вам бесконечной удачи, крепкого здоровья и безмерного счастья!

                Воспользуйтесь вашим подарком \uD83C\uDF81

                *Купон на скидку 5% на любые товары в наших аптеках!*
                                
                _*Действует в течение 30 дней_
                """;
    }

    public static String refCoupon(final int referralCount) {
        return "\uD83E\uDEC2 *Вы пригласили уже " + referralCount + " новых пользователей!*\n\nСпасибо, что совершаете покупки у нас и привлекаете к этому ваших друзей!\n\n\uD83C\uDF81 Вы получаете подарок от сети аптек \"Ледиком\"!\n\n"
                + "*Купон на скидку 5% на любые покупке в наших аптеках!*\n\n"
                + "_*Действует в течение 30 дней_";
    }

    public static String responseTimeExceeded() {
        return "Время ожидания на ответ вышло.\n\nВ случае необходимости повторите операцию через меню.";
    }

    public static String consultationWiki() {
        return """
                *Отправьте боту ваш вопрос*
                                
                                
                *Как правильно задавать вопросы*

                1. *Сформулируйте вопрос четко и развернуто:* Постарайтесь выразить свой вопрос ясно и в полном объеме. Можете использовать несколько предложений. Это поможет боту лучше понять ваш запрос и дать более точный ответ.

                2. *Соблюдайте ограничение в 300 символов:* Ваш вопрос должен быть коротким и не превышать 300 символов.

                3. *Соблюдайте тему медицины или здоровья:* Бот специализирован на вопросах по медицине и здоровью, поэтому задавайте вопросы, связанные с этой темой.

                *Примеры хороших вопросов:*
                "Какие симптомы гриппа и каковы методы лечения?"
                "Как принимать Кагоцел взрослому человеку?"
                "Как улучшить сон и бороться с бессонницей?"
                "Что такое антиоксиданты и почему они важны для здоровья?"
                "Как поддерживать здоровое пищеварение и избегать желудочных проблем?"
                "Какое продукты полезны при недостатке железа?"
                "Как справиться с волнением перед выступлением?"

                Соблюдение этих правил поможет вам получить более точные и полезные ответы от бота в рамках темы *медицины и здоровья*.
                                
                _*Бот работает на основе сложных алгоритмов и искусственного интеллекта, и обработка вашего запроса может занять некоторое время. Обычно это занимает до 30 секунд. Бот постарается предоставить наилучший и информативный ответ на ваш вопрос._
                """;
    }

    public static String waitForGptResponse() {
        return "Идёт обработка вашего запроса...\n" +
                "Обычно это занимает *до 30 секунд*";
    }

    public static String workOutMenu() {
        return """
                *Утренняя зарядка* - Выберите вид зарядки
                                
                *Йога*: Проведите короткую йога-сессию, чтобы растянуть мышцы и подготовить тело к новому дню. Включите асаны (позы) для гибкости и медитацию для умиротворения ума.

                *Кардио-зарядка*: Выполните серию кардиоупражнений, таких как бег на месте, подскоки или приседания. Это поможет увеличить уровень энергии и улучшить кровообращение.

                *Стретчинг*: Проведите утренний стретчинг, чтобы расслабить и размять мышцы. Это помогает улучшить гибкость и уменьшить риск напряжения.

                *Пилатес*: Займитесь утренним пилатесом, чтобы укрепить мышцы корпуса и улучшить осанку. Это также способствует лучшему контролю над телом и снятию напряжения.
                                
                _*Бот отправит вам небольшое видео с утренней зарядкой. Пожалуйста, следуйте указаниям тренера и выполняйте упражнения для эффективной зарядки. Желаем вам активного и здорового дня!_""";

    }

    public static String gymnasticsMenu() {
        return """
                *Вечерняя расслабляющая гимнастика* - Выберите вид гимнастики
                                
                *Медитация и дыхательная гимнастика*: Практика медитации и глубокого дыхания поможет устранить стресс, успокоить ум и создать гармонию в вашем вечере.

                *Сонная йога*: Займитесь специальными асанами, которые способствуют расслаблению и улучшению качества сна. Это отличный способ снять напряжение после дня.

                *Постельная гимнастика*: Расположитесь на матрасе и позвольте всему напряжению уйти. Простые упражнения расслабления и дыхания помогут вам снять стресс и подготовиться к спокойному сну.

                *Вечерняя растяжка*: Проведите короткую сессию растяжки, чтобы расслабить напряженные мышцы и готовить тело к спокойному сну. Это поможет улучшить гибкость и укрепить здоровье вашей спины.
                                
                _*Бот отправит вам небольшое видео с гимнастикой. Пожалуйста, следуйте указаниям тренера и выполняйте упражнения для лучшего эфекта. Желаем вам спокойных снов!_""";

    }

    public static String video(final String videoLink) {
        return "[" + videoLink + "](" + videoLink + ")";
    }

    public static String chooseCityToFilterPharmacies() {
        return "Выберите город, чтобы просмотреть информация об аптеках:";
    }

    public static String pharmaciesInfo(final List<Pharmacy> pharmacies) {
        StringBuilder sb = new StringBuilder("Список аптек 🏥");
        sb.append("\n\n");

        pharmacies.forEach(pharmacy -> sb.append(pharmacy.getName()).append("\n")
                .append("Адрес: *").append(pharmacy.getAddress()).append("*\n")
                .append("График: *").append(pharmacy.getOpenHours()).append("*\n")
                .append("Телефон: ").append("`").append(pharmacy.getPhoneNumber()).append("`\n")
                .append("[Показать на карте](").append(pharmacy.getCoordinates()).append(")")
                .append("\n\n\n"));

        sb.append("\n\n").append("_\"Ледиком\"_");


        return sb.toString();
    }
}
