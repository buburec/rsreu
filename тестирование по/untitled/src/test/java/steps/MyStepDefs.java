package steps;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class MyStepDefs {
    @Given("Существует пользователь с логином vanya и паролем {int}a{int}")
    public void существуетПользовательСЛогиномVanyaИПаролемA(Integer int1, Integer int2) {
        // Write code here that turns the phrase above into concrete actions
        //throw new cucumber.api.PendingException();
    }

    @When("Пользователь вводит vanya и {int}a{int}")
    public void пользовательВводитVanyaИA(Integer int1, Integer int2) {
        // Write code here that turns the phrase above into concrete actions
        //throw new cucumber.api.PendingException();
    }

    @Then("Происходит авторизация")
    public void происходитАвторизация() {
        // Write code here that turns the phrase above into concrete actions
        //throw new cucumber.api.PendingException();
    }

    @Given("Существует пользователь с логином petya и паролем {int}d{int}")
    public void существуетПользовательСЛогиномPetyaИПаролемD(Integer int1, Integer int2) {
        // Write code here that turns the phrase above into concrete actions
        //throw new cucumber.api.PendingException();
    }

    @When("Пользователь вводит petya и {int}d{int}")
    public void пользовательВводитPetyaИD(Integer int1, Integer int2) {
        // Write code here that turns the phrase above into concrete actions
        //throw new cucumber.api.PendingException();
    }

    @Given("Существует пользователь с логином vasya и паролем gdh{int}gag")
    public void существуетПользовательСЛогиномVasyaИПаролемGdhGag(Integer int1) {
        // Write code here that turns the phrase above into concrete actions
        //throw new cucumber.api.PendingException();
    }

    @When("Пользователь вводит vasya и gdh{int}gag")
    public void пользовательВводитVasyaИGdhGag(Integer int1) {
        // Write code here that turns the phrase above into concrete actions
        //throw new cucumber.api.PendingException();
    }

    @When("Пользователь вводит vannya и {int}a{int}")
    public void пользовательВводитVannyaИA(Integer int1, Integer int2) {
        // Write code here that turns the phrase above into concrete actions
        //throw new cucumber.api.PendingException();
    }

    @Then("Выводится сообщение о неправильно введенном логине или пароле")
    public void выводитсяСообщениеОНеправильноВведенномЛогинеИлиПароле() {
        // Write code here that turns the phrase above into concrete actions
        // new cucumber.api.PendingException();
    }

    @When("Пользователь вводит petya и {int}f{int}")
    public void пользовательВводитPetyaИF(Integer int1, Integer int2) {
        // Write code here that turns the phrase above into concrete actions
        //throw new cucumber.api.PendingException();
    }

    @When("Пользователь вводит vasua и gbh{int}gag")
    public void пользовательВводитVasuaИGbhGag(Integer int1) {
        // Write code here that turns the phrase above into concrete actions
        //throw new cucumber.api.PendingException();
    }

    @Given("Позиция, которую пользователь хочет добавить в корзину")
    public void позицияКоторуюПользовательХочетДобавитьВКорзину() {
        // Write code here that turns the phrase above into concrete actions
//        throw new cucumber.api.PendingException();
    }

    @When("Пользователь нажимает кнопку {string}")
    public void пользовательНажимаетКнопку(String string) {
        // Write code here that turns the phrase above into concrete actions
//        throw new cucumber.api.PendingException();
    }

    @Then("В корзину добавляется одна выбранная уточка")
    public void вКорзинуДобавляетсяОднаВыбраннаяУточка() {
        // Write code here that turns the phrase above into concrete actions
//        throw new cucumber.api.PendingException();
    }

    @Given("Заказ, который необходимо оплатить")
    public void заказКоторыйНеобходимоОплатить() {
        // Write code here that turns the phrase above into concrete actions
//        throw new cucumber.api.PendingException();
    }

    @When("Пользователь нажимает оплатить")
    public void пользовательНажимаетОплатить() {
        // Write code here that turns the phrase above into concrete actions
//        throw new cucumber.api.PendingException();
    }

    @When("Не возникает ошибка оплаты")
    public void неВозникаетОшибкаОплаты() {
        // Write code here that turns the phrase above into concrete actions
//        throw new cucumber.api.PendingException();
    }

    @Then("Происходит оплата")
    public void происходитОплата() {
        // Write code here that turns the phrase above into concrete actions
//        throw new cucumber.api.PendingException();
    }

    @When("Возникает ошибка оплаты")
    public void возникаетОшибкаОплаты() {
        // Write code here that turns the phrase above into concrete actions
//        throw new cucumber.api.PendingException();
    }

    @Then("Выводится сообщение об ошибке")
    public void выводитсяСообщениеОбОшибке() {
        // Write code here that turns the phrase above into concrete actions
//        throw new cucumber.api.PendingException();
    }

    @Given("Товар, количество которго пользователь хочет увеличить")
    public void товарКоличествоКоторгоПользовательХочетУвеличить() {
        // Write code here that turns the phrase above into concrete actions
//        throw new cucumber.api.PendingException();
    }

    @When("Получаемое количество не превышает количество товара на складе")
    public void получаемоеКоличествоНеПревышаетКоличествоТовараНаСкладе() {
        // Write code here that turns the phrase above into concrete actions
//        throw new cucumber.api.PendingException();
    }

    @Then("Количество товара увеличивается")
    public void количествоТовараУвеличивается() {
        // Write code here that turns the phrase above into concrete actions
//        throw new cucumber.api.PendingException();
    }

    @When("Получаемое количество превышает количество товара на складе")
    public void получаемоеКоличествоПревышаетКоличествоТовараНаСкладе() {
        // Write code here that turns the phrase above into concrete actions
//        throw new cucumber.api.PendingException();
    }

    @Then("Количество товара не увеличивается")
    public void количествоТовараНеУвеличивается() {
        // Write code here that turns the phrase above into concrete actions
//        throw new cucumber.api.PendingException();
    }

    @When("Получаемое количество не меньше {int}")
    public void получаемоеКоличествоНеМеньше(Integer int1) {
        // Write code here that turns the phrase above into concrete actions
//        throw new cucumber.api.PendingException();
    }

    @Then("Количество товара уменьшается")
    public void количествоТовараУменьшается() {
        // Write code here that turns the phrase above into concrete actions
//        throw new cucumber.api.PendingException();
    }

    @When("Получаемое количество меньше {int}")
    public void получаемоеКоличествоМеньше(Integer int1) {
        // Write code here that turns the phrase above into concrete actions
//        throw new cucumber.api.PendingException();
    }

    @Then("Количество товара не уменьшается")
    public void количествоТовараНеУменьшается() {
        // Write code here that turns the phrase above into concrete actions
//        throw new cucumber.api.PendingException();
    }

    @Given("Товар, который пользователь хочет удалить")
    public void товарКоторыйПользовательХочетУдалить() {
        // Write code here that turns the phrase above into concrete actions
//        throw new cucumber.api.PendingException();
    }

    @Then("Товар удаляется из корзины")
    public void товарУдаляетсяИзКорзины() {
        // Write code here that turns the phrase above into concrete actions
//        throw new cucumber.api.PendingException();
    }

    @Given("Искомая уточка")
    public void искомаяУточка() {
        // Write code here that turns the phrase above into concrete actions
//        throw new cucumber.api.PendingException();
    }

    @When("Пользователь вводит в строку поиска искомое наименование")
    public void пользовательВводитВСтрокуПоискаИскомоеНаименование() {
        // Write code here that turns the phrase above into concrete actions
//        throw new cucumber.api.PendingException();
    }

    @When("Искомый товар существует на складе")
    public void искомыйТоварСуществуетНаСкладе() {
        // Write code here that turns the phrase above into concrete actions
//        throw new cucumber.api.PendingException();
    }

    @Then("Выводятся результаты поиска")
    public void выводятсяРезультатыПоиска() {
        // Write code here that turns the phrase above into concrete actions
//        throw new cucumber.api.PendingException();
    }

    @When("Искомый товар не существует на складе")
    public void искомыйТоварНеСуществуетНаСкладе() {
        // Write code here that turns the phrase above into concrete actions
//        throw new cucumber.api.PendingException();
    }

    @Then("Выводится сообщение о том, что искомый товар не найден")
    public void выводитсяСообщениеОТомЧтоИскомыйТоварНеНайден() {
        // Write code here that turns the phrase above into concrete actions
//        throw new cucumber.api.PendingException();
    }

}
