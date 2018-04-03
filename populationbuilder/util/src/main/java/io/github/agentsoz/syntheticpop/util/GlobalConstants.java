package io.github.agentsoz.syntheticpop.util;

/**
 * @author Bhagya N. Wickramasinghe
 */
public class GlobalConstants {

    public enum ExitCode {
        NORMAL(0, "All Good!"),
        IOERROR(1, "Input/output error"),
        ENVVAR(2, "Environment variable read error"),
        USERINPUT(3, "User input error"),
        PROGERROR(4, "Programmar error"),
        UNDEF(99, "Undefined error"),
        DATA_ERROR(5, "Error in population data");

        private int code;
        private String msg;

        ExitCode(int code, String msg) {
            this.code = code;
            this.msg = msg;
        }

        public int getCode() {
            return this.code;
        }

        public String getMsg() {
            return this.msg;
        }
    }
}
