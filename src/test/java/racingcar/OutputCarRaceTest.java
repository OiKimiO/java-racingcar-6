package racingcar;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static java.lang.String.format;

import org.junit.jupiter.api.Test;
import racingcar.output.PrintTest;

public class OutputCarRaceTest extends PrintTest {
    private static final String 차이름 = "차이름";
    private static final String 차이름들 = "pobi1,pobi2,pobi3,pobi4";

    enum MessageType{
        INPUT_CAR_NAME_PRINT("경주할 자동차 이름을 입력하세요.(이름은 쉼표(,) 기준으로 구분)"),
        INPUT_CAR_RACING_COUNT_PRINT("시도할 회수는 몇회인가요?"),
        INPUT_DATA_PRINT("%s"),

        OUTPUT_RACE_RESULT_PRINT("실행 결과"),
        OUTPUT_IMPLEMENTATION_RESULT_PRINT("%s : %s"),
        OUTPUT_WINNERS("최종 우승자 : %s")
        ;

        private final String value;

        MessageType(String value){
            this.value = value;
        }

        public String getValue(){
            return value;
        }
    }

    static class OutputCarRace{
        public static OutputCarRace print(final MessageType messageType){
            return new OutputCarRace(messageType);
        }

        public static OutputCarRace printf(final MessageType messageType, final Object... inputNames){
            return new OutputCarRace(messageType, inputNames);
        }

        private OutputCarRace(){}

        private OutputCarRace(final MessageType messageType){
            String message = messageType.getValue();

            printMessage(message);
        }

        private OutputCarRace(final MessageType messageType, final Object... inputNames){
            String message = messageType.getValue();

            printMessage(format(message, inputNames));
        }

        private static void printMessage(String message){
            System.out.println(message);
        }
    }

    @Test
    void 자동차_이름입력멘트_출력_정상(){
        OutputCarRace.print(MessageType.INPUT_CAR_NAME_PRINT);

        assertThat(output()).contains(MessageType.INPUT_CAR_NAME_PRINT.getValue());
    }

    @Test
    void 자동차_경주회수_출력_정상(){
        OutputCarRace.print(MessageType.INPUT_CAR_RACING_COUNT_PRINT);

        assertThat(output()).contains(MessageType.INPUT_CAR_RACING_COUNT_PRINT.getValue());
    }

    @Test
    void 자동차_경주_실행결과_정상(){
        OutputCarRace.print(MessageType.OUTPUT_RACE_RESULT_PRINT);

        assertThat(output()).contains(MessageType.OUTPUT_RACE_RESULT_PRINT.getValue());
    }

    @Test
    void 자동차_이름들_출력_정상(){
        String format = format(MessageType.OUTPUT_IMPLEMENTATION_RESULT_PRINT.getValue(), 차이름, "");
        OutputCarRace.printf(MessageType.OUTPUT_IMPLEMENTATION_RESULT_PRINT, 차이름, "");

        assertThat(outputf(차이름)).contains(format);
    }

    @Test
    void 입력했던_값_출력(){
        String format = format(MessageType.INPUT_DATA_PRINT.getValue(), 차이름들);
        OutputCarRace.printf(MessageType.INPUT_DATA_PRINT, 차이름들);

        assertThat(outputf(차이름들)).contains(format);
    }

    @Test
    void 우승자_값_출력(){
        String format = format(MessageType.OUTPUT_WINNERS.getValue(), 차이름들);
        OutputCarRace.printf(MessageType.OUTPUT_WINNERS, 차이름들);

        assertThat(outputf(차이름들)).contains(format);
    }
}
