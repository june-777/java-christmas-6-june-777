package christmas.view;

import christmas.controller.ChristmasController;
import christmas.domain.exception.OutOfRangeDayException;
import christmas.service.badge.BadgeService;
import christmas.service.event.EventService;
import christmas.service.event.discount.DiscountEventService;
import christmas.service.event.gift.FreeGiftEventService;
import christmas.service.order.OrderService;
import christmas.view.exception.InputDateBlankException;
import christmas.view.exception.InputDateNotIntegerException;
import christmas.view.exception.InputDateNotNumericException;
import christmas.view.exception.InputMenuWrongFormException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class InputViewTest {

    OrderService orderService;
    EventService eventService;
    BadgeService badgeService;
    ChristmasController christmasController;

    @BeforeEach
    void beforeEach() {
        orderService = new OrderService();
        eventService = new EventService(new DiscountEventService(), new FreeGiftEventService());
        badgeService = new BadgeService();
        christmasController = new ChristmasController(orderService, eventService, badgeService);
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " ", "  ", "    ", "     ", "\n", "\t", "\r"})
    @DisplayName("[Exception] 공백만 입력 시 예외가 발생한다.")
    void blank(String wrongInput) {
        InputView inputView = new InputView(() -> wrongInput);
        Assertions.assertThatThrownBy(() -> inputView.readDate())
                .isInstanceOf(InputDateBlankException.class);
    }

    @ParameterizedTest
    @ValueSource(strings = {"01", "02", "03", "011", "0000024"})
    @DisplayName("[Exception] 앞에 0이 있을 경우 예외가 발생한다.")
    void firstZero(String wrongInput) {
        InputView inputView = new InputView(() -> wrongInput);
        Assertions.assertThatThrownBy(() -> inputView.readDate())
                .isInstanceOf(InputDateNotNumericException.class);
    }

    @ParameterizedTest
    @ValueSource(strings = {" !1", "2ㅁ ", "3 1", "rdfd ", " dra", "1I", "%1", " ㄱ1", "1 ㄱ"})
    @DisplayName("[Exception] 문자 포함이면 예외가 발생한다.")
    void notNumeric(String wrongInput) {
        InputView inputView = new InputView(() -> wrongInput);
        Assertions.assertThatThrownBy(() -> inputView.readDate())
                .isInstanceOf(InputDateNotNumericException.class);
    }

    @ParameterizedTest
    @ValueSource(strings = {"-1", "-2", "-3", "-11", "-24"})
    @DisplayName("[Exception] 앞에 -가 있을 경우 예외가 발생한다.")
    void firstMinus(String wrongInput) {
        InputView inputView = new InputView(() -> wrongInput);
        Assertions.assertThatThrownBy(() -> inputView.readDate())
                .isInstanceOf(InputDateNotNumericException.class);
    }

    @ParameterizedTest
    @ValueSource(strings = {"2147483648", "21474836470"})
    @DisplayName("[Exception] 앞에 -가 있을 경우 예외가 발생한다.")
    void notInteger(String wrongInput) {
        InputView inputView = new InputView(() -> wrongInput);
        Assertions.assertThatThrownBy(() -> inputView.readDate())
                .isInstanceOf(InputDateNotIntegerException.class);
    }

    @ParameterizedTest
    @ValueSource(strings = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10",
            "11", "12", "13", "14", "15", "16", "17", "18", "19", "20",
            "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "10000", "2123123"})
    @DisplayName("[Success] 음수, 0으로 시작하지 않는 숫자는 성공한다.")
    void correct(String correctInput) {
        InputView inputView = new InputView(() -> correctInput);
        Assertions.assertThatCode(() -> inputView.readDate())
                .doesNotThrowAnyException();
    }

    @ParameterizedTest
    @ValueSource(strings = {"1 ", "2 ", "3 ", "4 ", "5 ", "6 ", "7 ", "8 ", "9 ", "10 ",
            "11 ", "   12 ", "13 ", "14 ", "15  ", "16   ", "17    ", "18  ", "19  ", "20 ",
            "21  ", "  22   ", "23 ", "24  ", "25 ", "26 ", "27  ", "28   ", "29      ", "30 ", "31  ", "10000  ",
            "   2123123 "})
    @DisplayName("[Success] 음수, 0으로 시작하지 않는 숫자는 성공한다.")
    void correctLastBlank(String correctInput) {
        InputView inputView = new InputView(() -> correctInput);
        Assertions.assertThatCode(() -> inputView.readDate())
                .doesNotThrowAnyException();
    }

    @ParameterizedTest
    @ValueSource(strings = {"32", "124", "12421"})
    @DisplayName("[Exception] 31을 넘어가는 수는 domain에서 예외가 발생한다.")
    void outOfRange(String correctInput) {
        InputView inputView = new InputView(() -> correctInput);
        int date = inputView.readDate();

        Assertions.assertThatThrownBy(() -> christmasController.createOrderDay(date))
                .isInstanceOf(OutOfRangeDayException.class);
    }

    @ParameterizedTest
    @ValueSource(strings = {"해산물파스타--1", "해산물파스타-1,크리스마스파스타--2", ",제로콜라-1", "제로콜라-1,",
            "해산물파스타-!", "초코케이크-타파스", "3-타파스", "타파스-3,4-초코케이크", "타파스-4,제로콜라-5-", "-타파스-5,제로콜라-1", "타-파-스-1"})
    @DisplayName("[Exception] 31을 넘어가는 수는 domain에서 예외가 발생한다.")
    void wrongMenuForm(String wrongInput) {
        InputView inputView = new InputView(() -> wrongInput);

        Assertions.assertThatThrownBy(() -> inputView.readMenuForm())
                .isInstanceOf(InputMenuWrongFormException.class);
    }

    @DisplayName("[Exception] XXX-X,XXX-XX,... 형식이 지켜지지 않는 경우")
    @ParameterizedTest
    @ValueSource(strings = {",타파스-1", "타파스-4,", "타 파스-5,,제로콜라-1"})
    void wrongMenuForm2(String wrongInput) {
        InputView inputView = new InputView(() -> wrongInput);

        Assertions.assertThatThrownBy(() -> inputView.readMenuForm())
                .isInstanceOf(InputMenuWrongFormException.class);
    }

    @DisplayName("[Exception] XXX-X 형식이 지켜지지 않는 경우")
    @ParameterizedTest
    @ValueSource(strings = {"-타파스-1", "타파스--1", "타-파스-1", "타파-스-1", "타파스1", " 타파스- -1", "타파스--", " - ", "-", "타파스-",
            "-7", "타파스-1,-", "타파스-6, -", "타파스-7, - ", "타파스-6,   -", "타파스-7,-  "})
    void wrongMenuAndCountForm(String wrongInput) {
        InputView inputView = new InputView(() -> wrongInput);

        Assertions.assertThatThrownBy(() -> inputView.readMenuForm())
                .isInstanceOf(InputMenuWrongFormException.class);
    }

    @DisplayName("[Exception] XXX-X 형식 중 X가 숫자가 아닌 경우")
    @ParameterizedTest
    @ValueSource(strings = {"타파스-!", "타파스-@", "타파스-ㄲ", "타파스-\n", "타파스-한개", " 타파스-김",
            "타파스-1!"})
    void wrongMenuCount(String wrongInput) {
        InputView inputView = new InputView(() -> wrongInput);

        Assertions.assertThatThrownBy(() -> inputView.readMenuForm())
                .isInstanceOf(InputMenuWrongFormException.class);
    }

    @DisplayName("[Success] 올바른 메뉴 형식이면 예외가 발생하지 않는다.")
    @ParameterizedTest
    @ValueSource(strings = {"타파스-1", "제로콜라-5,타파스-1", "바비큐립-7,해산물파스타-1,타파스-1",
            " 타파스-1", "타파스 -1", "타 파 스-1", "타 파 스 -1", " 타 파 스-2", " 타 파 스 -4",
            "!@#-1", "$%^ -2", " &*(-3", " *()_ -4",
            "해산물파스타 -1", "해 산 물 파 스 타-2", " 해산물파스타 -2"})
    void correctMenuForm(String wrongInput) {
        InputView inputView = new InputView(() -> wrongInput);

        Assertions.assertThatCode(() -> inputView.readMenuForm())
                .doesNotThrowAnyException();
    }
}